package Concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

// TimeUnit is an enum for 
public class CheckResultsFutureExp {
    private static int counter = 0;

    public static void main(String[] args) {
        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor();
            Future<?> result = service.submit(() -> {
                for (int i = 0; i < 500000; i++)
                    CheckResultsFutureExp.counter++;
            });
            result.get(1, TimeUnit.SECONDS);
            System.out.println(counter);
            System.out.println("Reached");
        } catch (Exception e) {
            System.out.println("not reached in time");
        } finally {
            if (service != null)
                service.shutdown();
        }
    }
}
