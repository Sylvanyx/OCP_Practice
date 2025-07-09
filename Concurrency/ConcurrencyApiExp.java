package Concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * The Concurrency API includes the ExecutorService interface  which defines services to create & manage threads
 * The Executors factory class is included for instantiating ExecutorService objects
 * 
 * 
 * Shuting down tasks has a lifecycle 
 * shutdown() and isTerminated() are used to check lifecycle while shutting down a thread
 *      Active -> both methods return false
 *      Shutting Down - > isShutdown==true, isTerminated==false
 *      Shutdown -> both are true 
 * 
 * 
 * You can submit a task to an executor service in several ways
 *  void execute(Runnable cmd) 
 *  Future<?> submit(Runnable task)
 *  <T> Future<T> submit(Callable<T> task)
 *  <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException
 * <T> T invokeAny(Collection<? extents Callable<T>> tasks) throws InterruptedException,ExecutionException
 */
public class ConcurrencyApiExp {

    public static void main(String[] args) {
        ExecutorService service = null;
        Runnable t1 = () -> System.out.println("getting inventory");
        Runnable t2 = () -> {
            for (int i = 0; i < 3; i++)
                System.out.println("record: " + i);
        };

        try {
            service = Executors.newSingleThreadExecutor();
            System.out.println("starting...");
            service.execute(t1);
            service.execute(t2);
            service.execute(t1);

        } catch (Exception e) {
            if (service != null)
                service.shutdown();
        }
    }
}
