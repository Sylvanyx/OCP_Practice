package Concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * Executor Factory methods
 * ------------------------
 * 
 * ExecutorService newsingleThreadExecutor()
 * ScheduledExecutorService newSingleThreadScheduledExecutor()
 * 
 * ExecutorService newCachedThreadPool() - creates new threads as needed,reuses old when possible
 * ExecutorService newFixedThreadPool(int) - reuses fixed number of threads operating off shared bounded queue
 * ScheduledExecutorService newScheduledThreadpool(int) - can schedule cmds after given delay or to execute periodically
 * 
 * Atomic classis are provided for working with primitive operations
 * Atomic is the property of an operation to have its reads/writes performed without interruption from another thread
 * in other words the operation is carried out as a single unit of execution
 * 
 * AtomicBoolean
 * AtomicLong
 * AtomicInteger
 * 
 * get() / set() / getAndSet()
 * incrementAndGet() / decrementAndGet()
 * getAndIncrement() / getAndDecrement()
 * 
 */
public class PoolingAndAtomicExp {
    static int count = 0;
    static AtomicInteger atomic = new AtomicInteger(0);

    public static void badIncrementAndGet() {
        System.out.println((++count) + " ");
    }

    public static void badCounter(ExecutorService s) {
        try {
            s = Executors.newFixedThreadPool(20);
            for (int i = 0; i < 5; i++) {
                s.submit(() -> badIncrementAndGet());
            }
        } catch (Exception e) {

        }
    }

    public static void goodCounter(ExecutorService s) {
        try {
            s = Executors.newFixedThreadPool(20);
            for (int i = 0; i < 5; i++) {
                s.submit(() -> System.out.println(atomic.incrementAndGet()));
            }
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService s = null;
        badCounter(s);
        Thread.sleep(2000);
        System.out.println();
        goodCounter(s);
    }

}
