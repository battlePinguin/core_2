package concurrency;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ComplexTaskExecutor {
    private final int numberOfTasks;

    public ComplexTaskExecutor(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
    }


    public void executeTasks() {
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfTasks);


        CyclicBarrier barrier = new CyclicBarrier(numberOfTasks, () -> {
            System.out.println("Все задачи выполнены. Теперь мы можем объеденить результаты!");
        });

        for (int i = 1; i <= numberOfTasks; i++) {
            executorService.submit(new ComplexTask(i, barrier));
        }

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(1, TimeUnit.MINUTES)) {
                System.out.println("Не все задачи завершились за 1 минуту. Завершение принудительно.");
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
