package vavr;

import io.vavr.control.Option;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class VavrOptionTest {

    @Test
    public void shouldTest() {
        Option<Integer> integer1 = Option.of(5);
        Option<Integer> some = integer1.map(i -> i + 1);
        System.out.println(some.get());
    }

    /*
    Instances of Option are either an instance of Some or the None.
    In Optional, a call to .map that results in a null will result in an empty Optional.
    In Vavr, it would result in a Some(null)
    */
    @Test(expected = NullPointerException.class)
    public void checkSomeOfNull() {
        Option<String> maybeFoo = Option.of("foo");
        then(maybeFoo.get()).isEqualTo("foo");

        Option<String> maybeFooBar = maybeFoo
                .map(s -> (String) null)
                .map(String::toUpperCase);
        fail("None is not Some");
    }

    @Test(expected = NoSuchElementException.class)
    public void checkOptionOfNull() {
        Option<String> maybeFoo = Option.of(null);
        then(maybeFoo.get()).isEqualTo(null);
    }

    @Test
    public void checkOptionEmptyness() {
        Option<String> maybeFoo1 = Option.of(null);
        Option<String> maybeFoo2 = Option.of("NotNull");
        Option<String> map1 = maybeFoo1.map(String::toUpperCase);
        Option<String> flatMap1 = map1.flatMap(s -> Option.of(s.toUpperCase()));
        Option<String> map2 = maybeFoo2.map(String::toUpperCase);

        System.out.println("maybeFoo1:" + maybeFoo1.isEmpty());
        System.out.println("maybeFoo2:" + maybeFoo2.isEmpty());
        System.out.println("s1b:" + map1.isEmpty());
        System.out.println("s1a:" + flatMap1.isEmpty());
//        at io.vavr.control.Option$None.get(Option.java:495)
        String s2 = map2.get();
        System.out.println("s2 :" + map2.isEmpty());
    }

    /*
    If Option was not null then it is instance of Some.
    If mapping result in null, then it will become Some(null).
    Using Some(null) give Null pointer.
    Option of null, gives None.
    Map on None gives None.
    It forces us to create new Option if we suspect null
     */
    @Test
    public void checkChainingFlatMap() {
        Option<String> maybeFoo = Option.of("NotNull");
        Option<String> flatMap1 = maybeFoo
                .map(s -> s.toLowerCase())
                .map(s -> s.toUpperCase())
                .flatMap(s -> Option.of(s.toUpperCase()))
                .flatMap(s -> Option.of(s.toLowerCase()))
                .flatMap(s -> Option.of(s.toUpperCase()))
                .flatMap(s -> Option.of(s.toLowerCase()));
        System.out.println("flatMap1 :" + flatMap1.isEmpty());
    }


    @Test(expected = NoSuchElementException.class)
    public void shouldProveNoneIsNotSomeXX() {
        Option<String> maybeFoo = Option.of(null);
        then(maybeFoo.get()).isEqualTo(null);
    }

    @Test
    public void shouldTest2() {
        Option<String> maybeFoo = Option.of("foo");
        then(maybeFoo.get()).isEqualTo("foo");

        Option<String> maybeFooBar = maybeFoo
                .map(s -> (String) null)
                .flatMap(
                        s -> Option.of(s).map(t -> t.toUpperCase() + "bar")
                );
        then(maybeFooBar.isEmpty()).isTrue();
    }

    @Test
    public void givenValue_whenCreatesOption_thenCorrect() {
        String andWhatsThis = null;
        Option<String> isNoneOption = Option.of(andWhatsThis);
        Option<String> noneOption = Option.of(null);
        Option<String> someOption = Option.of("valX");

        assertEquals("None", isNoneOption.toString());
        assertEquals("None", noneOption.toString());
        assertEquals("Some(valX)", someOption.toString());

        //TODO: ONGOING

//        noneOption.get(); NoSuchElementException


        someOption = someOption
                .map(s -> (String) null)
                .flatMap(Option::of)
                .map(s -> s.toUpperCase());

//                .map(i -> i + "xx");

        someOption.get();

        System.out.println(someOption);

    }

}
