package javavanila.concurent;


import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ConcurentHelloTest {

    @Test
    public void CompletableFutureSupplyAsync() throws ExecutionException, InterruptedException {

        System.out.println("some action before CompletableFuture");
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Inside CompletableFuture, before first delay");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Inside CompletableFuture, after first delay");
            return "Hello";
        });

        System.out.println("some action directly after CompletableFuture.supplyAsync, before join");
        completableFuture.join();
        System.out.println("some action directly after CompletableFuture.join");

        CompletableFuture<Integer> future1 = completableFuture
                .thenApply(s -> concat(s, " hello"))
                .thenApply(s -> concat(s, " again"))
                .thenApply(s -> 6);
        System.out.println("some action directly after CompletableFuture thenApply");
        CompletableFuture<Integer> future2 = future1.thenApply(i -> i * 2);


        System.out.println("Result should be 12: " + future2.get());
    }

    String concat(String input, String add) {
        System.out.println(input + add);
        return input + add;
    }
}