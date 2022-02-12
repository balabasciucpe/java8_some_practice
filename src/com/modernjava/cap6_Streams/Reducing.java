package com.modernjava.cap6_Streams;

import static java.util.stream.Collectors.reducing;

public class Reducing {

    public static void main(String[] args) {

        System.out.println(calculateTotalCalories());
        System.out.println(calculateTotalCaloriesWithMethodReference());
        System.out.println(calculateTotalCaloriesWithMap());
        System.out.println(calculateTotalCaloriesWithoutAutomaticBoxing());

    }

    private static int calculateTotalCalories()
    {
        return Dish.menu.stream().collect(reducing(0, Dish::getCalories, (Integer i, Integer j) -> i + j));
    }

    private static int calculateTotalCaloriesWithMethodReference()
    {
        return Dish.menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
    }

    private static int calculateTotalCaloriesWithMap()
    {
        return Dish.menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
    }

    private static int calculateTotalCaloriesWithoutAutomaticBoxing()
    {
        return Dish.menu.stream().mapToInt(Dish::getCalories).sum();
    }
}
