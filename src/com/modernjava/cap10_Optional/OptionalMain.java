package com.modernjava.cap10_Optional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public class OptionalMain {

    public static void main(String[] args) {

        sum(Optional.ofNullable(null), Optional.of(10));


        Optional<Integer> opt1 = Optional.of(5);
        Optional<Integer> opt2 = opt1.or(() -> Optional.of(4));

        System.out.println(of(5).or(() -> of(4)));

        System.out.println(max(of(3), of(5)));
        System.out.println(max(empty(), of(5)));

        Insurance insurance = new Insurance("abc");
        Optional<Insurance> optionalInsurance = Optional.of(insurance);
        Optional<String> name = optionalInsurance.map(Insurance::getName);
        System.out.println(name);


    }

    public static final Optional<Integer> max(Optional<Integer> i, Optional<Integer> j)
    {
        return i.flatMap(a -> j.map(b -> Math.max(a, b)));
    }


    public static Integer sum(Optional<Integer> a, Optional<Integer> b) {
        //Optional.isPresent - checks the value is present or not

        System.out.println("First parameter is present: " + a.isPresent());
        System.out.println("Second parameter is present: " + b.isPresent());

        //Optional.orElse - returns the value if present otherwise returns
        //the default value passed.
        Integer value1 = a.orElse(0);

        //Optional.get - gets the value, value should be present
        Integer value2 = b.get();
        System.out.println(value1 + value2);
        return 0;
    }
}
