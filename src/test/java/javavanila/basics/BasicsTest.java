package javavanila.basics;

import org.junit.Test;

import java.util.Arrays;

public class BasicsTest {

    //Warning: when pass array to function, defensive programming is required,
    //otherwise we make side effects
    @Test
    public void shouldUpdateStreamDataSource() {
        String[] s1 = new String[]{"one", "two"};
        System.out.println("s1 before:" + Arrays.toString(s1));
        String[] s2 = foo(s1);
        System.out.println("s1 after:" + Arrays.toString(s1));
        System.out.println("s2 after:" + Arrays.toString(s2));
    }

    private String[] foo(String[] arr) {
        String temp = arr[0];
        arr[0] = arr[1];
        arr[1] = temp;
        return arr;
    }
}
