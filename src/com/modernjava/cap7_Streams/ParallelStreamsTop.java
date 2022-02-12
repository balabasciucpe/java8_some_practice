package com.modernjava.cap7_Streams;

import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;

public class ParallelStreamsTop {

    public static final ForkJoinPool FORK_JOIN_POOL = new ForkJoinPool();

    public static void main(String[] args) {

        System.out.println("Iterative sum done in: " + measurePerf(ParallelStreams::iterativSum, 10_000_000L));
        System.out.println("Seqvential sum done in: " + measurePerf(ParallelStreams::seqventialDeclarativeSum, 10_000_000L));
        System.out.println("Parallel forkJoinSum done in: " + measurePerf(ParallelStreams::parallelStream, 10_000_000L));
        System.out.println("Range forkJoinSum done in: " + measurePerf(ParallelStreams::rangedSum, 10_000_000L));
        System.out.println("Parallel range forkJoinSum done in: " + measurePerf(ParallelStreams::parallelRangedSum, 10_000_000L));
        System.out.println("SideEffectParallel sum done in : " + measurePerf(ParallelStreams::sideEffectParallelStream, 10_000_000L));
        System.out.println("SideEffect sum done in: " + measurePerf(ParallelStreams::sideEffectSum, 10_000_000L));

    }

    public static <T, R> long measurePerf(Function<T, R> f, T input)
    {
        long fastest = Long.MAX_VALUE;
        for(int i = 0; i < 10; i++)
        {
            long start = System.nanoTime();
            R result = f.apply(input);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + result);
            if(duration < fastest)
                fastest = duration;
        }

        return fastest;
    }
}
