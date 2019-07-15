package vavr;

import com.google.common.collect.ImmutableList;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.apache.kafka.common.errors.SerializationException;
import org.junit.Test;

import java.util.List;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.instanceOf;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;

public class VavrTryTest {

    VavrFun vavrFun = new VavrFun();

    @Test(expected = ArithmeticException.class)
    public void shouldReturnTheResultButFailWhenRetrieve() {
        //when
        Try<Integer> divide = vavrFun.divide(1, 0);
        //then
        assertNotNull(divide);
        //and
        Integer integer = divide.get();
        //then
        fail();
    }

    @Test
    public void shouldTryWithFailure() {
        //when
        Try<Integer> divide = vavrFun.divide(1, 0);
        //then
        Option<Integer> divisionResult = divide
                .onSuccess(i -> System.out.println("onSuccess:" + i))
                .onFailure(t -> System.out.println("onFailure:" + t))
                .andFinally(() -> System.out.println("andFinally"))
//                .recover(t -> {
//                    System.out.println("recover:" + t);
//                    return 0;
//                }).
                .toOption();

        Integer result = divisionResult.getOrElse(0);
        System.out.println(result);
        //TODO assert long long
    }

    @Test
    public void shouldTryWithRecoverForFailure() {
        //when
        Try<Integer> divide = vavrFun.divide(1, 0);
        //then
        Try<Integer> andFinally = divide
                .onSuccess(i -> System.out.println("onSuccess:" + i))
                .onFailure(t -> System.out.println("onFailure:" + t))
                .andFinally(() -> System.out.println("andFinally"))
                .recover(t -> {
                    System.out.println("recover:" + t);
                    return 0;
                });

        Integer integer = andFinally.get();
        //TODO assert long long
//        assertEquals(integer, 0);
        assertTrue(integer == 0);

    }

    @Test
    public void shouldTryWithRecoverForSuccess() {
        //when
        Try<Integer> divide = vavrFun.divide(36, 3);
        //then
        Try<Integer> andFinally = divide
                .onSuccess(i -> System.out.println("onSuccess:" + i))
                .onFailure(t -> System.out.println("onFailure:" + t))
                .andFinally(() -> System.out.println("andFinally"))
                .recover(t -> {
                    System.out.println("recover:" + t);
                    return 0;
                });
        Integer integer = andFinally.get();
        //TODO assert long long
        assertTrue(integer == 12);
    }

    @Test
    public void shouldTryWithSuccess() {
        //when
        Try<Integer> divide = vavrFun.divide(44, 4);
        //then
        divide
                .onSuccess(i -> System.out.println("onSuccess:" + i))
                .onFailure(t -> System.out.println("onFailure:" + t))
                .andFinally(() -> System.out.println("andFinally"));

    }

    @Test
    public void checkHowTryWorks() {

        List<Object[]> tests = ImmutableList.of(
                new Object[]{new IllegalStateException(), "illegalStateExceptionHandler"},
                new Object[]{new IndexOutOfBoundsException(), "indexOutOfBoundsExceptionHandler"},
                new Object[]{new SerializationException(), "Did not catch any handled exception"}
        );

        tests.forEach(test -> {
            Exception e = (Exception) test[0];
            String expected = (String) test[1];
            String tried = tryNewThings(e);
            assertEquals(tried, expected);
        });
    }

    private String tryNewThings(Exception e) {
        return Try.of(() -> mayResultInSomeException(e))
                .recover(x -> Match(x).of(
                        Case($(instanceOf(IllegalStateException.class)), this::illegalStateExceptionHandler),
                        Case($(instanceOf(IndexOutOfBoundsException.class)), this::indexOutOfBoundsExceptionHandler)
                ))
                .getOrElse("Did not catch any handled exception");
    }

    private String mayResultInSomeException(Exception e) throws Exception {
        if (true) {
            throw e;
        }
        return "did not throw exception";
    }

    private String illegalStateExceptionHandler(RuntimeException e) {
        return "illegalStateExceptionHandler";
    }

    private String indexOutOfBoundsExceptionHandler(RuntimeException e) {
        return "indexOutOfBoundsExceptionHandler";
    }
}
