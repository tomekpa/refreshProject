package vavr;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.junit.Test;

public class ToupleTest {

    @Test
    public void shouldTest() {

        Tuple2<String, Integer> java8 = Tuple.of("Java", 8);
        String s = java8._1;
        Integer i = java8._2;

        System.out.println(s);
        System.out.println(i);

    }
}


