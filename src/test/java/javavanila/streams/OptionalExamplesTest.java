package javavanila.streams;

import com.google.common.collect.ImmutableList;
import javavanila.streams.OptionalExamples.Car;
import javavanila.streams.OptionalExamples.Company;
import javavanila.streams.OptionalExamples.Owner;
import lombok.Getter;
import lombok.Setter;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;


@RunWith(MockitoJUnitRunner.class)
public class OptionalExamplesTest {

    static Integer helpCounter = 0;


    @Test
    public void shouldMapOptionalBoolToString() {
        assertThat(mapOptionalBoolToStringA(true), is("AAA"));
        assertThat(mapOptionalBoolToStringA(false), is("BBB"));
        assertThat(mapOptionalBoolToStringA(null), is("CCC"));
        assertThat(mapOptionalBoolToStringB(true), is("AAA"));
        assertThat(mapOptionalBoolToStringB(false), is("BBB"));
        assertThat(mapOptionalBoolToStringB(null), is("CCC"));
    }

    private String mapOptionalBoolToStringA(Boolean aBoolean) {
        return Optional.ofNullable(aBoolean)
                .map(bool -> {
                    if (bool) {
                        return "AAA";
                    } else {
                        return "BBB";
                    }
                })
                .orElse("CCC");
    }

    private String mapOptionalBoolToStringB(Boolean aBoolean) {
        if (aBoolean == null) return "CCC";
        if (aBoolean) {
            return "AAA";
        } else {
            return "BBB";
        }
    }

    @Test
    public void shouldMapOptionalToString() {
        Optional<TestCar> opt = Optional.ofNullable(new TestCar());
        String name = opt.map(TestCar::getName).get();
        assertThat(name, is("CarName"));
    }

    @Test
    public void shouldGiveRandomName1() {
        Optional<TestCar> opt = Optional.ofNullable(null);
        String name = opt.map(TestCar::getName).orElse("RandomName");
        assertThat(name, is("RandomName"));
    }

    @Test
    public void shouldGiveRandomName2() {
        Optional<TestCar> opt = Optional.ofNullable(new TestCar());
        assertThat(opt.get(), not(nullValue()));
    }


    @Test
    public void shouldPrintNothingIfNotPresent() {
        Optional<TestCar> opt = Optional.ofNullable(null);
        opt.ifPresent(
                c -> System.out.println(c.getName())
        );
    }


    @Test
    public void optionalFilter() {
        Optional<String> opt = Optional.ofNullable("carName");
//        Optional<String> name = opt.filter(c -> c.contains("Name")).orElse("Dupa");
    }

    /**
     * get on empty optional = NoSuchElementException
     * <p>
     * flatMap(Function<? super T,Optional<U>> mapper)
     * If a value is present, apply the provided Optional-bearing mapping function to it, return that result, otherwise return an empty Optional.
     * aplikuje maping albo zwraca optional empty, ale jak zaaplikuje maping też zwraca Optionala???
     */
    @Test
    public void optionalGenericMapFlatmap() {

        Optional<String> nonEmptyGender = Optional.of("male");
        Optional<String> emptyGender = Optional.empty();

        System.out.println("Non-Empty Optional:: " + nonEmptyGender.map(String::toString));
        System.out.println("Empty Optional    :: " + emptyGender.map(String::toString));

        Optional<Optional<String>> nonEmptyOptionalGender = Optional.of(Optional.of("male"));
        System.out.println("Optional value   :: " + nonEmptyOptionalGender);
        System.out.println("Optional.map     :: " + nonEmptyOptionalGender.map(gender -> gender.map(String::toString)));
        System.out.println("Optional.flatMap :: " + nonEmptyOptionalGender.flatMap(gender -> gender.map(String::toString)));

        Optional<Optional<String>> emptyOptionalGender = Optional.of(Optional.ofNullable(null));
        System.out.println("Empty Optional value   :: " + emptyOptionalGender);
        System.out.println("Empty Optional.map     :: " + emptyOptionalGender.map(gender -> gender.map(String::toString)));
        System.out.println("Empty Optional.flatMap :: " + emptyOptionalGender.flatMap(gender -> gender.map(String::toString)));

        Optional<String> s = Optional.of("input");
        System.out.println(s.map(OptionalExamplesTest::getOutput));
        System.out.println(s.flatMap(OptionalExamplesTest::getOutputOpt));
    }

    @Test
    public void optionalMap() {

        Optional<String> nonEmptyGender = Optional.of("male");
        Optional<String> emptyGender = Optional.ofNullable(null);

        Optional<String> nonEmptyOptional = nonEmptyGender
                .map(this::addNewNumber)
                .map(this::addNewNumber)
                .map(this::addNewNumber);

        System.out.println(nonEmptyOptional);

        Optional<String> emptyOptional = emptyGender
                .map(this::addNewNumber)
                .map(this::addNewNumber)
                .map(this::addNewNumber);

        System.out.println(emptyOptional);

        ImmutableList<Optional<String>> of = ImmutableList.of(
                Optional.of("first"),
                Optional.of("second")
        );

        of.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(s -> Optional.of(s + "-mapped"))
                .forEach(System.out::println);

        String s = null;
        String nonEmptyGenderMap = Optional.ofNullable(s)
                .map(this::addNewNumber)
                .orElseGet(() -> {
                    return "asdfasdf";
                });

        System.out.println(nonEmptyGenderMap);
    }

    @Test
    public void optionalFlatMap() {

        Optional<String> nonEmptyGender = Optional.of("male");

        String s1 = nonEmptyGender
                .flatMap(s -> Optional.of(s + "-modified"))
                .orElse("");

        System.out.printf(s1);
    }

    @Test
    public void chainingFlatMapsTest() throws Exception {
        String companyName = "TomekPa Future Solutions";

        Company company = new Company(companyName);
        Car car = new Car(company);
        Owner owner = new Owner(car);

        String name = owner.getCar()
                .flatMap(Car::getCompany)
                .flatMap(Company::getName)
                .orElse("Ups No Name");

        System.out.println(name);
    }

    @Test
    public void chainingFlatMapsNullTest() throws Exception {
        String companyName = null;

        Company company = new Company(companyName);
        Car car = new Car(company);
        Owner owner = new Owner(car);

        String name = owner.getCar()
                .flatMap(Car::getCompany)
                .flatMap(Company::getName)
                .orElse("Ups No Name");

        System.out.println(name);

        Optional<String> opt = owner.getCar()
                .flatMap(Car::getCompany)
                .flatMap(Company::getName);

        System.out.println(opt);
    }

    @Test
    public void chainingNormalMapsTest() throws Exception {
        String companyName = "TomekPa Future Solutions";

        Company company = new Company(companyName);
        Car car = new Car(company);
        Owner owner = new Owner(car);

        String s = owner.getCar()
                .map(Car::getCompany)
                .map(Optional::get)
                .map(Company::getName)
                .map(Optional::get)
                .get();

        System.out.println(s);

        Optional<Optional<String>> opt = owner.getCar()
                .map(Car::getCompany)
                .map(Optional::get)
                .map(Company::getName);

        System.out.println(opt);
    }

    @Test
    public void flatMapOnEmptyOptional() throws Exception {
        Optional<Object> empty = Optional.empty();

        Object its_empty_after_all = empty
                .flatMap(o -> Optional.empty())
                .flatMap(o -> Optional.empty())
                .flatMap(o -> Optional.empty())
                .flatMap(o -> Optional.empty())
                .orElse("Its empty after all");

        System.out.println(its_empty_after_all);
    }

    @Test
    public void should() throws Exception {
        Optional<String> empty = Optional.of("start_");

        String its_empty_after_all = empty
                .flatMap(o -> {
                    System.out.println("debug:" + o);
                    return Optional.ofNullable(o + "x");
                })
                .flatMap(o -> {
                    System.out.println("debug:" + o);
                    return Optional.ofNullable(o + "y");
                })
                .orElse("Its empty after all");

        System.out.println(its_empty_after_all);
    }

    /**
     * Flat map create stream
     * So we can make stream from sub collection or stream from one object
     * Belo example from one object we make stream of 2 objects
     */


    @Test
    public void doubleNumberOfObjectsInStreamUsingFlatmap() throws Exception {

        ImmutableList<Integer> inputList = ImmutableList.of(0, 1, 2, 3, 4, 5, 6);

        List<String> collection = inputList.stream() //Optional does not have flatMap(Stream)
                .flatMap(elem -> Stream.of(elem, elem))
                .map(elem -> "This is: " + elem)
                .collect(Collectors.toList());

        Arrays.stream(collection.toArray()).forEach(System.out::println);

        assertThat(collection.size(), is(14));
    }

    @Getter
    @Setter
    static class TestCar {
        int id = 1;
        String name = "CarName";
        Optional<String> companyName;
    }

    String addNewNumber(String input) {
        return input + helpCounter++;
    }

    static String getOutput(String input) {
        return input == null ? null : "output for " + input;
    }

    static Optional<String> getOutputOpt(String input) {
        return input == null ? Optional.empty() : Optional.of("output for " + input);
    }
}