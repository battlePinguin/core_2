package concurrency;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ConcurrentBank {
    private final List<BankAccount> accounts = new ArrayList<>();

    public synchronized BankAccount createAccount(BigDecimal initialBalance) {
        UUID accountNumber = UUID.randomUUID();
        BankAccount account = new BankAccount(accountNumber, initialBalance);
        accounts.add(account);
        return account;
    }

    public void transfer(BankAccount fromAccount, BankAccount toAccount, BigDecimal amount) {

        BankAccount firstLock = fromAccount.getAccountNumber().compareTo(toAccount.getAccountNumber()) < 0 ? fromAccount : toAccount;
        BankAccount secondLock = fromAccount.getAccountNumber().compareTo(toAccount.getAccountNumber()) < 0 ? toAccount : fromAccount;

        firstLock.lockAccount();
        try {
            secondLock.lockAccount();
            try {
                if (fromAccount.getBalance().compareTo(amount) >= 0) {
                    fromAccount.withdraw(amount);
                    toAccount.deposit(amount);
                    System.out.println("Переведено " + amount + " с счета " + fromAccount.getAccountNumber()
                            + " на счет " + toAccount.getAccountNumber());
                } else {
                    System.out.println("Недостаточно средств на счете " + fromAccount.getAccountNumber());
                }
            } finally {
                secondLock.unlockAccount();
            }
        } finally {
            firstLock.unlockAccount();
        }
    }

    public synchronized BigDecimal getTotalBalance() {
        BigDecimal totalBalance = BigDecimal.ZERO;
        for (BankAccount account : accounts) {
            totalBalance = totalBalance.add(account.getBalance());
        }
        return totalBalance;
    }
}
