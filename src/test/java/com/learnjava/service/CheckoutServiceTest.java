package com.learnjava.service;

import com.learnjava.domain.checkout.Cart;
import com.learnjava.domain.checkout.CheckoutResponse;
import com.learnjava.domain.checkout.CheckoutStatus;
import com.learnjava.util.DataSet;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutServiceTest {

    PriceValidatorService priceValidatorService = new PriceValidatorService();
    CheckoutService checkoutService = new CheckoutService(priceValidatorService);

    @Test
    void noOfCores() {
        System.out.println("No of cores: " + Runtime.getRuntime().availableProcessors());
    }

    @Test
    void parallelism() {
        System.out.println("parallelism: " + ForkJoinPool.getCommonPoolParallelism());
    }

    @Test
    void checkoutSixItems() {
        Cart cart = DataSet.createCart(6);

        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);

        assertEquals(CheckoutStatus.SUCCESS, checkoutResponse.getCheckoutStatus());
        assertTrue(checkoutResponse.getFinalRate() > 0);
    }

    @Test
    void checkoutThirteenItems() {
        Cart cart = DataSet.createCart(13);

        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);

        assertEquals(CheckoutStatus.FAILURE, checkoutResponse.getCheckoutStatus());
    }

    @Test
    void modifyParallelism() {
        System.out.println("getParallelism=" +ForkJoinPool.commonPool().getParallelism());

        Cart cart = DataSet.createCart(100);

        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);

        assertEquals(CheckoutStatus.FAILURE, checkoutResponse.getCheckoutStatus());
    }

    @Test
    void checkoutTwentyFiveItems() {
        Cart cart = DataSet.createCart(25);

        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);

        assertEquals(CheckoutStatus.FAILURE, checkoutResponse.getCheckoutStatus());
    }
}