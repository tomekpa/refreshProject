package javavanila;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SimpleCommonTest {

    @Test
    public void name() throws Exception {
        int test;

        synchronized (SimpleCommonTest.class) {
            System.out.printf("sdfdsfds");
        }

     }
}
