package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static org.junit.jupiter.api.Assertions.*;

class CompletableFutureHelloWorldTest {

    HelloWorldService helloWorldService = new HelloWorldService();
    CompletableFutureHelloWorld completableFutureHelloWorld = new CompletableFutureHelloWorld(helloWorldService);

    @Test
    void helloWorld() {

        CompletableFuture<String> completableFuture = completableFutureHelloWorld.helloWorld();

        completableFuture.thenAccept(s -> assertEquals("HELLO WORLD", s)).join();
    }

    @Test
    void helloWorldWithSize() {

        CompletableFuture<String> completableFuture = completableFutureHelloWorld.helloWorldWithSize();

        completableFuture.thenAccept(s -> assertEquals("11 - HELLO WORLD", s)).join();
    }

    @Test
    void helloWorldMultipleAsyncCalls() {
        startTimer();
        String hello = completableFutureHelloWorld.helloWorldMultipleAsyncCalls();
        timeTaken();
        assertEquals("HELLO WORLD!", hello);
    }

    @Test
    void helloWorld3AsyncCalls() {
        startTimer();
        String hello = completableFutureHelloWorld.helloWorld3AsyncCalls();
        timeTaken();
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE", hello);
    }

    @Test
    void helloWorld4AsyncCalls() {
        startTimer();
        String hello = completableFutureHelloWorld.helloWorld4AsyncCalls();
        timeTaken();
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE! BYE!", hello);
    }

    @Test
    void helloWorldThenCompose() {
        startTimer();
        CompletableFuture<String> completableFuture = completableFutureHelloWorld.helloWorldThenCompose();
        timeTaken();
        completableFuture.thenAccept(s -> assertEquals("HELLO WORLD!", s)).join();
    }
}