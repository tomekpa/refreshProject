package javavanila.streams;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.OptionalInt;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ReduceExamplesTest {

    @Test
    public void simpleMax() throws Exception {
        OptionalInt max = ImmutableList
                .of(1, 2, 3, 4, 5)
                .stream()
                .mapToInt(v -> v) // Convert to int stream
                .max(); //this max, Max is special case of reduction

        max.ifPresent(Integer::toString);
    }

    @Test
    public void simpleReduce() throws Exception {

        BinaryOperator<Integer> maxOperator = (value, current) -> {
            return Math.max(value, current);
        };

        BinaryOperator<Integer> sumOperator = (value, current) -> {
            return value + current;
        };

        Integer max = ImmutableList
                .of(1, 2, 3, 4, 5)
                .stream()
                .reduce(0, maxOperator);

        Integer sum = ImmutableList
                .of(1, 2, 3, 4, 5)
                .stream()
                .reduce(0, sumOperator);

        ImmutableList
                .of(1, 2, 3, 4, 5)
                .stream()
                .reduce(0, Integer::max);

        assertThat(max, equalTo(5));
        assertThat(sum, equalTo(15));
    }

    @Test
    public void maxWithReduce() throws Exception {
        OptionalInt max = ImmutableList
                .of(1, 2, 3, 4, 5)
                .stream()
                .mapToInt(v -> v) // Convert to int stream
                .max(); //this max, Max is special case of reduction

        max.ifPresent(Integer::toString);
    }

    @Test
    public void whatIsIdentity() throws Exception {

        MyIdentity identity = new MyIdentity(0);

        List<MyIdentity> list = ImmutableList.of(1, 2, 3, 4, 5).stream()
                .map(MyIdentity::new)
                .collect(Collectors.toList());

        MyIdentity reduce = list.stream()
                .reduce(identity, (identityElement, i) -> {
                    //identityElement and identity is the same reference
                    //identity.a = 99;
                    identityElement.a = identityElement.a + i.a;
                    identityElement.as = identityElement.as + "-" + i.as;
                    return identityElement;
                });

        System.out.println(reduce.a);
        System.out.println(reduce.as);
    }

    @Test
    public void primitiveReduce() throws Exception {
        //given
        List<MyIdentity> inputList = IntStream.rangeClosed(0, 9)
                .boxed()
                .map(MyIdentity::new)
                .collect(Collectors.toList());
        //when
        int outputStream = inputList.stream()
                .mapToInt(MyIdentity::getA)
                .sum();
        //then
        assertEquals(outputStream, 45);
    }

    private static class MyIdentity {
        public int getA() {
            return a;
        }

        public int a;
        public String as;

        public MyIdentity(int a) {
            this.a = a;
            this.as = Integer.valueOf(a).toString() + "*";
        }
    }
}
