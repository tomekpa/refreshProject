package javavanila.threads;

public class DeadlockExamples {

    private final static Object resourceOne = new Object();
    private final static Object resourceTwo = new Object();

    public static void method1() {
        synchronized (resourceOne) {
            System.out.println("1 - Aquired lock on resourceOne object");

            synchronized (resourceTwo) {
                System.out.println("1 - Aquired lock on resourceTwo object");
            }
        }
    }

    public static void method2() {
        synchronized (resourceTwo) {
            System.out.println("2 - Aquired lock on resourceTwo object");

            synchronized (resourceOne) {
                System.out.println("2 - Aquired lock on resourceOne object");
            }
        }
    }
}
