package com.modernjava.streamsEx;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BasicStream {

    public static void main(String[] args) {


        List<String> names = Arrays.asList("testam", "vedem", "exersam");
        Stream<String> streamNames = names.stream();
        streamNames.forEach(System.out::println);

        List<Integer> numbers = Arrays.asList(1, 4, 6, 7, 10);
        List<Integer> twoEvenSquares =
                numbers.stream().filter(
                        i -> {
                            System.out.println("Filtering... " + i);
                            return i %2 ==0;
                        })
                .map(n ->
                {
                    System.out.println("mapping... " + n);
                    return n * n;
                })
                .limit(2)
                .collect(Collectors.toList());

        //java7
        getCaloricDishesOldWay(Dish.dishList()).forEach(System.out::println);

        //java8
        getLowCaloricDishes(Dish.dishList()).forEach(System.out::println);

    }

    public static List<String> getCaloricDishesOldWay(List<Dish> dishes)
    {
        List<Dish> caloricDishes = new ArrayList<>();
        for(Dish d : dishes)
        {
            if(d.getCalories() < 400)
            {
                caloricDishes.add(d);
            }
        }

        List<String> caloricDishesToSort = new ArrayList<>();
        Collections.sort(caloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(), o2.getCalories());
            }
        });

        for(Dish d: caloricDishes)
        {
            caloricDishesToSort.add(d.getName());
        }

        return caloricDishesToSort;
    }

    public static List<String> getLowCaloricDishes(List<Dish> dishList)
    {
        return dishList.stream()
                .filter(dish -> dish.getCalories() < 400)
                .sorted(Comparator.comparingInt(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
    }
}
