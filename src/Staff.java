import java.sql.*;
class Staff extends BankUser{
   public String key;
    public void login(){
        getCredentials();
        //get key
        System.out.println("Enter your private key: ");
        key=c.nextLine();

        String query="SELECT * FROM staff WHERE name=? AND password=? AND keyString=?";
        try(Connection conn=Database.getConnection()){
            if(conn==null){
                System.out.println("Failed to connect to the database");
                return;
            }

            PreparedStatement stmt=conn.prepareStatement(query);
            stmt.setString(1,name);
            stmt.setString(2,password);
            stmt.setString(3, key);
            ResultSet res=stmt.executeQuery();
            if(res.next()){
                System.out.println("Successfully logged in");
                
                System.out.println("Invalid credentials");
            }

        }catch(SQLException e){
            System.out.println("SQL error: "+e);
        }
    }

    public void signup(){
        getCredentials();
        String createQuery="INSERT INTO staff (name,password) VALUES(?,?)";
        String retrieveQuery="SELECT * FROM staff WHERE name=? AND password=?";
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
                key=res.getString("keyString");
                System.out.println("Here goes your key: "+key);
                System.out.println("Note!!!!!!!!!!!!!!!!1");
                System.out.println("You will need this anytime you want to access our system");
                
            }

        }catch(SQLException e){
            System.out.println("SQL error: "+e);
        }
    }

     public void staffOperations(){
        System.out.println("STAFF MENU");
        System.out.println("!__________________________________________________!");
        System.out.println("1.Check Accounts");
        System.out.println("2.Close Accounts");
        int choice=c.nextInt();
        if(choice==1){

        }else{

        }
       
    }

    private void checkBalance(String accNum){
        String query="SELECT * FROM customers";
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

    private void closeAccount(){
      String query="delete from customers where accNum=(select accNum from customers where name=? and accNum=?)";
      System.out.println("Let's close the account!");
      System.out.println("Name of account owner: ");
      String name=c.nextLine();
      System.out.println("Account number of owner: ");
      String accNum=c.nextLine();

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

}

