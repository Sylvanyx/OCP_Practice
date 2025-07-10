package Concurrency;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/*
* java.util.concurrent.Callable is a functional interface
* unlike Runnable, the call method can throw a checked exception
*   public interface Callable<V>{ V call() throws Exception; }
*
* Runnable and Callable are interchangeable when the lambda impl does not throw an exception and there is no return type
*  
*/
public class CallableExp {

    public static void addData(ExecutorService s) throws Exception {
        try {
            s = Executors.newSingleThreadExecutor();
            Future<Integer> result = s.submit(() -> 30 + 10);
            Future<Integer> result2 = s.submit(() -> 50 + 20 * 42 / 5 ^ 6 + 8 * 8);
            Future<String> result3 = s.submit(() -> {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 100; i++) {
                    sb.append(i).append(" ");
                }
                return sb.toString();
            });
            System.out.println(result.get());
            System.out.println(result2.get());
            System.out.println(result3.get());
        } catch (Exception e) {
        } finally {
            s.shutdown();
        }
        if (s != null) {
            s.awaitTermination(1, TimeUnit.SECONDS);
            if (s.isTerminated())
                System.out.println("Done");
            else
                System.out.println("Somethings still running");
        }
    }

    public static void invokeSomething(ExecutorService s) throws Exception {
        s = Executors.newSingleThreadExecutor();
        Callable<Integer> t1 = () -> 30 + 10;
        Callable<Integer> t2 = () -> 50 + 20 * 42 / 5 ^ 6 + 8 * 8;
        List<Future<Integer>> futures = s.invokeAll(List.of(t1, t2));
        for (Future<Integer> f : futures)
            System.out.println(f.get());
    }

    public static void randomInvoke(ExecutorService s) throws Exception {
        s = Executors.newSingleThreadExecutor();
        Callable<String> t1 = () -> "squirtle";
        Callable<String> t2 = () -> "charmander";
        Callable<String> t3 = () -> "bulbasaur";
        String future = s.invokeAny(List.of(t1, t2, t3));
        System.out.println(future);
    }

    public static void main(String[] args) throws Exception {
        ExecutorService s = null;
        addData(s);
        invokeSomething(s);
        randomInvoke(s);

    }
}
