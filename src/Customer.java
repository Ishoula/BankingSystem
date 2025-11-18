import java.sql.*;
import java.util.InputMismatchException;

class Customer extends BankUser {

    private double balance;

    // SQL queries
    private static final String SELECT_BY_ACC =
        "SELECT * FROM customers WHERE accNum=?";
    private static final String UPDATE_BALANCE =
        "UPDATE customers SET balance=? WHERE accNum=?";
    private static final String LOGIN_QUERY =
        "SELECT * FROM customers WHERE name=? AND password=? AND email=?";
    private static final String SIGNUP_QUERY =
        "INSERT INTO customers (name, password, balance, email) VALUES (?, ?, ?, ?)";
    private static final String RETRIEVE_QUERY =
        "SELECT * FROM customers WHERE name=? AND password=? AND email=?";

    @Override
    public void login() {
        getCredentials();

        try (Connection conn = Database.getConnection()) {

            if (conn == null) {
                System.out.println("Failed to connect to database");
                return;
            }

            PreparedStatement stmt = conn.prepareStatement(LOGIN_QUERY);
            stmt.setString(1, name);
            stmt.setString(2, password);
            stmt.setString(3, email);

            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                accNum = res.getString("accNum");
                System.out.println("Login successful!");
                operations();
            } else {
                System.out.println("Invalid login credentials.");
                return;
            }

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }

    @Override
    public void signup() {
        getCredentials();

        try (Connection conn = Database.getConnection()) {

            if (conn == null) {
                System.out.println("Failed to connect to database");
                return;
            }

            System.out.print("Enter initial deposit (minimum 2000): ");
            double amount = c.nextDouble();
            c.nextLine(); // flush

            if (amount < 2000) {
                System.out.println("Minimum starting balance is 2000.");
                return;
            }

            PreparedStatement stmt = conn.prepareStatement(SIGNUP_QUERY);
            stmt.setString(1, name);
            stmt.setString(2, password);
            stmt.setDouble(3, amount);
            stmt.setString(4, email);
            stmt.executeUpdate();

            PreparedStatement retrieve = conn.prepareStatement(RETRIEVE_QUERY);
            retrieve.setString(1, name);
            retrieve.setString(2, password);
            retrieve.setString(3, email);

            ResultSet res = retrieve.executeQuery();
            if (res.next()) {
                accNum = res.getString("accNum");
                System.out.println("Signup successful! Your account number: " + accNum);
                operations();
            }

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }

    private boolean loadAccount(Connection conn, String acc) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ACC);
        stmt.setString(1, acc);

        ResultSet res = stmt.executeQuery();

        if (res.next()) {
            balance = res.getDouble("balance");
            return true;
        }
        return false;
    }

    private void deposit() {
        getAccNum();

        try (Connection conn = Database.getConnection()) {
            if (!loadAccount(conn, accNum)) {
                System.out.println("Account not found.");
                return;
            }

            System.out.print("Enter amount to deposit: ");
            double amount = c.nextDouble();
            c.nextLine();

            if (amount <= 0) {
                System.out.println("Deposit must be greater than zero.");
                return;
            }

            double newBalance = balance + amount;

            PreparedStatement update = conn.prepareStatement(UPDATE_BALANCE);
            update.setDouble(1, newBalance);
            update.setString(2, accNum);
            update.executeUpdate();

            System.out.println("Deposit successful. New balance: " + newBalance);

        } catch (SQLException | InputMismatchException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void withdraw() {
        getAccNum();

        try (Connection conn = Database.getConnection()) {
            if (!loadAccount(conn, accNum)) {
                System.out.println("Account not found.");
                return;
            }

            System.out.print("Enter amount to withdraw: ");
            double amount = c.nextDouble();
            c.nextLine();

            if (amount <= 0) {
                System.out.println("Amount must be greater than zero.");
                return;
            }

            if (amount > balance) {
                System.out.println("Insufficient funds.");
                return;
            }

            double newBalance = balance - amount;

            PreparedStatement update = conn.prepareStatement(UPDATE_BALANCE);
            update.setDouble(1, newBalance);
            update.setString(2, accNum);
            update.executeUpdate();

            System.out.println("Withdrawal successful. New balance: " + newBalance);

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }

    private void checkBalance() {
        getAccNum();

        try (Connection conn = Database.getConnection()) {
            if (!loadAccount(conn, accNum)) {
                System.out.println("Account not found.");
                return;
            }

            System.out.println("Your balance: " + balance);

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }

    private void transfer() {
        
        getAccNum();

        try (Connection conn = Database.getConnection()) {

            conn.setAutoCommit(false); // atomic transaction

            // Load sender
            if (!loadAccount(conn, accNum)) {
                System.out.println("Sender account not found.");
                return;
            }

            System.out.print("Enter receiver account number: ");
            String receiverAcc = c.nextLine();

            if (receiverAcc.equals(accNum)) {
                System.out.println("Cannot transfer to the same account.");
                return;
            }

            // Load receiver
            if (!loadAccount(conn, receiverAcc)) {
                System.out.println("Receiver account not found.");
                return;
            }
            double receiverBalance = balance;

            // Re-load sender after overwriting 'balance'
            loadAccount(conn, accNum);
            double senderBalance = balance;

            System.out.print("Enter amount to transfer: ");
            double amount = c.nextDouble();
            c.nextLine();

            if (amount <= 0 || amount > senderBalance) {
                System.out.println("Invalid transfer amount.");
                return;
            }

            // Update sender
            PreparedStatement updateSender = conn.prepareStatement(UPDATE_BALANCE);
            updateSender.setDouble(1, senderBalance - amount);
            updateSender.setString(2, accNum);
            updateSender.executeUpdate();

            // Update receiver
            PreparedStatement updateReceiver = conn.prepareStatement(UPDATE_BALANCE);
            updateReceiver.setDouble(1, receiverBalance + amount);
            updateReceiver.setString(2, receiverAcc);
            updateReceiver.executeUpdate();

            conn.commit();
            System.out.println("Transfer successful!");

        } catch (SQLException e) {
            System.out.println("Transfer failed: " + e.getMessage());
        }
    }

    
    public void operations() {
        boolean run = true;

        while (run) {
            System.out.println("\nCustomer Menu");
            System.out.println("______________________________________");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check balance");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");
            System.out.print("Make your choice: ");

            int choice = c.nextInt();
            c.nextLine(); // FIX SCANNER BUG

            switch (choice) {
                case 1 -> deposit();
                case 2 -> withdraw();
                case 3 -> checkBalance();
                case 4 -> transfer();
                case 5 -> {
                    System.out.println("Goodbye!");
                    run = false;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
