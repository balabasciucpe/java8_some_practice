package com.modernjava.cap6_Streams;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.*;


public class Grouping {

    enum CaloricLevel { DIET, NORMAL, FAT }

    public static void main(String[] args) {

        System.out.println("Grouping By Dish Type: " + groupingByDish());
        System.out.println("Grouping By Dish Name: " + groupingByDishName());
        System.out.println("Group Caloric Dishes By Type: " + groupCaloricDishesByType());

        System.out.println("Grouping DishTags by Type " + groupingDishTagsByType());
        System.out.println("Grouping Dish By Caloric Level " + groupingDishByCaloricLevel());
        System.out.println("Grouping Dishes by Type and Caloric Level: " + groupingDisheshByTypeAndCaloricLevel());

        System.out.println("Count Dishes Type: " + countDishesType());
        System.out.println("Most caloric Dishes by Type" + mostCaloricDishesByType());
        System.out.println("Most caloric Dishes By Type with using Optionals " + mostCaloricDishesByTypeWithOptionals());

        System.out.println("Sum calories by Type: " + sumCaloriesByType());
        System.out.println("Caloric level by Type: " + caloricLevelByType());

    }

    private static Map<Dish.Type, List<Dish>> groupingByDish()
    {
        return getMenu().stream().collect(groupingBy(Dish::getType));
    }

    private static Map<Dish.Type, List<String>> groupingByDishName()
    {
        return getMenu().stream().collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));
    }

    private static Map<Dish.Type, List<Dish>> groupCaloricDishesByType()
    {
        return getMenu().stream().collect(groupingBy(Dish::getType, filtering(dish -> dish.getCalories() > 500, toList())));
    }

    private static Map<Dish.Type, Set<String>> groupingDishTagsByType()
    {
        return getMenu().stream().collect(groupingBy(Dish::getType, flatMapping(dish -> Dish.dishTags.get(dish.getName()).stream(), toSet())));
    }

    private static Map<CaloricLevel, List<Dish>> groupingDishByCaloricLevel()
    {
        return getMenu().stream().collect(
                groupingBy(dish ->
                {
                    if(dish.getCalories() <= 400)
                        return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700)
                        return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                }));}

     private static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupingDisheshByTypeAndCaloricLevel()
     {
         return getMenu().stream().collect(
                 groupingBy(Dish::getType,
                         groupingBy( dish ->
                         {
                             if(dish.getCalories() <= 400)
                                 return CaloricLevel.DIET;
                             else if(dish.getCalories() <= 700)
                                 return CaloricLevel.NORMAL;
                             else
                                 return CaloricLevel.FAT;
                         })));
     }

     private static Map<Dish.Type, Long> countDishesType()
    {
        return getMenu().stream().collect(groupingBy(Dish::getType, counting()));
    }

    private static Map<Dish.Type, Optional<Dish>> mostCaloricDishesByType()
    {
        return getMenu().stream().collect(groupingBy(Dish::getType, reducing((Dish d1, Dish d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)));
    }

    private static Map<Dish.Type, Dish> mostCaloricDishesByTypeWithOptionals()
    {
        return getMenu().stream().collect(groupingBy(Dish::getType, collectingAndThen(
                reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2), Optional::get
        )));
    }

    private static Map<Dish.Type, Integer> sumCaloriesByType()
    {
        return getMenu().stream().collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
    }

    private static Map<Dish.Type, Set<CaloricLevel>> caloricLevelByType()
    {
        return getMenu().stream().collect(groupingBy(Dish::getType, mapping(dish ->
        {
            if(dish.getCalories() <= 400)
                return CaloricLevel.DIET;
            else if(dish.getCalories() <= 700)
                return CaloricLevel.NORMAL;
            else
                return CaloricLevel.FAT;},
                toSet())));
    }

    private static List<Dish> getMenu()
    {
        return Dish.menu;
    }
}
