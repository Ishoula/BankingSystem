import java.util.Scanner;
import java.sql.*;
class Customer{
    Scanner sc=new Scanner(System.in);

    public void createAccount(){
        System.out.println("Enter name: ");
        String name=sc.nextLine();
        System.out.println("Enter password: ");
        String password=sc.nextLine();
        System.out.println("Enter initial balance: ");
        double balance=sc.nextDouble();
       String query="INSERT INTO customer (name,password,balance) VALUES(?,?,?)";

       try (Connection conn=Database.getConnection()
       ){
        if(conn==null){
            System.out.println("Cannot continue without database connection");
            return;
        }
        PreparedStatement stmt=conn.prepareStatement(query);
        stmt.setString(1,name);
        stmt.setString(2, password);
        stmt.setDouble(3,balance);
        stmt.executeUpdate();
        System.out.println("Registration successful");
       } catch (SQLException e) {
        System.out.println("Registration failed: "+e.getMessage());
        System.out.println("Try registering again");
       }

    }
    public void login(){
        System.out.println("Enter name: ");
        String name=sc.nextLine();
        System.out.println("Enter password: ");
        String password=sc.nextLine();
       
        String query="SELECT *FROM customer WHERE name=? and password=?";
       
       try (Connection conn=Database.getConnection()
       ){
        if(conn==null){
            System.out.println("Cannot continue without database connection");
            return;
        }
        PreparedStatement stmt=conn.prepareStatement(query);
        stmt.setString(1, name);
        stmt.setString(2, password);
       
          ResultSet res=stmt.executeQuery();
        if(res.next()){
          System.out.println("What are the next steps");
          //call the CustomUser class which has the Customer methods
        }
        
       } catch (SQLException e) {
         System.out.println("Invalid credentials. Try again");
       }
    }
}