package sbu.cs.CalculatePi;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class PiCalculator {

    /**
     * Calculate pi and represent it as a BigDecimal object with the given floating point number (digits after . )
     * There are several algorithms designed for calculating pi, it's up to you to decide which one to implement.
     * Experiment with different algorithms to find accurate results.
     * <p>
     * You must design a multithreaded program to calculate pi. Creating a thread pool is recommended.
     * Create as many classes and threads as you need.
     * Your code must pass all of the test cases provided in the test folder.
     *
     * @param floatingPoint the exact number of digits after the floating point
     * @return pi in string format (the string representation of the BigDecimal object)
     */

    public static String calculate(int floatingPoint) {
        AtomicReference<BigDecimal> result = new AtomicReference<>(new BigDecimal("0"));
        ExecutorService myFThreadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i <= 100; ++i) {
            final int index = i;
            myFThreadPool.execute(() -> {
                result.updateAndGet(current -> current.add(partialCalculation(index * 1000 + 1)));
            });
        }
        myFThreadPool.shutdown();
        try {
            myFThreadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result.get().toString();
    }

    private static BigDecimal partialCalculation(int start)
    {
        BigDecimal bigDecimal = new BigDecimal("0");
        for(int i =start;i<=start+1000;++i)
        {
            bigDecimal = bigDecimal.add(new BigDecimal((i%2==1? "" : "-")+ String.valueOf((double) 4/(2*i-1))));
        }
        return bigDecimal;
    }

    public static void main(String[] args) {
        System.out.println(calculate(1));
        // Use the main function to test the code yourself
    }

}
