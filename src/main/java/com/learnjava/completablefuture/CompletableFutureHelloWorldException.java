package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.delay;
import static com.learnjava.util.LoggerUtil.log;

public class CompletableFutureHelloWorldException {

    private HelloWorldService helloWorldService;

    public CompletableFutureHelloWorldException(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    public String helloWorld3AsyncCallsHandle() {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(helloWorldService::hello);
        CompletableFuture<String> world = CompletableFuture.supplyAsync(helloWorldService::world);
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi CompletableFuture";
        });

        return hello
                .handle((h, e) -> {
                    log("h: " + h);
                    if (e != null) {
                        log("Exception: " + e.getMessage());
                        return "";
                    }
                    return h;
                })
                .thenCombine(world, (h, w) -> h + w)
                .handle((h, e) -> {
                    log("h: " + h);
                    if (e != null) {
                        log("Exception after world: " + e.getMessage());
                        return "";
                    }
                    return h;
                })
                .thenCombine(completableFuture, (previous, current) -> previous + current)
                .thenApply(String::toUpperCase)
                .join();
    }

    public String helloWorld3AsyncCallsExceptionally() {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(helloWorldService::hello);
        CompletableFuture<String> world = CompletableFuture.supplyAsync(helloWorldService::world);
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi CompletableFuture";
        });

        return hello
                .exceptionally(e -> {
                    log("Exception: " + e.getMessage());
                    return "";
                })
                .thenCombine(world, (h, w) -> h + w)
                .exceptionally(e -> {
                    log("Exception after world: " + e.getMessage());
                    return "";
                })
                .thenCombine(completableFuture, (previous, current) -> previous + current)
                .thenApply(String::toUpperCase)
                .join();
    }

    public String helloWorld3AsyncCallsWhenComplete() {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(helloWorldService::hello);
        CompletableFuture<String> world = CompletableFuture.supplyAsync(helloWorldService::world);
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " Hi CompletableFuture";
        });

        return hello
                .whenComplete((h, e) -> {
                    log("h: " + h);
                    if (e != null) {
                        log("Exception: " + e.getMessage());
                    }
                })
                .thenCombine(world, (h, w) -> h + w)
                .whenComplete((h, e) -> {
                    log("h: " + h);
                    if (e != null) {
                        log("Exception after world: " + e.getMessage());
                    }
                })
                .exceptionally(e -> {
                    log("Exception after thenCombine: " + e.getMessage());
                    return "";
                })
                .thenCombine(completableFuture, (previous, current) -> previous + current)
                .thenApply(String::toUpperCase)
                .join();
    }
}
