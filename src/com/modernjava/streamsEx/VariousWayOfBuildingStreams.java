package com.modernjava.streamsEx;

import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class VariousWayOfBuildingStreams {

    public static void main(String[] args) {

        //Stream of
        Stream<String> stream = Stream.of("test", "debugging");
        stream.map(String::toUpperCase).forEach(System.out::println);

        //Stream empty
        Stream<String> emptryStream = Stream.empty();

        //Arrays Stream
        int[] numbers = {1, 2, 10, 33, 100};
        IntStream integerStream = Arrays.stream(numbers);
        System.out.println(integerStream.sum());

        Stream.iterate(0, n -> n+2)
                .limit(10)
                .forEach(System.out::println);

        //fibonacci iterate
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .forEach(t -> System.out.println("( " + t[0] + ", " + t[1] + "}"));

        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(30)
                .map(t -> t[0])
                .forEach(System.out::println);

        //random Stream Elements generated
        Stream.generate(Math::random)
                .limit(40)
                .forEach(System.out::println);

        IntStream.generate(() -> 1)
            .limit(10)
                .forEach(System.out::println);

        IntStream.generate(new IntSupplier() {
            @Override
            public int getAsInt() {
                return 2;
            }
        });

        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;
            @Override
            public int getAsInt() {
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return this.previous;
            }
        };

        IntStream.generate(fib).limit(10).forEach(System.out::println);





    }
}
