import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner scan= new Scanner(System.in);
        System.out.println("MENU");
        System.out.println("Who is using our system: ");
        System.out.println("1. Staff");
        System.out.println("2. Customer");
        int choice=0;
        try{
            switch( choice){
                case 1:
                    System.out.println("1. Create Account");
                    Staff staff=new Staff();
                    staff.createAccount();
                    System.out.println("2.Login");
                    break;
                case 2:
                    System.out.println("1.Create Account");
                    System.out.println("2.Login");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}