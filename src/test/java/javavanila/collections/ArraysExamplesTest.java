package javavanila.collections;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class ArraysExamplesTest {

    @Test
    public void initSomeArray() throws Exception {
        int[] tab = new int[]{3, 4, 5};
        System.out.println(tab.length);
        System.out.println(tab.toString());
        System.out.println(Arrays.toString(tab));
    }

    @Test
    public void initSomeList() throws Exception {
        List<Integer> of = new ArrayList<Integer>();
        of.add(1);
        of.add(2);
        of.add(3);
    }

    @Test
    public void name() throws Exception {

        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        for (int i = 0; i < 10; i++) {
            bout.write((byte) (Math.random() * 128));
        }
        byte[] byteArray = bout.toByteArray();
        for (byte b : byteArray)
            System.out.print(b + " ");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowExceptionWhenUnmodifiable() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("first");
        List<String> uList = Collections.unmodifiableList(list);
        uList.add("second");
    }

}