import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("MENU");
        System.out.println("Who is using our system: ");
        System.out.println("1. Customer");
        System.out.println("2. Staff");
        
        int choice = scan.nextInt();
        if(choice==1){
            Customer cust=new Customer();
            System.out.println("Let's move on with the next steps: ");
            System.out.println("1.Login");
            System.out.println("2.Signup");
            choice=scan.nextInt();
            if(choice==1){
                cust.login();
                cust.operations();
                
            } else{
                cust.signup();
            }           
        }else{
            System.out.println("Daah not easy");
        }
    }
}