package money;

public class VirtualWallet {
    long balance;

    VirtualWallet(){}

    VirtualWallet(long amount) {
        balance = amount;
    }

    public long getBalance() {
        return balance;
    }

    public void getWinning(long amount) {
        balance += amount;
    }
}
