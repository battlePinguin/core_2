package concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ComplexTask implements Runnable{
    private final int taskId;
    private final CyclicBarrier barrier;

    public ComplexTask(int taskId, CyclicBarrier barrier) {
        this.taskId = taskId;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.println("Задача " + taskId + " выполняется потоком: " + Thread.currentThread().getName());

            Thread.sleep(1000 * taskId);

            System.out.println("Задача " + taskId + " выполнена потоком " + Thread.currentThread().getName());

            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();
        }
    }
}
