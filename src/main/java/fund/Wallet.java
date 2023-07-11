package fund;

public interface Wallet {

    long getBalance();

    void getWinning(long amount);

    boolean subtract(long amount) throws IllegalStateException;
}
