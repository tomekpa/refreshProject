package vavr;

import com.google.common.collect.ImmutableList;
import io.vavr.control.Try;
import org.apache.kafka.common.errors.SerializationException;
import org.junit.Test;

import java.util.List;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.instanceOf;
import static org.junit.Assert.assertEquals;

public class VavrTryTest {

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
