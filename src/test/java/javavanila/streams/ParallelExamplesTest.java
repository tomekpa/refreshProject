package javavanila.streams;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;

public class ParallelExamplesTest {

    @Test
    public void parallelStreamTest() throws Exception {
        //given

        //IntStream.rangeClosed is sequential, so we cant test parallelism
        List<Integer> intList = IntStream.rangeClosed(1, 10)
                .boxed()
                .collect(Collectors.toList());

        System.out.println(intList);

        //when
        List<Integer> collect = intList.parallelStream()
                .map(this::noMappWithPrint1)
                .map(this::noMappWithPrint2) //peek
                .collect(Collectors.toList());

        //then
        assertTrue(collect.size() == 10);
    }

    @Test
    public void nonParallelStreamTest() throws Exception {
        //given
        List<Integer> intList = IntStream.rangeClosed(1, 10) //sequential
                .boxed()
                .collect(Collectors.toList());

        System.out.println(intList);

        //when
        List<Integer> collect = intList.stream()
                .map(this::noMappWithPrint1)
                .map(this::noMappWithPrint2)
                .collect(Collectors.toList());

        //then
        assertTrue(collect.size() == 10);
    }


    @Test
    public void nonParallelStreamWithStatefulIntermidieteOperation() throws Exception {
        //given

        //IntStream.rangeClosed is sequential, so we cant test parallelism
        List<Integer> intList1 = IntStream.rangeClosed(1, 10)
                .boxed()
                .collect(Collectors.toList());

        List<Integer> intList2 = IntStream.rangeClosed(5, 15)
                .boxed()
                .collect(Collectors.toList());

        intList1.addAll(intList2);

        System.out.println(intList1);

        //when
        Stream<Integer> stream = intList1.stream();

        List<Integer> collect = stream
                .map(this::noMappWithPrint1)
                .map(this::noMappWithPrint2)
                .distinct() //3
                .map(this::noMappWithPrint3) //4
                .sorted()
                .map(this::noMappWithPrint4)
                .collect(Collectors.toList());

        //then
        assertTrue(collect.size() == 15);
    }

    @Test
    public void parallelStreamWithStatefulIntermidieteOperation() throws Exception {
        //given

        //IntStream.rangeClosed is sequential, so we cant test parallelism
        List<Integer> intList1 = IntStream.rangeClosed(1, 10)
                .boxed()
                .collect(Collectors.toList());

        List<Integer> intList2 = IntStream.rangeClosed(5, 15)
                .boxed()
                .collect(Collectors.toList());

        intList1.addAll(intList2);

        System.out.println(intList1);

        //when
        Stream<Integer> stream = intList1.parallelStream();

        List<Integer> collect = stream
                .map(this::noMappWithPrint1)
                .map(this::noMappWithPrint2)
                .distinct() //3
                .map(this::noMappWithPrint3) //4
                .sorted()
                .map(this::noMappWithPrint4)
                .collect(Collectors.toList());

        //then
        assertTrue(collect.size() == 15);
    }

    private Integer noMappWithPrint1(Integer input) {
        System.out.println(input + "-map1");
        System.out.flush();
        return input;
    }

    private Integer noMappWithPrint2(Integer input) {
        System.out.println(input + "-map2");
        System.out.flush();
        return input;
    }

    private Integer noMappWithPrint3(Integer input) {
        System.out.println(input + "-map3-after-distinct");
        System.out.flush();
        return input;
    }

    private Integer noMappWithPrint4(Integer input) {
        System.out.println(input + "-map4-after-sort");
        System.out.flush();
        return input;
    }
}
