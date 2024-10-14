import collection.CollectionUtils;
import collection.СollectionFilter;
import concurrency.BankAccount;
import concurrency.BlockingQueue;
import concurrency.ComplexTaskExecutor;
import concurrency.ConcurrentBank;
import core.StringBuilderWithUndo;

import java.math.BigDecimal;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
//        StringBuilder
//        buildStringWithUndo();
//        Collection - фильтрация
//        filterArray();
//        Collection - count of elements
//        countElements();
//        Блокирующая очередь
        produceAndConsumeGenerics();
//        Многопоточный банковский счет
        transferСoncurrently();
//        Синхронизаторы
        executeTasksWithBarrier();


    }

    public static void executeTasksWithBarrier() {
        ComplexTaskExecutor taskExecutor = new ComplexTaskExecutor(5);

        Runnable testRunnable = () -> {
            System.out.println(Thread.currentThread().getName() + " started the test.");

            taskExecutor.executeTasks();

            System.out.println(Thread.currentThread().getName() + " completed the test.");
        };

        Thread thread1 = new Thread(testRunnable, "TestThread-1");
        Thread thread2 = new Thread(testRunnable, "TestThread-2");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void transferСoncurrently() {
        ConcurrentBank bank = new ConcurrentBank();

        BankAccount account1 = bank.createAccount(BigDecimal.valueOf(1000));
        BankAccount account2 = bank.createAccount(BigDecimal.valueOf(500));

        Thread transferThread1 = new Thread(() -> bank.transfer(account1, account2, BigDecimal.valueOf(200)));
        Thread transferThread2 = new Thread(() -> bank.transfer(account2, account1, BigDecimal.valueOf(100.00)));

        transferThread1.start();
        transferThread2.start();

        try {
            transferThread1.join();
            transferThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Total balance: " + bank.getTotalBalance());
    }

    public static void produceAndConsumeGenerics() {
        BlockingQueue<Integer> queue = new BlockingQueue<>(5);

        Runnable producer = () -> {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Производитель пытается добавить: " + i);
                    queue.enqueue(i);
                    System.out.println("Производитель добавил: " + i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Runnable consumer = () -> {
            try {
                for (int i = 0; i < 10; i++) {
                    Integer item = queue.dequeue();
                    System.out.println("Потребитель извлек: " + item);
                    Thread.sleep(300);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        new Thread(producer).start();
        new Thread(consumer).start();
    }

    public static void filterArray() {
        Object[] array = {1, 2, 3, 5, 6, 7, 8, 9};

        СollectionFilter evenFilter = o -> {
            if ((int) o % 2 == 0) {
                return o;
            }
            return null;
        };

        Object[] filteredArray = CollectionUtils.filter(array, evenFilter);

        printIt(filteredArray);
    }

    public static void countElements() {
        String[] array = {"java", "banana", "java", "banana", "code"};

        Map<String, Integer> elementCount = CollectionUtils.countOccurrences(array);

        for (Map.Entry<String, Integer> entry : elementCount.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public static void buildStringWithUndo() {
        StringBuilderWithUndo stringBuilderWithUndo = new StringBuilderWithUndo();

        stringBuilderWithUndo.append("Один")
                .print("Первое добавление")
                .append(", Два")
                .print("Второе добавление")
                .undo()
                .print("Отмена второго добавления")
                .insert(4, ", Три")
                .print("После вставки")
                .undo()
                .print("Отмена вставки")
                .delete(0, 4)
                .print("После удаления")
                .undo()
                .print("Отмена удаления");
    }

    private static void printIt(Object[] array) {
        for (Object element : array) {
            System.out.println(element);
        }
    }


}
