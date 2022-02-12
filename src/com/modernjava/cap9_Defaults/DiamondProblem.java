package com.modernjava.cap9_Defaults;

public class DiamondProblem {

    public static void main(String[] args) {

        new D().hello();

    }

    static interface A
    {
        public default void hello()
        {
            System.out.println("Hello from A");
        }
    }

    static interface B extends A {
        @Override
        default void hello() {
            System.out.println("Hello from B");
        }
    }
    static interface C extends B {}

    static class D implements B, C
    {
    }
}
