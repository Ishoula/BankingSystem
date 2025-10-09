import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("MENU");
        System.out.println("Who is using our system: ");
        System.out.println("1. Staff");
        System.out.println("2. Customer");
        int choice = scan.nextInt();
        try {
            switch (choice) {
                case 1:
                    System.out.println("STAFF_MENU");
                    System.out.println("1. Create Account");
                    System.out.println("2.Login");
                    Staff staff = new Staff();
                    choice = scan.nextInt();
                    switch (choice) {
                        case 1:
                            staff.createAccount();
                            break;
                        case 2:
                            staff.login();
                            break;
                        default:
                            System.out.println("Invalid option");
                            break;
                    }

                    break;
                case 2:
                    Customer cust = new Customer();
                    System.out.println("STAFF_MENU");
                    System.out.println("1. Create Account");
                    System.out.println("2.Login");

                    choice = scan.nextInt();
                    switch (choice) {
                        case 1:
                            cust.createAccount();
                            break;
                        case 2:
                            cust.login();
                            break;
                        default:
                            System.out.println("Invalid option");
                            break;
                    }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input");
        }catch(Exception e){
            System.out.println("Ohhh! "+e.getMessage());
        }
    }
}