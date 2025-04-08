public class InsufficientFundException extends Exception {
    public InsufficientFundException() {
        super("Error: Insufficient funds for this withdrawal");
    }
}