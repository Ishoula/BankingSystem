import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;


class BankUser {
   String query="SELECT * FROM customers WHERE accNum=? ";
   Scanner c=new Scanner(System.in);
   public void deposit(String accNum){
      
       try(Connection conn = Database.getConnection()) {
           if(conn==null){
               System.out.println("Cannot continue without conection to database");
               return;
           }

           PreparedStatement select_stmt=conn.prepareStatement(query);
           select_stmt.setString(1, accNum);
           ResultSet res=select_stmt.executeQuery();
           if(res.next()){
               double balance=res.getDouble("balance");
               System.out.println("Amount to deposit");
               double amount=c.nextDouble();
               balance+=amount;


               //update the balance
               query="UPDATE customers SET balance=? WHERE accNum=?";
               PreparedStatement update_smt=conn.prepareStatement(query);
              
               update_smt.setDouble(1,balance);
               update_smt.setString(2, accNum);
               update_smt.executeQuery();
               System.out.println("Deposited "+amount+" to your account "+accNum+" your balance is "+balance);


              
           }
       } catch (SQLException e) {
           System.out.println("SQL error: " +e.getMessage());
       }catch(InputMismatchException e){
           System.out.println("Amount should be a number!");
       }
   }
   public void withdraw(String accNum){
       try(Connection conn = Database.getConnection()) {
           if(conn==null){
               System.out.println("Cannot continue without conection to database");
               return;
           }

           PreparedStatement stmt=conn.prepareStatement(query);
           stmt.setString(1, accNum);
           ResultSet res=stmt.executeQuery();
           if(res.next()){
               double balance=res.getDouble("balance");
               System.out.println("Amount to withdraw: ");
               double amount=c.nextDouble();
               if(amount>balance){
                   System.out.println("You do not have money to perform this transaction");
                   return;
               }
               balance-=amount;
               //update the balance
              
               query="UPDATE customers SET balance=? WHERE accNum=?";
               PreparedStatement update_smt=conn.prepareStatement(query);
               update_smt.setDouble(1,balance);
               update_smt.setString(2, accNum);
               update_smt.executeQuery();
               System.out.println("Withdrew "+amount+" from your account "+accNum+" your balance is "+balance);


              
           }
       } catch (SQLException e) {
           System.out.println("SQL error: " +e.getMessage());
       }catch(InputMismatchException e){
           System.out.println("Amount should be a number!");
       }
    
   }

   public void checkBalance(String accNum){
       try(Connection conn = Database.getConnection()) {
           if(conn==null){
               System.out.println("Cannot continue without conection to database");
               return;
           }


           PreparedStatement stmt=conn.prepareStatement(query);
           stmt.setString(1, accNum);
           ResultSet res=stmt.executeQuery();
           if(res.next()){
               double balance=res.getDouble("balance");
               System.out.println("Your balance=" +balance);
             
           }
       } catch (SQLException e) {
           System.out.println("SQL error: " +e.getMessage());
       }
   }


   public void closeAccount(){
       String name,accNum,query="delete from customers where accNum=(select accNum from customers where name=? and accNum=?);";
       System.out.println("Let's close the account!");
       System.out.println("Name of account owner: ");
       name=c.nextLine();
       System.out.println("Account number of owner: ");
       accNum=c.nextLine();


       try (Connection conn=Database.getConnection()){
           if(conn==null){
               System.out.println("Cannot continue without connection database");
               return;
           }
           query="select * from customers where name=? and accNum=?";
           PreparedStatement selStatement=conn.prepareStatement(query);
           selStatement.setString(1, name);
           selStatement.setString(2, accNum);
           if(selStatement.executeQuery().next()){
               PreparedStatement delStatement=conn.prepareStatement(query);
               delStatement.setString(1,name);
               delStatement.setString(2, accNum);
               delStatement.executeUpdate();
               System.out.println("Successfully closed the account");
               return;
           }
           System.out.println("Account not found");
       } catch (SQLException e) {
           System.out.println("SQL error:"+e.getMessage());
       }
   }
   public void viewAccounts(){
       String query="SELECT * FROM customers";
       try (Connection conn=Database.getConnection()){
           if(conn==null){
               System.out.println("Cannot continue without connection to the database");
               return;
           }
           PreparedStatement selecStatement=conn.prepareStatement(query);
           ResultSet results=selecStatement.executeQuery();
           boolean found=false;
           while (results.next()) {
               found=true;
               String accNum=results.getString("accNum");
               String name=results.getString("name");
               double balance=results.getDouble("balance");
               System.out.println("Account number: "+ accNum+" Name: "+name+" Balance: "+ balance);
               System.out.println("                                    ____________________________                                 ");
           }
           if(!found){
               System.out.println("No users");
           }           
       } catch (SQLException e) {
           System.out.println("SQL error: " +e.getMessage());
       }
   }
   public void transfer(String accNum){
       String desAcc;
       String name;
       double amount;
       System.out.println("Enter recipient account number: ");
       desAcc=c.nextLine();
       System.out.println("Enter recipient name: ");
       name=c.nextLine();
       System.out.println("Enter amount to transfer:");
       amount=c.nextDouble();
       try(Connection conn=Database.getConnection()){
           if(conn==null){
               System.out.println("Cannot continue without connection to database");
               return;
           }
           //check if recipient exists
           String query="SELECT * FROM customers WHERE accNum=? and name=?";
           PreparedStatement selStmt=conn.prepareStatement(query);
           selStmt.setString(1,desAcc);
           selStmt.setString(2, name);
           ResultSet res=selStmt.executeQuery();
           if(!res.next()){
               System.out.println("Invalid details.");
               return;
           }//check if sender has enough balance
           query="SELECT * FROM customers WHERE accNum=?";
           PreparedStatement sel2=conn.prepareStatement(query);
           sel2.setString(1, accNum);
           res=sel2.executeQuery();
           if(res.next()){
               double balance=res.getDouble("balance");
           if(balance<amount){
               System.out.println("You do not have enough  balance to perform this transaction");
               return;
           }


           balance-=amount;
           //update balance
           query="update customers set balance=? where accNum=?";
           PreparedStatement update=conn.prepareStatement(query);
           update.setDouble(1, balance);
           update.setString(2, desAcc);
           update.executeUpdate();
           System.out.println("You have successfully transfered " + amount+" to "+ name);  
           }
          


       }catch(SQLException e){
           System.out.println("SQL error: "+e.getMessage());
       }
   }
}
