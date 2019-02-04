//package javavanila.threads;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.junit.MockitoJUnitRunner;
//
//@RunWith(MockitoJUnitRunner.class)
//public class DeadlockExamplesTest {
//
//    private static Integer loopCounter = 1000;
//
////    @Test
//    public void shouldCauseDeadlock() throws Exception {
//
//        Thread t1 = new Thread( new RunnableMethod1());
//        Thread t2 = new Thread( new RunnableMethod2());
//
//        t1.start();
//        Thread.sleep(10); //To show t1 works good until t2 start
//        t2.start();
//
//        Thread.sleep(10000); //With deadlock Thread.join would block whole application, this assures it will stop eventually
//        System.out.println("DONE WAITING");
//
////        117 // 1 - Aquired lock on String.class object
////        1 - Aquired lock on Integer.class object
////        0 // 118 // 1 - Aquired lock on String.class object
////        2 - Aquired lock on Integer.class object
////        DONE WAITING
//    }
//
////    @Test
//    public void shouldMainThreadFinishForThread1() throws Exception {
//        Thread t1 = new Thread( new RunnableMethod1());
//        t1.start();
//        t1.join();
//    }
//
//
//    static class RunnableMethod1 implements Runnable {
//        @Override
//        public void run() {
//            for (int i = 0; i < loopCounter; i++) {
//                System.out.println(i + " // ");
//                DeadlockExamples.method1();
//            }
//        }
//    }
//
//    static class RunnableMethod2 implements Runnable {
//        @Override
//        public void run() {
//            for (int i = 0; i < loopCounter; i++) {
//                System.out.println(i + " // ");
//                DeadlockExamples.method2();
//            }
//        }
//    }
//}