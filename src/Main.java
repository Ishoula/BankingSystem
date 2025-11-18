import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("MENU");
        System.out.println("Who is using our system: ");
        System.out.println("1. Customer");
        System.out.println("2. Staff");

        int choice;
        while (true) {
            if (scan.hasNextInt()) {
                choice = scan.nextInt();
                scan.nextLine(); // flush newline
                break;
            } else {
                System.out.println("Invalid input. Enter 1 or 2.");
                scan.next(); // skip invalid input
            }
        }

        switch (choice) {
            case 1 -> {
                Customer cust = new Customer();
                System.out.println("1. Login");
                System.out.println("2. Signup");

                int custChoice;
                while (true) {
                    if (scan.hasNextInt()) {
                        custChoice = scan.nextInt();
                        scan.nextLine(); 
                        break;
                    } else {
                        System.out.println("Invalid input. Enter 1 or 2.");
                        scan.next();
                    }
                }

                if (custChoice == 1) {
                    cust.login(); 
                } else if (custChoice == 2) {
                    cust.signup(); 
                } else {
                    System.out.println("Invalid choice.");
                }
            }

            case 2 -> {
                Staff staff = new Staff();
                System.out.println("1. Login");
                System.out.println("2. Signup");

                int staffChoice;
                while (true) {
                    if (scan.hasNextInt()) {
                        staffChoice = scan.nextInt();
                        scan.nextLine(); // flush
                        break;
                    } else {
                        System.out.println("Invalid input. Enter 1 or 2.");
                        scan.next();
                    }
                }

                if (staffChoice == 1) {
                    staff.login();  
                } else if (staffChoice == 2) {
                    staff.signup(); 
                } else {
                    System.out.println("Invalid choice.");
                }
            }

            default -> System.out.println("Invalid input. Exiting...");
        }

        scan.close();
    }
}
