package sbu.cs.Semaphore;

import java.time.LocalTime;

public class Resource {

    public static void accessResource() {
        try {
            // The reason that I put the log here was preventing confusion when thread switch occurs before println,
            // in the log you can see not more than 2 are acquiring the semaphore at while
            System.out.println(Thread.currentThread().getName() + " acquired sema at "+ LocalTime.now());
            Thread.sleep(100);
            System.out.println(Thread.currentThread().getName() + " released sema at "+ LocalTime.now());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
