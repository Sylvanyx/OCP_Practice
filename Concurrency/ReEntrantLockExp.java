package Concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * ReentrantLock interface provides 4 methods
 * 
 * lock() / unlock()
 * tryLock() / trylock(long,TimeUnit)
 * 
 * duplicate lock request will make the lock unavailable to other threads, unlock must be called the same amt as lock
 */
public class ReEntrantLockExp {
    static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        try {
            lock.lock();
            counter++;
            System.out.println(counter + " work done");
        } finally {
            lock.unlock();
        }

        if (lock.tryLock()) {
            try {
                counter++;
                System.out.println(counter + " work done");
            } finally {
                lock.unlock();
            }
            // protected code
        } else {
            System.out.println("no lock available");
        }

        if (lock.tryLock(10, TimeUnit.SECONDS)) {
            try {
                counter++;
                System.out.println(counter + " work done");
            } finally {
                lock.unlock();
            }
            // protected code
        } else {
            System.out.println("no lock available");
        }

    }

}
