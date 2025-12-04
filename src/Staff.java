import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class Staff extends BankUser {

    int choice;
    public String selectQuery = "SELECT *FROM staff WHERE email=? and password=?";
    private String email;

    public void login() {

        getCredentials();

        try (Connection conn = Database.getConnection()) {
            if (conn == null) {
                System.out.println("Cannot continue without database connection");
                return;
            }

            // check if user exists
            PreparedStatement stmt = conn.prepareStatement(selectQuery);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                System.out.println("Let's get the job done");
                OperationLogger.log("Staff login successful for email " + email);
            } else {
               throw new InvalidCredentialsException();
            }

        } catch (SQLException e) {
            System.out.println("SQL ERROR: " + e.getMessage());
        }
    }

    public void signup() {
        getCredentials();

        String createQuery = "INSERT INTO staff (name,password,email) VALUES(?,?,?)";
        try (Connection conn = Database.getConnection()) {
            if (conn == null) {
                System.out.println("Failed to connect to the database");
                return;
            }
            PreparedStatement createStmt = conn.prepareStatement(createQuery);
            createStmt.setString(1, name);
            createStmt.setString(2, password);
            createStmt.setString(3, email);

            createStmt.executeUpdate();
            PreparedStatement retrieveStmt = conn.prepareStatement(selectQuery);
            retrieveStmt.setString(1, email);
            retrieveStmt.setString(2, password);
            ResultSet res = retrieveStmt.executeQuery();

            if (res.next()) {
                System.out.println("Now let's do business");
                OperationLogger.log("Staff signup completed for email " + email);

            } else {
                System.out.println("Sign up failed u should try again later");
            }

        }

        catch (SQLException e) {
            System.out.println("SQL error: " + e);
        }
    }

    private void closeAccount() {
        String name, accNum;
        String selectQuery="select * from customers where name=? and accNum=?";
        String deleteQuery = "delete from customers where accNum=(select accNum from customers where name=? and accNum=?)";
        System.out.println("Let's close the account!");
        c.nextLine();
        System.out.println("Name of account owner: ");
        name = c.nextLine();
        System.out.println("Account number of owner: ");
        accNum = c.nextLine();

        try (Connection conn = Database.getConnection()) {
            if (conn == null) {
                System.out.println("Cannot continue without connection database");
                return;
            }
            //check if user exists
            PreparedStatement selStatement = conn.prepareStatement(selectQuery);
            selStatement.setString(1, name);
            selStatement.setString(2, accNum);
            if (selStatement.executeQuery().next()) {
                PreparedStatement delStatement = conn.prepareStatement(deleteQuery);
                delStatement.setString(1, name);
                delStatement.setString(2, accNum);
                delStatement.executeUpdate();
                System.out.println("Successfully closed the account");
                OperationLogger.log("Account " + accNum + " closed by staff " + email);
                return;
            }
            else{
                throw new AccountNotFoundException(accNum);
            }
        } catch (SQLException e) {
            System.out.println("SQL error:" + e.getMessage());
        }
    }

     private void viewAccounts(){
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
               OperationLogger.log("Staff " + email + " viewed account " + accNum);
               System.out.println("                                    ____________________________                                 ");
           }
           if(!found){
               System.out.println("No users");
           }           
       } catch (SQLException e) {
           System.out.println("SQL error: " +e.getMessage());
       }
   }



public void operations() {
    boolean run = true;

    while (run) {
        System.out.println("\nLet's keep the momentum!");
        System.out.println("Staff Menu");
        System.out.println("______________________________________");
        System.out.println("1. View Accounts");
        System.out.println("2. Close Account");
        System.out.println("3. Exit");
        System.out.print("Make your choice: ");

        while (!c.hasNextInt()) {
            System.out.println("Invalid choice. Enter a number.");
            c.next();
        }

        choice = c.nextInt();
        c.nextLine(); // flush leftover newline

        switch (choice) {
            case 1 -> viewAccounts();
            case 2 -> closeAccount();
            case 3 -> {
                System.out.println("Goodbye!");
                run = false;
            }
            default -> System.out.println("Invalid choice.");
        }
    }
}

}
