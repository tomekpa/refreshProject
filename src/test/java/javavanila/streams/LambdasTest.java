package javavanila.streams;

import org.junit.Test;

import java.util.function.Supplier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class LambdasTest {

    @Test
    public void shouldProvideSimpleSupplier() {
        Supplier<String> supplier = () -> "AAA";
        String str = supplier.get();
        assertThat(str, is("AAA"));
    }

    @Test
    public void shouldRunAMethodWithSupplier() {
        String str = processBehaviour(
                () -> "AAA",
                () -> "BBB"
        );
        assertThat(str, is("AAABBB"));
    }

    String processBehaviour(Supplier<String> supplier1, Supplier<String> supplier2){
        return supplier1.get() + supplier2.get();
    }
}

