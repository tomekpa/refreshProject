package javavanila.threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * WIKI:
 * This means that multiple threads can read the data in parallel but an exclusive lock is needed for writing or modifying data.
 * When a writer is writing the data, all other writers or readers will be blocked until the writer is finished writing.
 */
public class ReadWriteLockExamples {

    private static SharedResource sharedResource = new SharedResource(0);

    private static int writeLoopCounter = 100;
    private static int readLoopCounter = 1000;

    private static int delaysBetweenWrites = 2000; //MS
    private static int delaysBetweenReads = 100; //MS

    static class WritingRunnable implements Runnable {
        @Override
        public void run() {

            for (int i = 0; i < writeLoopCounter; i++) {
                sharedResource.manualWriteLock();
                System.out.println("WritingRunnable - before write: " + sharedResource.getCounter() + ", " + System.currentTimeMillis());
                sharedResource.manualUnsafeCounterIncrement();
                try {
                    Thread.sleep(delaysBetweenWrites);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sharedResource.manualWriteUnlock();
                System.out.println("WritingRunnable - after write: " + sharedResource.getCounter() + ", " + System.currentTimeMillis());
            }
        }
    }

    static class ReadingRunnable implements Runnable {
        @Override
        public void run() {

            for (int i = 0; i < readLoopCounter; i++) {
                System.out.println("ReadingRunnable - read:         " + sharedResource.getCounter() + ", " + System.currentTimeMillis());
                try {
                    Thread.sleep(delaysBetweenReads);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class ReadingLockRunnable implements Runnable {
        @Override
        public void run() {

            for (int i = 0; i < readLoopCounter; i++) {
                System.out.println("ReadingLockRunnable - read:     " + sharedResource.getCounterUsingReadLock() + ", " + System.currentTimeMillis());
                try {
                    Thread.sleep(delaysBetweenReads);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class SharedResource {

        private Integer counter = 1;
        private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        private final Lock readLock = readWriteLock.readLock();
        private final Lock writeLock = readWriteLock.writeLock();

        SharedResource(Integer counter) {
            this.counter = counter;
        }

        void incrementCounter() {
            writeLock.lock();
            counter = counter + 1;
            writeLock.unlock();
        }

        void manualUnsafeCounterIncrement() {
            counter = counter + 1;
        }

        void manualWriteLock() {
            writeLock.lock();
        }

        void manualWriteUnlock() {
            writeLock.unlock();
        }

        Integer getCounterUsingReadLock() {
            Integer localCounterTest;
            readLock.lock();
            localCounterTest = counter;
            readLock.unlock();
            return localCounterTest;
        }

        Integer getCounter() {
            return counter;
        }
    }
}
