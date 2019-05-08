package vavr;

import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Queue;
import io.vavr.control.Option;
import org.junit.Test;

//TODO: What is monad

public class VavrBasicCollectionsTest {

    @Test
    public void shouldTestVavrList() {
        List<Integer> vavrList = List.of(1, 2, 2, 2, 3);
        System.out.println(vavrList.toString());

        List<Integer> vavrList2 = vavrList.distinct();
        System.out.println(vavrList2.toString());

        vavrList2 = vavrList.prepend(0);
        System.out.println(vavrList2.toString());

        vavrList2 = vavrList.tail().prepend(4);
        System.out.println(vavrList2.toString());

        vavrList2 = vavrList2.append(5);
        System.out.println(vavrList2.toString());

        vavrList2 = vavrList2.tail().append(6);
        System.out.println(vavrList2.toString());
//        List(1, 2, 2, 2, 3)
//        List(1, 2, 3)
//        List(0, 1, 2, 2, 2, 3)
//        List(4, 2, 2, 2, 3)
//        List(4, 2, 2, 2, 3, 5)
//        List(2, 2, 2, 3, 5, 6)
    }

    @Test
    public void shouldTestVavrQueue() {
        Queue<Integer> queue = Queue.of(1);
        System.out.println(queue.toString());

        queue = queue.enqueue(2);
        System.out.println(queue.toString());

        queue = queue.enqueue(3);
        System.out.println(queue.toString());
        System.out.println("======================================");

        Option<Tuple2<Integer, Queue<Integer>>> tupleQueue = queue.dequeueOption();

        Option<Integer> element1 = tupleQueue.map(Tuple2::_1);
        Option<Queue<Integer>> reaminingQueue1 = tupleQueue.map(Tuple2::_2);
        Integer integer1 = element1.get();//.orElse(0);
        System.out.println(integer1);
        queue = reaminingQueue1.get();
        System.out.println(queue);

        tupleQueue = queue.dequeueOption();
        element1 = tupleQueue.map(Tuple2::_1);
        reaminingQueue1 = tupleQueue.map(Tuple2::_2);
        integer1 = element1.get();
        System.out.println(integer1);
        queue = reaminingQueue1.get();
        System.out.println(queue);

        tupleQueue = queue.dequeueOption();
        element1 = tupleQueue.map(Tuple2::_1);
        reaminingQueue1 = tupleQueue.map(Tuple2::_2);
        integer1 = element1.get();
        System.out.println(integer1);
        queue = reaminingQueue1.get();
        System.out.println(queue);

        tupleQueue = queue.dequeueOption();
        element1 = tupleQueue.map(Tuple2::_1);
        reaminingQueue1 = tupleQueue.map(Tuple2::_2);
        integer1 = element1.get();
        System.out.println(integer1);
        queue = reaminingQueue1.get();
        System.out.println(queue);
    }
}
