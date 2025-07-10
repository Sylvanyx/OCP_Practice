package Concurrency;

import java.util.concurrent.ExecutorService;

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
 */
public class PoolingExps {

    public static void main(String[] args) {
        ExecutorService s = null;
    }

}
