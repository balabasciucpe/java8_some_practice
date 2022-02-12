package com.modernjava.cap10_Optional;
import org.junit.*;

import java.util.Optional;
import java.util.Properties;
import static org.junit.Assert.assertEquals;

public class ReadPositiveIntParam {

    @Test
    public void testMap()
    {
        Properties prop = new Properties();
        prop.setProperty("a", "5");
        prop.setProperty("b", "true");
        prop.setProperty("c", "-3");

        assertEquals(5, readDurationImperative(prop, "a"));
        assertEquals(0, readDurationImperative(prop, "b"));
        assertEquals(0, readDurationImperative(prop, "c"));
        assertEquals(0, readDurationImperative(prop, "d"));

        assertEquals(5, readDurationWithOptional(prop, "a"));
        assertEquals(0, readDurationWithOptional(prop, "b"));
        assertEquals(0, readDurationWithOptional(prop, "c"));
        assertEquals(0, readDurationWithOptional(prop, "d"));

    }

    public static int readDurationImperative(Properties properties, String name) {
        String value = properties.getProperty(name);
        if (value != null) {
            try {
                int i = Integer.parseInt(value);
                if (i > 0) return i;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public static int readDurationWithOptional(Properties properties, String name)
    {
        return Optional.ofNullable(properties.getProperty(name))
                .flatMap(ReadPositiveIntParam::stringToInt).filter(i -> i > 0).orElse(0);
    }

    public static Optional<Integer> stringToInt(String number)
    {
        try
        {
            return Optional.of(Integer.parseInt(number));
        }
        catch (NumberFormatException e) { return Optional.empty(); }
    }

}
