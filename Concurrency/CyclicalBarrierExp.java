package Concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicalBarrierExp {

    public static void removeLions() {
        System.out.println("removing lions");
    };

    public static void cleanCages() {
        System.out.println("cleaning");
    };

    public static void returnLions() {
        System.out.println("returning lions");
    };

    public static void badZooWork() {
        removeLions();
        cleanCages();
        returnLions();
    }

    public static void goodZooWork(CyclicBarrier c1, CyclicBarrier c2)
            throws InterruptedException, BrokenBarrierException {
        removeLions();
        c1.await();
        cleanCages();
        c2.await();
        returnLions();
    }

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        ExecutorService s = null;

        // anti example
        try {
            s = Executors.newFixedThreadPool(4);
            var manager = new CyclicalBarrierExp();
            for (int i = 0; i < 5; i++)
                s.submit(() -> manager.badZooWork());
        } finally {
            s.shutdown();
        }
        Thread.sleep(2000);
        System.out.println();

        s = Executors.newFixedThreadPool(4);
        CyclicBarrier c1 = new CyclicBarrier(4);
        CyclicBarrier c2 = new CyclicBarrier(4);
        for (int i = 0; i < 5; i++)
            s.submit(() -> {
                try {
                    goodZooWork(c1, c2);
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });

    }
}
