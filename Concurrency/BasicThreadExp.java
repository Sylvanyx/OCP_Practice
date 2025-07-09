package Concurrency;

// Concurrency is the property of executing multiple threads/processes at the same time
// OS's use a thread scheduler to coordinate threads
// A context switch occurs when a threads time is completed before its task is finished
//the context switch is the process of storing the threads state for later use
/*
 * public interface Runnable{
 *  void run();
 * }
 */
public class BasicThreadExp {

    class SampleThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread is running");
        }
    }

    static class StaticSampleThread extends Thread { // static so no enclosing class is needed
        @Override
        public void run() {
            System.out.println("Thread is running");
        }
    }

    public static void main(String[] args) {
        // You can define a task with java.lang.Runnable interface
        Runnable r1 = () -> System.out.println("hello");
        Runnable r2 = () -> {
            int i = 10;
            i++;
            System.out.println(i);
        };
        // run a Runnable task by passing it to the Thread constructor
        new Thread(r2).start();

        // Runnnable task has void return type
        // Runnable r3= () -> "";

        // run() does not execute a new thread, method runs synchronously with main()
        System.out.println("running");
        new StaticSampleThread().run();

        // Create a class that extends Thread and override run()

        System.out.println();
        // member inner class needs enclosing class in order to be called
        BasicThreadExp exp = new BasicThreadExp();
        exp.new SampleThread().start();

        // static inner class does not need enclosing class instance
        // A Thread can be run by calling start on class extending Thread
        // or by passing the class to the Thread Constructor and then calling start()
        new StaticSampleThread().start();
        new Thread(new StaticSampleThread()).start();
    }
}
