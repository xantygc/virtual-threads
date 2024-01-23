package basic.virtual.threads.old;

import basic.virtual.threads.common.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Launcher
{
    public static void main(String[] args) {
        try {
            try (ExecutorService executor = Executors.newFixedThreadPool(100)) {
                List<Task> tasks = new ArrayList<>();

                // Creating a list of 1000 tasks
                for (int i = 0; i < 1000; i++) {
                    tasks.add(new Task(i));
                }

                long startTime = System.currentTimeMillis();

                // Invoking all tasks and obtaining a list of Futures
                List<Future<Integer>> futures = executor.invokeAll(tasks);

                long sum = 0;

                // Summing up the results from each task
                for (Future<Integer> future : futures) {
                    sum += future.get();
                }

                long executionTime = System.currentTimeMillis() - startTime;

                // Displaying the sum and total execution time
                System.out.println("Sum = " + sum + "; Execution Time = " + executionTime + " ms");
            }
        } catch (InterruptedException | ExecutionException e) {
            // Handling exceptions
            e.printStackTrace();
        }
    }
}
