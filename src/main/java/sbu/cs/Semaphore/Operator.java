package sbu.cs.Semaphore;

import java.time.LocalTime;
import java.util.concurrent.Semaphore;

public class Operator extends Thread {

    Semaphore semaphore;

    public Operator(String name, Semaphore semaphore) {
        super(name);
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                semaphore.acquire();
                Resource.accessResource();         // critical section - a Maximum of 2 operators can access the resource concurrently
                semaphore.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
