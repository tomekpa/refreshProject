package javavanila.threads;

class ThreadLocalExamples {

    private static Integer loopCounter = 1000;

    static class SomeRunnable implements Runnable {

        private ThreadLocal<String> myThreadLocal = new ThreadLocal<>();
        private String initString;

        SomeRunnable(String initString) {
            this.initString = initString;
        }

        @Override
        public void run() {
            myThreadLocal.set(initString);

            for (int i = 0; i < loopCounter; i++) {
                System.out.println(Thread.currentThread().getName() + " // " + myThreadLocal.get());
            }
        }
    }
}
