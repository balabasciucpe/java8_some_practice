package com.modernjava.cap8_Streams;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class FactoryLambda {

    public static void main(String[] args) {

        //classic way
       Product p1 = ProductFactory.createProduct("loan");

        Supplier<Product> loanSupplier = Loan::new;
        Product p2 = loanSupplier.get();

        Product p3 = ProductFactory.createProductWithLambda("bond");


    }

    private static class ProductFactory
    {
        public static Product createProduct(String name){
        switch(name)
        {
            case "loan": return new Loan();
            case "stock": return new Stock();
            case "bond": return new Bond();
            default: throw new UnsupportedOperationException("No such product: " + name);
        }
    }

    public static Product createProductWithLambda(String name)
    {
        Supplier<Product> product = map.get(name);
        if(product != null) return product.get();
        throw new UnsupportedOperationException("No such product: " + name);
    }}

    static private interface Product {}
    static private class Loan implements Product{
        public Loan() {
            System.out.println("Working");
        }
    }
    static private class Stock implements Product {}
    static private class Bond implements Product {}

    final static Map<String, Supplier<Product>> map = new HashMap<>();
    static
    {
        map.put("loan", Loan::new);
        map.put("stock", Stock::new);
        map.put("bond", Bond::new);

    }

}


