package fund;

public interface Wallet {

    long getBalance();

    void getWinning(long amount);

    long subtract(long amount);
}
