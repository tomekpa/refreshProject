package vavr;

import io.vavr.Lazy;
import org.junit.Test;

public class LazyClassTest {

    @Test
    public void checkLazyClass() {
        Lazy<Double> lazy = Lazy.of(Math::random);
        System.out.println(lazy.isEvaluated()); // = false
        System.out.println(lazy.get());         // = 0.123 (random generated)
        System.out.println(lazy.isEvaluated()); // = true
        System.out.println(lazy.get());         // = 0.123 (memoized)
    }

    @Test
    public void checkLazyClass2() {
        CharSequence chars1 = Lazy.val(() -> stringProvider("Yay1!"), CharSequence.class);
        System.out.println(chars1);
        System.out.println(chars1);
        System.out.println(chars1);
        System.out.println(chars1);
        //Initialising
        //Yay1!
        //Yay1!
        //Yay1!
        //Yay1!
    }

    private String stringProvider(String string) {
        System.out.println("Initialising");
        return string;
    }
}