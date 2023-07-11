package fund;

public class VirtualWallet implements Wallet{
    private long balance;

    public VirtualWallet(){}

    public VirtualWallet(long amount) {
        balance = amount;
    }

    @Override
    public long getBalance() {
        return balance;
    }

    @Override
    public void getWinning(long amount) {
        balance += amount;
    }

    @Override
    public boolean subtract(long amount) throws IllegalStateException{
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }

}
