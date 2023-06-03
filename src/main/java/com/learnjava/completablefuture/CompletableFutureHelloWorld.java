package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import com.learnjava.util.LoggerUtil;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.delay;
import static com.learnjava.util.LoggerUtil.log;

public class CompletableFutureHelloWorld {

    private final HelloWorldService helloWorldService;

    public CompletableFutureHelloWorld(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    public CompletableFuture<String> helloWorld() {
        return CompletableFuture.supplyAsync(helloWorldService::helloWorld)
                .thenApply(String::toUpperCase);
    }

    public CompletableFuture<String> helloWorldWithSize() {
        return CompletableFuture.supplyAsync(helloWorldService::helloWorld)
                .thenApply(s -> s.length() + " - " + s.toUpperCase());
    }

    public String helloWorldMultipleAsyncCalls() {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(helloWorldService::hello);
        CompletableFuture<String> world = CompletableFuture.supplyAsync(helloWorldService::world);
        return hello.thenCombine(world, (h, w) -> h + w)
                .thenApply(String::toUpperCase)
                .join();
    }

    public String helloWorld3AsyncCalls() {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(helloWorldService::hello);
        CompletableFuture<String> world = CompletableFuture.supplyAsync(helloWorldService::world);
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi CompletableFuture";
        });

        return hello.thenCombine(world, (h, w) -> h + w)
                .thenCombine(completableFuture, (previous, current) -> previous + current)
                .thenApply(String::toUpperCase)
                .join();
    }

    public CompletableFuture<String> helloWorldThenCompose() {
        return CompletableFuture.supplyAsync(helloWorldService::hello)
                .thenCompose(helloWorldService::worldFuture)
                .thenApply(String::toUpperCase);

    }

    public static void main(String[] args) {
        HelloWorldService helloWorldService = new HelloWorldService();
        CompletableFuture.supplyAsync(helloWorldService::helloWorld)
                .thenApply(String::toUpperCase)
                .thenAccept(LoggerUtil::log)
                .join();
        log("Done!");
//        delay(2000);
    }

    public String helloWorld4AsyncCalls() {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(helloWorldService::hello);
        CompletableFuture<String> world = CompletableFuture.supplyAsync(helloWorldService::world);
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi CompletableFuture!";
        });
        CompletableFuture<String> byeCompletableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Bye!";
        });

        return hello.thenCombine(world, (h, w) -> h + w)
                .thenCombine(completableFuture, (previous, current) -> previous + current)
                .thenCombine(byeCompletableFuture, (previous, current) -> previous + current)
                .thenApply(String::toUpperCase)
                .join();
    }
}
