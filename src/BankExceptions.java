//    String name;
//    int id;
//
//    public Person(String name, int id){
//        this.name=name;
//        this.id=id;
//    }
//}
//
//class Customer extends Person{
//
//    String email;
//    String phone;
//    public Customer(String name, int id, String email, String phone) {
//        super(name, id);
//        this.email=email;
//        this.phone=phone;
//
//    }
//
//    public void performTransaction(Account a, double amount,String operation){
//        if(operation=="Deposit"){
//            a.deposit(amount);
//        }else{
//            a.withdraw(amount);
//        }
//    }
//
//}
//
//class Staff extends Person{
//
//    public Staff(String name, int id) {
//        super(name, id);
//    }
//    public String get_name(){
//        return name;
//    }
//    public void createAccount(Bank b, String accountNumber, double balance, String ownerName){
//        b.createAccount(accountNumber,balance,ownerName);
//    }
//    public void closeAccount(Bank b, String accountNumber){
//        b.closeAccount(accountNumber);
//    }
//}


//    public static Scanner c=new Scanner(System.in);
//    public static void createAccount(Bank bank,StaffUser sta){
//        System.out.println("Enter your name: ");
//        String name=c.nextLine();
//        System.out.println("Enter your account number: ");
//        String acc_num=c.nextLine();
//        System.out.println("Enter some initial amount: ");
//        double amount=c.nextDouble();
//        sta.createAccount(bank,acc_num,amount,name);
//    }
//
//    public static void closeAccount(Bank bank, StaffUser staff){
//        System.out.println("Enter your account number");
//        String acc_num=c.nextLine();
//        staff.closeAccount(bank,acc_num);
//    }


//import java.util.ArrayList;
//class Bank {
//    private ArrayList<Account>accounts;
//
//    public Bank(){
//        accounts=new ArrayList<>();
//    }
//
//    public void createAccount(String accountNumber, double balance, String ownerName){
//        Account newAccount=new Account(accountNumber,balance,ownerName);
//        accounts.add(newAccount);
//        System.out.println("Account created: "+accountNumber);
//    }
//
//    public void closeAccount(String accountNumber){
//        Account toRemove=null;
//        for(Account a: accounts){
//            if(a.getAccountNumber().equals(accountNumber)){
//                toRemove=a;
//                break;
//            }
//        }
//
//        if(toRemove!=null){
//            accounts.remove(toRemove);
//            System.out.println("Account closed: "+accountNumber);
//        }else{
//            System.out.println("Account not found");
//        }
//    }
//}
