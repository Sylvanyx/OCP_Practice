package Concurrency;

/*
 * A moniter, or lock, is an object that supports mutual exclusion(two events cannot occur simultaneously)
 * synchronized makes any java object a moniter
 * 
 * 
 *  
 *  
 */
public class SynchronizedExp {
    private int counter = 0;

    static class inner {
        int count = 0;
    }

    public static synchronized void synchronizedMethod(SynchronizedExp s) {
        for (int i = 0; i < 5; i++)
            System.out.println(s.counter++);
    }

    public static void main(String[] args) {
        SynchronizedExp lock = new SynchronizedExp();

        // synchronized block
        synchronized (lock) {
            for (int i = 0; i < 5; i++)
                System.out.println(lock.counter++);
        }

        synchronizedMethod(lock);

    }
}
