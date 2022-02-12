package com.modernjava.cap6_Streams;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.*;

public class Partioning {

    public static void main(String[] args) {

        System.out.println(partitionByVegetarianDish());
        System.out.println(partitioningByDishTipeAndVegetarian());
        System.out.println(mostCaloricDishPartitionedByBeingVegetarian());
    }

    private static Map<Boolean, List<Dish>> partitionByVegetarianDish()
    {
        return Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian));
    }

    private static Map<Boolean, Map<Dish.Type, List<Dish>>> partitioningByDishTipeAndVegetarian()
    {
        return Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
    }

    private static Object mostCaloricDishPartitionedByBeingVegetarian()
    {
        return Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian, collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
    }
}
