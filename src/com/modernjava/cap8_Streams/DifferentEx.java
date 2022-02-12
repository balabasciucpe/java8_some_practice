package com.modernjava.cap8_Streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DifferentEx {

    public static void main(String[] args) {


    List<Integer> result = Stream.of(2, 5, 40, 2, 1, 5, 2)
            .peek(x -> System.out.println("Taking from stream: " + x)).map(x -> x + 100)
            .peek(x -> System.out.println("after map x is: " + x)).filter(x -> x % 2 ==0)
            .peek(x -> System.out.println("After filter x is: " + x )).limit(3)
            .peek(x -> System.out.println("After limit we have next numbers: ")).collect(Collectors.toList());

    List<Point> points = Arrays.asList(new Point(10, 23), null);
    points.stream().map(Point::getX).forEach(System.out::println);
}

    private static class Point
    {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }
    }
}
