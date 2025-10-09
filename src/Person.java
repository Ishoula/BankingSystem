import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

class Staff{
    private static final AtomicInteger id=new AtomicInteger(1);
    private String name;
    private String password;
    Scanner c=new Scanner(System.in);

    Staff(String name, String password){
        this.name=name;
        this.password=password;
    }
    Staff(){

    }
    public void createAccount(){
        System.out.println("Enter name: ");
        String name=c.nextLine();
        System.out.println("Enter password: ");
        String password=c.nextLine();
        new Staff(name,password);
        String fileName="staff.txt";
        try(FileWriter writer=new FileWriter(fileName,true)){
            writer.append(name);
            writer.append(password);
        }catch(IOException e){
            System.out.println("File not found");
        }
    }

    public void login(){
        System.out.println("Enter password: ");

    }

}