import java.sql.*;
import java.util.InputMismatchException;
class Customer extends BankUser{
    public String key;
    int choice;
    public void login(){
        getCredentials();
        //get key
        System.out.println("Enter your private key: ");
        key=c.nextLine();
        String query="SELECT * FROM customers WHERE name=? AND password=?";

        try(Connection conn=Database.getConnection()){
            if(conn==null){
                System.out.println("Failed to connect to the database");
                return;
            }

            PreparedStatement stmt=conn.prepareStatement(query);
            stmt.setString(1,name);
            stmt.setString(2,password);
            ResultSet res=stmt.executeQuery();
            if(res.next()){
                System.out.println("Successfully logged in");
            }else{
                System.out.println("Invalid credentials");
            }

        }catch(SQLException e){
            System.out.println("SQL error: "+e);
        }
    }

    public void signup(){
        getCredentials();
        //getkey

        String createQuery="INSERT INTO customers (name,password) VALUES(?,?)";
        String retrieveQuery="SELECT * FROM customers WHERE name=? AND password=?";
        try(Connection conn=Database.getConnection()){
            if(conn==null){
                System.out.println("Failed to connect to the database");
                return;
            }

            PreparedStatement createStmt=conn.prepareStatement(createQuery);
            createStmt.setString(1,name);
            createStmt.setString(2,password);
            createStmt.executeUpdate();
            PreparedStatement retrieveStmt=conn.prepareStatement(retrieveQuery);
            retrieveStmt.setString(1,name);
            retrieveStmt.setString(2,password);
            ResultSet res=retrieveStmt.executeQuery();
            
            if(res.next()){
                String accNum=res.getString("accNum");
                key=res.getString("keyString");
                System.out.println("Here goes your accNumber: "+accNum);
                System.out.println("Here goes your key: "+key);
                System.out.println("Note!!!!!!!!!!!!!!!!1");
                System.out.println("You will need these anytime you want to access our system");
            }

        }catch(SQLException e){
            System.out.println("SQL error: "+e);
        }
    }

    private void deposit(){
        String updateQuery="UPDATE customers SET balance=? WHERE accNum=?";
        String checkQuery="SELECT * FROM customers where name=? AND password=?";
      try(Connection conn = Database.getConnection()) {
          if(conn==null){
              System.out.println("Cannot continue without conection to database");
              return;
          }

          PreparedStatement select_stmt=conn.prepareStatement(checkQuery);
          select_stmt.setString(1, name);
          select_stmt.setString(2, password);
          ResultSet res=select_stmt.executeQuery();
          if(res.next()){
    
              double balance=res.getDouble("balance");
              System.out.println("Amount to deposit");
              double amount=c.nextDouble();
              balance+=amount;
              //update the balance
              String accNum=res.getString("accNum");
              PreparedStatement update_smt=conn.prepareStatement(updateQuery);
              update_smt.setDouble(1,balance);
              update_smt.setString(2, accNum);
              update_smt.executeQuery();
              System.out.println("Money successfully deposited to your account. Your balance: "+balance);
  
          }
      } catch (SQLException e) {
          System.out.println("SQL error: " +e.getMessage());
      }catch(InputMismatchException e){
          System.out.println("Amount should be a number!");
      }
  }

  private void withdraw(){

  }

  public void operations(){
    System.out.println("Let's get you started");
    lo
    System.out.println("Customer Menu");
    System.out.println("______________________________________");
    System.out.println("1.Deposit");
    System.out.println("2.Withdraw");
    System.out.println("3.Check balance");
    System.out.println("4.Transfer");
    choice=c.nextInt();

    if(choice==1){
        deposit();
    }
  }

}