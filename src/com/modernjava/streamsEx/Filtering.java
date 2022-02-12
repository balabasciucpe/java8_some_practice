package com.modernjava.streamsEx;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Filtering {

    public static void main(String[] args) {

        List<Dish> dishes = Dish.dishList();

        //using Predicate to Filter
        List<Dish> dishList = dishes.stream().filter(Dish::isVegetarian).collect(Collectors.toList());
        dishList.forEach(System.out::println);

        //unique Elements
        List<Integer> numberList = Arrays.asList(1, 5, 5, 3, 2, 5, 1);
        numberList.stream().filter(i -> i%2 ==0)
                .distinct().forEach(System.out::println);

        //Truncating a Stream
        List<Dish> dishListTruncated = dishes.stream().filter(dish -> dish.getCalories() < 300).limit(3).collect(Collectors.toList());
        dishListTruncated.forEach(System.out::println);

        //Skipping Elements
        List<Dish> skippedDishes = dishes.stream().filter(dish -> dish.isVegetarian()).skip(1).collect(Collectors.toList());
        skippedDishes.forEach(System.out::println);
    }
}
