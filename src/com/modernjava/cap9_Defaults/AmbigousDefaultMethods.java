package com.modernjava.cap9_Defaults;

public class AmbigousDefaultMethods {

    public static void main(String[] args) {

        new C().hello();


    }

    static interface A
    {
        public default void hello()
        {
            System.out.println("Hello from A");
        }
    }

    static  interface B
    {
        default void hello()
        {
            System.out.println("Hello from B");
        }
    }

    static class C implements B, A
    {
        @Override
        public void hello() {
            A.super.hello();
            B.super.hello();
        }
    }
}
