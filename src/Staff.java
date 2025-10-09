import java.sql.*;
import java.util.Scanner;
class Staff{
   
    Scanner c=new Scanner(System.in);
    public void createAccount(){
        System.out.println("Enter name: ");
        String name=c.nextLine();
        System.out.println("Enter password: ");
        String password=c.nextLine();
       //save the staff user in database 
       String query="INSERT INTO staff (name,password) VALUES(?,?)";

       
       try (Connection conn=Database.getConnection()
       ){
        if(conn==null){
            System.out.println("Cannot continue without database connection");
            return;
        }
        PreparedStatement stmt=conn.prepareStatement(query);
        stmt.setString(1,name);
        stmt.setString(2, password);
        stmt.executeUpdate();
        System.out.println("Registration successful");
       } catch (SQLException e) {
        System.out.println("Registration failed: "+e.getMessage());
        System.out.println("Try registering again");
       }

    }

    public void login(){
        System.out.println("Enter name: ");
        String name=c.nextLine();
        System.out.println("Enter password: ");
        String password=c.nextLine();

        String query="SELECT *FROM staff WHERE name=? and password=?";
       try (Connection conn=Database.getConnection()){
        if(conn==null){
        System.out.println("Cannot continue without a database connection");
        return;
       }
        PreparedStatement stmt=conn.prepareStatement(query);
        stmt.setString(1, name);
        stmt.setString(2, password);
        ResultSet res=stmt.executeQuery();
        if(res.next()){
          System.out.println("You can now start working");
          //call the StaffUser class which has the Staff methods
        }
        
       } catch (SQLException e) {
         System.out.println("Invalid credentials. Try again");
       }

    }

}

