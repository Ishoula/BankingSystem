import java.sql.*;
import java.util.*;

abstract class BankUser{

    public String name;
    public String password;
    
    Scanner c=new Scanner(System.in);
    abstract void login();
    abstract void signup();

    public void getCredentials(){
        System.out.println("Let's get you started: ");
        System.out.println("Enter your name: ");
        name=c.nextLine();
        System.out.println("Enter password: ");
        password=c.nextLine();
        

    }

  
}