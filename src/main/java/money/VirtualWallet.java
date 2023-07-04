package money;

public class VirtualWallet {
    long balance;

    public VirtualWallet(){}

    public VirtualWallet(long amount) {
        balance = amount;
    }

    public long getBalance() {
        return balance;
    }

    public void getWinning(long amount) {
        balance += amount;
    }

    public long subtract(long amount) {
        if (amount > balance) {
            throw new IllegalStateException("지갑에서 잔액보다 더 많은 금액 출금 시도");
        }
        balance -= amount;
        return amount;
    }

}
