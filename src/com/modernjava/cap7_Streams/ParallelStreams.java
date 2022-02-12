package com.modernjava.cap7_Streams;

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelStreams {

    public static long iterativSum(long n)
    {
        long result = 0;
        for(int i = 0; i <= n; i++)
        {
            result +=i;
        }
        return result;
    }

    public static long seqventialDeclarativeSum(long n)
    {
        return Stream.iterate(1L,  i -> i + 1).limit(n).reduce(Long::sum).get();
    }

    public static long parallelStream(long n)
    {
        return Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(Long::sum).get();
    }

    public static long rangedSum(long n)
    {
        return LongStream.rangeClosed(1, n).reduce(Long::sum).getAsLong();
    }

    public static long parallelRangedSum(long n)
    {
        return LongStream.rangeClosed(1, n).parallel().reduce(Long::sum).getAsLong();
    }

    public static long sideEffectSum(long n)
    {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }
    public static long sideEffectParallelStream(long n)
    {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }

    public static class Accumulator
    {
        private long total = 0;

        public void add(long suma)
        {
            this.total += suma;
        }
    }


}
