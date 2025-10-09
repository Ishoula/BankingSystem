class Account {
    private String accountNumber;
    private double balance;
    private String ownerName;

    public Account(String accountNumber, double balance, String ownerName){
        this.accountNumber=accountNumber;
        this.balance=balance;
        this.ownerName=ownerName;
    }

    public Account() {

    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void deposit(double amount){
        balance+=amount;
        System.out.printf("You have deposited: %.2f. Your balance is %.2f \n",amount,getBalance());
    }

    public void withdraw(double amount){
        if(balance>=amount){
            balance-=amount;
            System.out.printf("You have withdrawed: %.2f. Your new balance is %.2f \n", amount,getBalance());
        }
    }
}
