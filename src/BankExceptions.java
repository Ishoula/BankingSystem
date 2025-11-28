public class BankExceptions extends RuntimeException {

    public BankExceptions(String message) {
        super(message);
    }

    public BankExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}

class InvalidCredentialsException extends BankExceptions {

    public InvalidCredentialsException() {
        super("Invalid credentials supplied.");
    }

    public InvalidCredentialsException(String message) {
        super(message);
    }
}

class AccountNotFoundException extends BankExceptions {

    public AccountNotFoundException(String accountNumber) {
        super("Account not found: " + accountNumber);
    }
}

class InsufficientFundsException extends BankExceptions {

    public InsufficientFundsException(double attemptedAmount, double availableBalance) {
        super("Attempted to use " + attemptedAmount + " with only " + availableBalance + " available.");
    }
}

class MinimumBalanceException extends BankExceptions {

    public MinimumBalanceException(double requiredAmount) {
        super("Operation requires at least " + requiredAmount + ".");
    }
}

class TransferException extends BankExceptions {

    public TransferException(String message) {
        super(message);
    }

    public TransferException(String message, Throwable cause) {
        super(message, cause);
    }
}

class DatabaseConnectionException extends BankExceptions {

    public DatabaseConnectionException() {
        super("Unable to establish database connection.");
    }

    public DatabaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
