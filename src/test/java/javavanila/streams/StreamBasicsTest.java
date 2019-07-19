package javavanila.streams;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class StreamBasicsTest {
    @Test
    public void shouldUpdateStreamDataSource() {
        //given
        List<String> inputList = new ArrayList(Arrays.asList("one", "two"));
        Stream<String> inputListStream = inputList.stream();
        //when
        inputList.add("updateStreamDataSource");
        String outputString = inputListStream.collect(Collectors.joining(" "));
        //then
        assertEquals(outputString, "one two updateStreamDataSource");
    }

    @Test
    public void areStreamsAreLazyUntilTerminalOperation() { //seems so
        //given
        List<String> inputList = new ArrayList(Arrays.asList("one", "two"));
        Stream<String> inputListStream = inputList.parallelStream()
                .map(this::noMappWithPrint1)
                .distinct()
                .map(this::noMappWithPrint1);
        //when
        inputList.add("updateStreamDataSource");
        System.out.println("*UPDATE DATA SOURCE");
        System.out.flush();

        String outputString = inputListStream.collect(Collectors.joining(" "));
        //then
        assertEquals(outputString, "one two updateStreamDataSource");

    }

    private String noMappWithPrint1(String input) {
        System.out.println(input + "-map1");
        System.out.flush();
        return input;
    }
}
