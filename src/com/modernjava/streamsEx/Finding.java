package com.modernjava.streamsEx;

import java.util.List;
import java.util.Optional;

public class Finding {

    public static void main(String[] args) {

    if(isVegetarianFriendlyMenu())
    {
        System.out.println("Is vegetarian");
    }
        System.out.println(isHealtyMenu());

        Optional<Dish> dish = findVegetarianDish();
        dish.ifPresent(System.out::println);
    }

    private static boolean isVegetarianFriendlyMenu()
    {
        return getDish().stream().anyMatch(Dish::isVegetarian);
    }

    private static boolean isHealtyMenu()
    {
        return getDish().stream().allMatch(dish -> dish.getCalories() < 500);
    }

    private static Optional<Dish> findVegetarianDish()
    {
        return getDish().stream().filter(Dish::isVegetarian).findAny();
    }

    private static List<Dish> getDish()
    {
        return Dish.dishList();
    }
}
