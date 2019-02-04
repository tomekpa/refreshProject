//package javavanila.threads;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.junit.MockitoJUnitRunner;
//
//@RunWith(MockitoJUnitRunner.class)
//public class ThreadLocalExamplesTest {
//
////    @Test
//    public void shouldCauseDeadlock() throws Exception {
//
//        Thread t1 = new Thread( new ThreadLocalExamples.SomeRunnable("Data 0"));
//        Thread t2 = new Thread( new ThreadLocalExamples.SomeRunnable("Data 1"));
//
//        t1.start();
//        t2.start();
//
//        t1.join();
//        t2.join();
//    }
//}