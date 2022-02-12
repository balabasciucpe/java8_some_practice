package com.modernjava.cap1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FilteringApples {

    public static void main(String[] args) {

    List<Apple> appleList = Arrays.asList(new Apple(2, "RED"), new Apple(50, "GREEN"));

    List<Apple> isGreenApple = filterApples(appleList);
        System.out.println(isGreenApple);
    List<Apple> isHeavyApple = filterHeavyApples(appleList);
        System.out.println(isHeavyApple);

    List<Apple> isGreenApplePredicate = filterApplesByPredicate(appleList, FilteringApples::isGreenApple);
        System.out.println(isGreenApplePredicate);
    List<Apple> isHeaavyApplePredicate = filterApplesByPredicate(appleList, FilteringApples::isHeavyApple);
        System.out.println(isHeaavyApplePredicate);

    List<Apple> isGreenAppleLambda = filterApplesByPredicate(appleList, apple -> apple.getColor().equalsIgnoreCase("green"));
        System.out.println(isGreenAppleLambda);
    List<Apple> isHeavyAppleLambda = filterApplesByPredicate(appleList, apple -> apple.getWeight() > 30);
        System.out.println(isHeavyAppleLambda);



    }




    public static boolean isHeavyApple(Apple apple)
    {
        return apple.getWeight() > 30;
    }

    public static boolean isGreenApple(Apple apple)
    {
        return "green".equalsIgnoreCase(apple.getColor());
    }

    public static List<Apple> filterHeavyApples(List<Apple> appleList)
    {
        List<Apple> heavyApples = new ArrayList<>();
        for(Apple apple : appleList)
        {
            if(apple.getWeight() > 30)
            {
                heavyApples.add(apple);
            }
        }
        return heavyApples;
    }

    public static List<Apple> filterApples(List<Apple> appleList)
    {
        List<Apple> filteredApples = new ArrayList<>();
        for(Apple apple : appleList)
        {
            if("green".equalsIgnoreCase(apple.getColor()))
            {
                filteredApples.add(apple);
            }
        }

        return filteredApples;
    }

    public static List<Apple> filterApplesByPredicate(List<Apple> appleList, Predicate<Apple> applePredicate)
    {
        List<Apple> filteredApples = new ArrayList<>();
        for(Apple apple : appleList)
        {
            if(applePredicate.test(apple))
            {
                filteredApples.add(apple);
            }
        }
        return filteredApples;
    }

    public static class Apple
    {
        private int weight;
        private String color;

        public Apple(int weight, String color)
        {
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return "Apple{" +
                    "weight=" + weight +
                    ", color='" + color + '\'' +
                    '}';
        }
    }


}
