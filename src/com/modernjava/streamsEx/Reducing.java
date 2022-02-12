package com.modernjava.streamsEx;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Reducing {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 5, 6, 2, 20, 100, 5);
        int num = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(num);

        int sum = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum);

        int max = numbers.stream().reduce(0, (a, b) -> Integer.max(a, b));
        System.out.println(max);

        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        min.ifPresent(System.out::println);

        int calories = getDish().stream().map(Dish::getCalories).reduce(0, Integer::sum);
        System.out.println(calories);
    }

    public static List<Dish> getDish()
    {
        return Dish.dishList();
    }
}
