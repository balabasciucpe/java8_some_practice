package com.modernjava.cap9_Defaults;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class VariousEx {

    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(2, 5, 3, 1, 10, 5);
        integerList.sort(Comparator.naturalOrder());
        System.out.println(integerList);


        Function<String, String> addHeader = (String s) -> VariousEx.addHeader("love");
        Function<String, String> transformationPipeLine
                = addHeader.andThen(VariousEx::checkSpelling);

        System.out.println(transformationPipeLine.apply("\nC++ stay away!"));
    }

    public static String addHeader(String text)
    {
        return "\nFrom petru with: " + text;
    }


    public static String checkSpelling(String text)
    {
        return text.replaceAll("C\\+\\+", "Censored");
    }
}
