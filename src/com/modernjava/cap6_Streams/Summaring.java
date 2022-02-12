package com.modernjava.cap6_Streams;

import java.util.Comparator;
import java.util.function.BinaryOperator;

import static java.util.stream.Collectors.*;

public class Summaring {

    public static void main(String[] args) {

        System.out.println(howManyDishes());
        System.out.println(findMostCaloricDish());
        System.out.println(findMostCaloricDishUsingComparator());
        System.out.println(calculatingCalories());
        System.out.println(calculatingCaloriesWithMap());
        System.out.println(calculatingAverageCalories());
        System.out.println(getShortMenu());
        System.out.println(getShorMenuCommaSeparated());

    }

    private static long howManyDishes()
    {
        return Dish.menu.stream().collect(counting()); //count, Collection.count
    }

    private static Dish findMostCaloricDish()
    {
        return Dish.menu.stream().collect(reducing((Dish d1, Dish d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)).get();
    }

    private static Dish findMostCaloricDishUsingComparator()
    {
        Comparator<Dish> comparatorCaloriesForDish = Comparator.comparingInt(Dish::getCalories);
        BinaryOperator<Dish> moreCaloricOf = BinaryOperator.maxBy(comparatorCaloriesForDish);
        return Dish.menu.stream().collect(reducing(moreCaloricOf)).get();
    }

    private static int calculatingCalories()
    {
        return Dish.menu.stream().collect(summingInt(Dish::getCalories));
    }

    private static int calculatingCaloriesWithMap()
    {
        return Dish.menu.stream().mapToInt(Dish::getCalories).sum();
    }

    private static double calculatingAverageCalories()
    {
        return Dish.menu.stream().collect(averagingDouble(Dish::getCalories));
    }

    private static String getShortMenu()
    {
        return Dish.menu.stream().map(Dish::getName).collect(joining());
    }

    private static String getShorMenuCommaSeparated()
    {
        return Dish.menu.stream().map(Dish::getName).collect(joining(", "));
    }


}
