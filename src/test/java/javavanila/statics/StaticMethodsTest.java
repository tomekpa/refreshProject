package javavanila.statics;

import org.junit.Test;

public class StaticMethodsTest {

    @Test
    public void shouldExecuteStaticMehtod() {
        someStaticMethod();
        someStaticMethod();
    }

    public static void someStaticMethod() {
        Object o = new Object();
        System.out.println(o.toString());
    }

}
