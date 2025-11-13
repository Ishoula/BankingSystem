// import java.util.Scanner;
// import java.sql.*;


// class Customer {
//    Scanner sc = new Scanner(System.in);


//    public void createAccount() {
//        System.out.println("Enter name: ");
//        String name = sc.nextLine();
//        System.out.println("Enter password: ");
//        String password = sc.nextLine();
//        System.out.println("Enter initial balance: ");
//        double balance = sc.nextDouble();
//        if(balance<2000){
//            System.out.println("Minimum balance to open account is 2000");
//            return;
//        }
//        String query = "INSERT INTO customers (name,password,balance) VALUES(?,?,?)";


//        try (Connection conn = Database.getConnection()) {
//            if (conn == null) {
//                System.out.println("Cannot continue without database connection");
//                return;
//            }
//            PreparedStatement stmt = conn.prepareStatement(query);
//            stmt.setString(1, name);
//            stmt.setString(2, password);
//            stmt.setDouble(3, balance);
//            stmt.executeUpdate();
//            System.out.println("Registration successful");
//            // retrieve account number
//            query = "SELECT accNum FROM customers where name=? AND password=?";
//            stmt = conn.prepareStatement(query);
//            stmt.setString(1, name);
//            stmt.setString(2, password);
//            ResultSet res = stmt.executeQuery();
//            String accNum;
//            if (res.next()) {
//                accNum = res.getString("accNum");
//                System.out.println("This is your account number: " + accNum);
//                operations(accNum);
//            }


//        } catch (SQLException e) {
//            System.out.println("Registration failed: " + e.getMessage());
//            System.out.println("Try registering again");
//        }


//    }


//    public void login() {
//        System.out.println("Enter name: ");
//        String name = sc.nextLine();
//        System.out.println("Enter password: ");
//        String password = sc.nextLine();


//        String query = "SELECT *FROM customers WHERE name=? and password=?";


//        try (Connection conn = Database.getConnection()) {
//            if (conn == null) {
//                System.out.println("Cannot continue without database connection");
//                return;
//            }
//            // check if user exists
//            PreparedStatement stmt = conn.prepareStatement(query);
//            stmt.setString(1, name);
//            stmt.setString(2, password);
//            ResultSet res = stmt.executeQuery();
//            String accNum;
//            if (res.next()) {
//                accNum = res.getString("accNum");
//                operations(accNum);
//                return;
//            }else{
//                System.out.println("Invalid credentials");
//                return;
//            }


//        } catch (SQLException e) {
//            System.out.println("SQL ERROR: "+e.getMessage());
//        }
//    }


//    public void operations(String accNum) {
//     //    BankUser acc = new BankUser();
//        while (true) {
//            System.out.println("What's next");
//            System.out.println("1. Deposit");
//            System.out.println("2. Withdraw");
//            System.out.println("3. Check balance");
//            System.out.println("4. Transfer");
//            System.out.println("5. Exit");
//            int choice = sc.nextInt();
//            switch (choice) {
//                case 1:
//                    acc.deposit(accNum);
//                    break;
//                case 2:
              
//                    acc.withdraw(accNum);
//                    break;
//                case 3:
//                    acc.checkBalance(accNum);
//                    break;
//                case 4:
//                    acc.transfer(accNum);
//                    break;
//                case 5:
//                    System.out.println("The story ends");
//                    return;


//                default:
//                    System.out.println("Invalid option");
//                    break;
//            }


//        }
//    }
// }



// import java.sql.*;
// import java.util.Scanner;
// class Staff{
   
//     Scanner c=new Scanner(System.in);
//     public void createAccount(){
//         System.out.println("Enter name: ");
//         String name=c.nextLine();
//         System.out.println("Enter password: ");
//         String password=c.nextLine();
//        //save the staff user in database 
//        String query="INSERT INTO staff (name,password) VALUES(?,?)";

       
//        try (Connection conn=Database.getConnection()
//        ){
//         if(conn==null){
//             System.out.println("Cannot continue without database connection");
//             return;
//         }
//         PreparedStatement stmt=conn.prepareStatement(query);
//         stmt.setString(1,name);
//         stmt.setString(2, password);
//         stmt.executeUpdate();
//         System.out.println("Registration successful");
//        } catch (SQLException e) {
//         System.out.println("Registration failed: "+e.getMessage());
//         System.out.println("Try registering again");
//        }

//     }

//     public void login(){
//         System.out.println("Enter name: ");
//         String name=c.nextLine();
//         System.out.println("Enter password: ");
//         String password=c.nextLine();

//         String query="SELECT *FROM staff WHERE name=? and password=?";
//        try (Connection conn=Database.getConnection()){
//         if(conn==null){
//         System.out.println("Cannot continue without a database connection");
//         return;
//        }
//         PreparedStatement stmt=conn.prepareStatement(query);
//         stmt.setString(1, name);
//         stmt.setString(2, password);
//         ResultSet res=stmt.executeQuery();
//         if(res.next()){
//           System.out.println("You can now start working");
//           //call the StaffUser class which has the Staff methods
//           operations();

//         }
        
//        } catch (SQLException e) {
//          System.out.println("Invalid credentials. Try again");
//        }

//     }

//      public void operations() {
//        BankUser acc = new BankUser();
//        while (true) {
//            System.out.println("What's next");
//            System.out.println("1. Check accounts");
//            System.out.println("2. Close Account");
//            System.out.println("3. Exit");
//            int choice = c.nextInt();
//            switch (choice) {
//                case 1:
//                    acc.viewAccounts();
//                    break;
//                case 2:
              
//                   acc.closeAccount();
//                   break;
//                case 3:
//                    System.out.println("The story ends");
//                    return;
//                default:
//                    System.out.println("Invalid option");
//                    break;
//            }


//        }
//    }
// }


// import java.sql.*;
// import java.util.Scanner;
// class Staff{
   
//     Scanner c=new Scanner(System.in);
//     public void createAccount(){
//         System.out.println("Enter name: ");
//         String name=c.nextLine();
//         System.out.println("Enter password: ");
//         String password=c.nextLine();
//        //save the staff user in database 
//        String query="INSERT INTO staff (name,password) VALUES(?,?)";

       
//        try (Connection conn=Database.getConnection()
//        ){
//         if(conn==null){
//             System.out.println("Cannot continue without database connection");
//             return;
//         }
//         PreparedStatement stmt=conn.prepareStatement(query);
//         stmt.setString(1,name);
//         stmt.setString(2, password);
//         stmt.executeUpdate();
//         System.out.println("Registration successful");
//        } catch (SQLException e) {
//         System.out.println("Registration failed: "+e.getMessage());
//         System.out.println("Try registering again");
//        }

//     }

//     public void login(){
//         System.out.println("Enter name: ");
//         String name=c.nextLine();
//         System.out.println("Enter password: ");
//         String password=c.nextLine();

//         String query="SELECT *FROM staff WHERE name=? and password=?";
//        try (Connection conn=Database.getConnection()){
//         if(conn==null){
//         System.out.println("Cannot continue without a database connection");
//         return;
//        }
//         PreparedStatement stmt=conn.prepareStatement(query);
//         stmt.setString(1, name);
//         stmt.setString(2, password);
//         ResultSet res=stmt.executeQuery();
//         if(res.next()){
//           System.out.println("You can now start working");
//           //call the StaffUser class which has the Staff methods
//           operations();

//         }
        
//        } catch (SQLException e) {
//          System.out.println("Invalid credentials. Try again");
//        }

//     }

//      public void operations() {
//        BankUser acc = new BankUser();
//        while (true) {
//            System.out.println("What's next");
//            System.out.println("1. Check accounts");
//            System.out.println("2. Close Account");
//            System.out.println("3. Exit");
//            int choice = c.nextInt();
//            switch (choice) {
//                case 1:
//                    acc.viewAccounts();
//                    break;
//                case 2:
              
//                   acc.closeAccount();
//                   break;
//                case 3:
//                    System.out.println("The story ends");
//                    return;
//                default:
//                    System.out.println("Invalid option");
//                    break;
//            }


//        }
//    }
// }

