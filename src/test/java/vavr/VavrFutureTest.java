package vavr;

import io.vavr.concurrent.Future;
import org.junit.Test;

public class VavrFutureTest {

    @Test
    public void shouldTest() {
        Future<String> future = Future.of(() -> getMeAString("asd"));
        int counter = 0;
        while (true) {
            if (!future.isCompleted()) {
                counter++;
                System.out.println(counter);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                break;
            }
        }
        System.out.println("Im done");
    }

    @Test
    public void shouldTest2() {
        Future<String> future = Future.of(() -> getMeAString("asd"));
        future.onComplete(s -> System.out.printf("Im done with result:" + s));

        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Im done with result:Success(asd)
    }

    @Test
    public void shouldTest3() {
        Future<String> future = Future.of(() -> getMeAStringFail("asd"));
        future.onComplete(s -> System.out.printf("Im done with result:" + s));

        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//      Im done with result:Failure(java.lang.RuntimeException: im unexpected)
    }

    private String getMeAString(String string) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return string;
    }

    private String getMeAStringFail(String string) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (true) {
            throw new RuntimeException("im unexpected");
        }
        return string;
    }
}