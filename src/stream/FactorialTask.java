package stream;

import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Long> {
    private final int n;
    private static final int THRESHOLD = 5;

    public FactorialTask(int n) {
        this.n = n;
    }

    @Override
    protected Long compute() {
        if (n < 0) {
            throw new IllegalArgumentException("Факториал для отрицательных чисел не определён: n = " + n);
        }
        if (n <= THRESHOLD) {
            return sequentialFactorial(n);
        }

        FactorialTask subTask = new FactorialTask(n - 1);
        subTask.fork();
        long result = n * subTask.join();

        return result;
    }

    private long sequentialFactorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
