package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompletableFutureHelloWorldExceptionTest {

    @Mock
    HelloWorldService helloWorldService = mock(HelloWorldService.class);

    @InjectMocks
    CompletableFutureHelloWorldException helloWorldException;

    @Test
    void helloWorld3AsyncCallsHandle() {
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception Occurred"));
        when(helloWorldService.world()).thenCallRealMethod();

        String result = helloWorldException.helloWorld3AsyncCallsHandle();
        assertEquals(" WORLD! HI COMPLETABLEFUTURE", result);
    }

    @Test
    void helloWorld3AsyncCallsHandle2() {
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception Occurred"));
        when(helloWorldService.world()).thenThrow(new RuntimeException("Exception Occurred"));

        String result = helloWorldException.helloWorld3AsyncCallsHandle();
        assertEquals(" HI COMPLETABLEFUTURE", result);
    }

    @Test
    void helloWorld3AsyncCallsHandle3() {
        when(helloWorldService.hello()).thenCallRealMethod();
        when(helloWorldService.world()).thenCallRealMethod();

        String result = helloWorldException.helloWorld3AsyncCallsHandle();
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE", result);
    }

    @Test
    void helloWorld3AsyncCallsExceptionally() {
        when(helloWorldService.hello()).thenCallRealMethod();
        when(helloWorldService.world()).thenCallRealMethod();

        String result = helloWorldException.helloWorld3AsyncCallsExceptionally();
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE", result);
    }

    @Test
    void helloWorld3AsyncCallsExceptionally2() {
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception Occurred"));
        when(helloWorldService.world()).thenCallRealMethod();

        String result = helloWorldException.helloWorld3AsyncCallsExceptionally();
        assertEquals(" WORLD! HI COMPLETABLEFUTURE", result);
    }

    @Test
    void helloWorld3AsyncCallsExceptionally3() {
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception Occurred"));
        when(helloWorldService.world()).thenThrow(new RuntimeException("Exception Occurred"));

        String result = helloWorldException.helloWorld3AsyncCallsExceptionally();
        assertEquals(" HI COMPLETABLEFUTURE", result);
    }

    @Test
    void helloWorld3AsyncCallsWhenComplete() {
        when(helloWorldService.hello()).thenCallRealMethod();
        when(helloWorldService.world()).thenCallRealMethod();

        String result = helloWorldException.helloWorld3AsyncCallsWhenComplete();
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE", result);
    }

    @Test
    void helloWorld3AsyncCallsExceptionallyWhen2() {
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception Occurred"));
        when(helloWorldService.world()).thenCallRealMethod();

        String result = helloWorldException.helloWorld3AsyncCallsWhenComplete();
        assertEquals(" HI COMPLETABLEFUTURE", result);
    }

    @Test
    void helloWorld3AsyncCallsExceptionallyWhen3() {
        when(helloWorldService.hello()).thenThrow(new RuntimeException("Exception Occurred"));
        when(helloWorldService.world()).thenThrow(new RuntimeException("Exception Occurred"));

        String result = helloWorldException.helloWorld3AsyncCallsWhenComplete();
        assertEquals(" HI COMPLETABLEFUTURE", result);
    }
}