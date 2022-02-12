package com.modernjava.streamsEx;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class NumericStreams {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 5, 7, 2, 1, 6);
        Arrays.stream(numbers.toArray()).forEach(System.out::println);

        List<Dish> dishes = getDish();
        int calories = dishes.stream().mapToInt(Dish::getCalories).sum();
        System.out.println("We having next number of calories: " + calories);

        OptionalInt maxCalories = dishes.stream().mapToInt(Dish::getCalories).max();

        int max;
        if(maxCalories.isPresent())
        {
            max = maxCalories.getAsInt();
        } else max = 1;
        System.out.println(max);

        IntStream evenNumbers = IntStream.rangeClosed(1, 100).filter(i -> i % 2 ==0);
        System.out.println(evenNumbers.count());



    }

    private static List<Dish> getDish()
    {
        return Dish.dishList();
    }
}
