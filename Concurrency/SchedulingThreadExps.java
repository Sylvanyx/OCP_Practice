package Concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/*
 * ScheduledExecutorService methods
 * --------------------------------
 * 
 * schedule(Callable<V> callable,long delay,TimeUnit u) - execute task after given delay
 * schedule(Runnable cmd, long delay,TimeUnit u) 
 * 
 * scheduleAtFixedRate(Runnable cmd, long initDelay,long period,TimeUnit u)
 * scheduleWithFixedDelay(Runnable cmd, long init,long delay,TimeUnit u)
 * 
 */
public class SchedulingThreadExps {

    public static void scheduleExp(ScheduledExecutorService s) {
        s = Executors.newSingleThreadScheduledExecutor();
        Runnable task1 = () -> System.out.println("Welcome to the jungle baby");
        Runnable task2 = () -> System.out.println("keep walking till u find ur way out");
        Runnable task3 = () -> System.out.println("still walking...");
        Runnable task4 = () -> System.out.println("rock");
        ScheduledFuture<?> res1 = s.schedule(task1, 0, TimeUnit.SECONDS);
        ScheduledFuture<?> res2 = s.schedule(task2, 1, TimeUnit.SECONDS);
        s.scheduleAtFixedRate(task3, 2, 1, TimeUnit.SECONDS);
        s.scheduleWithFixedDelay(task4, 3, 5, TimeUnit.SECONDS);

    }

    public static void main(String[] args) {
        ScheduledExecutorService s = null;
        s = Executors.newSingleThreadScheduledExecutor();
        scheduleExp(s);
    }

}
