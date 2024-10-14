package concurrency;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private BigDecimal balance; // Баланс счета
    private final UUID accountNumber; // Уникальный номер счета (UUID)
    private final Lock lock = new ReentrantLock(); // Для защиты операций над балансом

    public BankAccount(UUID accountNumber, BigDecimal initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    // Метод для пополнения счета
    public void deposit(BigDecimal amount) {
        lock.lock();
        try {
            balance = balance.add(amount);
        } finally {
            lock.unlock();
        }
    }

    // Метод для снятия средств со счета
    public void withdraw(BigDecimal amount) {
        lock.lock();
        try {
            if (amount.compareTo(balance) <= 0) {
                balance = balance.subtract(amount);
            } else {
                throw new IllegalArgumentException("Insufficient funds");
            }
        } finally {
            lock.unlock();
        }
    }

    // Метод для получения текущего баланса
    public BigDecimal getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }

    public UUID getAccountNumber() {
        return accountNumber;
    }

    // Метод для блокировки счета при переводе
    public void lockAccount() {
        lock.lock();
    }

    // Метод для разблокировки счета при переводе
    public void unlockAccount() {
        lock.unlock();
    }
}
