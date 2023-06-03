package com.learnjava.completablefuture;

import com.learnjava.domain.Product;
import com.learnjava.service.InventoryService;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceUsingCompletableFutureTest {
    private final ProductInfoService productInfoService = new ProductInfoService();
    private final ReviewService reviewService = new ReviewService();
    private final InventoryService inventoryService = new InventoryService();
    ProductServiceUsingCompletableFuture productServiceUsingCompletableFuture = new ProductServiceUsingCompletableFuture(productInfoService, reviewService, inventoryService);

    @Test
    void retrieveProductDetails() {
        String productId = "ABC123";

        Product product = productServiceUsingCompletableFuture.retrieveProductDetails(productId);

        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size() > 0);
        assertNotNull(product.getReview());
    }

    @Test
    void retrieveProductDetails2() {
        String productId = "ABC123";
        startTimer();

        CompletableFuture<Product> productCompletableFuture = productServiceUsingCompletableFuture.retrieveProductDetails2(productId);

        productCompletableFuture.thenAccept(product -> {
            assertNotNull(product);
            assertTrue(product.getProductInfo().getProductOptions().size() > 0);
            assertNotNull(product.getReview());
        }).join();

        timeTaken();
        stopWatchReset();
    }

    @Test
    void retrieveProductDetailsWithInventory() {
        String productId = "ABC123";

        Product product = productServiceUsingCompletableFuture.retrieveProductDetailsWithInventory(productId);

        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size() > 0);
        product.getProductInfo().getProductOptions()
                .forEach(productOption -> assertNotNull(productOption.getInventory()));
        assertNotNull(product.getReview());
    }

    @Test
    void retrieveProductDetailsWithInventorySecondApproach() {
        String productId = "ABC123";

        Product product = productServiceUsingCompletableFuture.retrieveProductDetailsWithInventorySecondApproach(productId);

        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size() > 0);
        product.getProductInfo().getProductOptions()
                .forEach(productOption -> assertNotNull(productOption.getInventory()));
        assertNotNull(product.getReview());
    }
}