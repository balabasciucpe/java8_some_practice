package com.modernjava.cap6_Streams;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.IntStream;

import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;
import static java.util.stream.Collectors.partitioningBy;

public class PartitionPrimeNumbers {

    public static void main(String[] args) {

        System.out.println(partitionPrime(100));
        System.out.println(partitionWithCustomComparator(100));
        System.out.println("Partitioning custom Collector done in: " + execute(PartitionPrimeNumbers::partitionWithCustomComparator));
        System.out.println("Partitioning done in: " + execute(PartitionPrimeNumbers::partitionPrime));
    }

    private static long execute(Consumer<Integer> primePartitioner) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i <= 10; i++)
        {
            long start = System.nanoTime();
            primePartitioner.accept(1_000_000);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if(duration < fastest)
                fastest = duration;
            System.out.println("Done in: " + duration);
        }
        return fastest;
    }




    public static Map<Boolean, List<Integer>> partitionPrime(int n)
    {
        return IntStream.rangeClosed(2, n).boxed().collect(partitioningBy(candidate -> isPrime(candidate)));
    }

    public static boolean isPrime(int candidate)
    {                                                           //returning largest integer less or equal than
        return IntStream.rangeClosed(2, candidate-1).limit((long)Math.floor(Math.sqrt((double) candidate))-1).noneMatch(i -> candidate % i == 0);
    }

    public static boolean isPrime(List<Integer> primes, Integer candidate)
    {
        double candidateRoot = Math.sqrt((double)candidate);
        return primes.stream().takeWhile(i -> i <= candidateRoot).noneMatch(i -> candidate % i == 0);
    }

    public static Map<Boolean, List<Integer>> partitionWithCustomComparator(int integer)
    {
        return IntStream.rangeClosed(2, integer).boxed().collect(new PrimeNumberCollectors());

    }

    public static class PrimeNumberCollectors implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>>
    {
        @Override
        public Supplier<Map<Boolean, List<Integer>>> supplier() {
            return () -> new HashMap<Boolean, List<Integer>>()
            {
                {
                    put(true, new ArrayList<Integer>());
                    put(false, new ArrayList<Integer>());
                }};
        }

        @Override
        public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
            return (Map<Boolean, List<Integer>> accumulator, Integer candidate) ->
            {
                accumulator.get(isPrime(accumulator.get(true), candidate)).add(candidate);
            };
        }

        @Override
        public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
            return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2)
            ->
            {
                map1.get(true).addAll(map2.get(true));
                map1.get(false).addAll(map2.get(false));
                return map1;
            };
        }

        @Override
        public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
            return i -> i;
        }

        @Override
        public Set<Characteristics> characteristics() {
           return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH));
        }
    }

}
