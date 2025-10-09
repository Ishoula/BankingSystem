//abstract class BankUser {
//    String name;
//    abstract void accessBank();
//    public BankUser(String name){
//        this.name=name;
//    }
//}
//
//class CustomerUser extends BankUser{
//    public CustomerUser(String name) {
//        super(name);
//    }
//    @Override
//    void accessBank() {
//        System.out.println("Performing transactions");
//
//    }
//}
//
//class StaffUser extends BankUser{
//    int id;
//    public StaffUser(String name, int i) {
//        super(name);
//        this.id=i;
//    }
//    public String get_name(){
//        return name;
//    }
//    @Override
//    void accessBank() {
//        System.out.println("Managing accounts");
//    }
//
//    public void createAccount(Bank b, String accountNumber, double balance, String ownerName){
//        b.createAccount(accountNumber,balance,ownerName);
//    }
//    public void closeAccount(Bank b, String accountNumber){
//        b.closeAccount(accountNumber);
//    }
//}
