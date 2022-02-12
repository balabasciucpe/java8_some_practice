package com.modernjava.streamsEx;

import java.text.CollationElementIterator;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Mapping {

    public static void main(String[] args) {
        List<Dish> dishes = Dish.dishList();

        List<String> dishName = dishes.stream().map(Dish::getName).collect(toList());
        System.out.println(dishName);

        List<String> words = Arrays.asList("HELLO", "WORLD", "JAVA");
        List<String> wordsStreamMap = words.stream().map(String::toLowerCase).collect(toList());
        System.out.println(wordsStreamMap);

        words.stream().flatMap((String line) -> Arrays.stream(line.split("")))
                .distinct().forEach(System.out::println);

        List<Integer> number1 = Arrays.asList(1,2,3,4,5);
        List<Integer> numero2 = Arrays.asList(6,7,8);

        // flatMap
        List<Integer> numbers1 = Arrays.asList(1,2,3,4,5);
        List<Integer> numbers2 = Arrays.asList(6,7,8);
        List<int[]> pairs =
                numbers1.stream()
                        .flatMap((Integer i) -> numbers2.stream()
                                .map((Integer j) -> new int[]{i, j})
                        )
                        .filter(pair -> (pair[0] + pair[1]) % 3 == 0)
                        .collect(toList());
        pairs.forEach(pair -> System.out.println("(" + pair[0] + ", " + pair[1] + ")"));

    }
}
