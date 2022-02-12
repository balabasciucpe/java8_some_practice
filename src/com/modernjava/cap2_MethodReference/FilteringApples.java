package com.modernjava.cap2_MethodReference;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FilteringApples {

    public static void main(String[] args) {

        List<Apple> appleList = Arrays.asList(new Apple(60, "Red"), new Apple(50, "green"),
                new Apple(30, "red"));

        List<Apple> redApples = filterBasic(appleList);
        System.out.println(redApples);

        List<Apple> greenApplesByColor = filterApplesByColor(appleList, "green");
        System.out.println(greenApplesByColor);

        List<Apple> applesByWeight= filterApples(appleList, 50);
        System.out.println(applesByWeight);

        List<Apple> listByPredicate = filterApplesByApplePredicate(appleList, new AppleColorAndWeightPredicate());
        System.out.println(listByPredicate);
    }

    public static List<Apple> filterBasic(List<Apple> appleList)
    {
        List<Apple> apples = new ArrayList<>();
        for(Apple apple : appleList)
        {
            if(apple.getColor().equalsIgnoreCase("red"))
            {
                apples.add(apple);
            }
        }

        return apples;
    }
    public static List<Apple> filterApplesByApplePredicate(List<Apple> appleList, ApplePredicate applePredicate)
    {
        List<Apple> apples = new ArrayList<>();
        for(Apple apple : appleList)
        {
            if(applePredicate.test(apple))
            {
                apples.add(apple);
            }
        }

        return apples;
    }

    public static List<Apple> filterApplesByColor(List<Apple> appleList, String color)
    {
        List<Apple> apples = new ArrayList<>();
        for(Apple apple : appleList)
        {
            if(apple.getColor().equalsIgnoreCase(color))
            {
                apples.add(apple);
            }
        }

        return apples;
    }

    public static List<Apple> filterApples(List<Apple> appleList, int weight)
    {
        List<Apple> filteredApples = new ArrayList<>();
        for(Apple apple : appleList)
        {
            if(apple.getWeight() > weight)
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

    public Apple(int weight, String color) {
        this.weight = weight;
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
    interface ApplePredicate
    {
        public boolean test(Apple apple);
    }

    public static class AppleWeightPredicate implements ApplePredicate
    {
        @Override
        public boolean test(Apple apple) {
            return apple.getWeight() > 50;
        }
    }

    public static class AppleColorPredicate implements ApplePredicate
    {
        @Override
        public boolean test(Apple apple) {
            return apple.getColor().equalsIgnoreCase("RED");
        }
    }

    public static class AppleColorAndWeightPredicate implements ApplePredicate
    {
        @Override
        public boolean test(Apple apple) {
            return apple.getWeight() > 50 || apple.getColor().equalsIgnoreCase("red");
        }
    }


}
