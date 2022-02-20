package se.magnus.api.composite;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface ProductCompositeService {

    /**
     * Fetches the aggreggate info for the product
     *
     * @param productId
     * @return
     */
    @GetMapping(value = "/product-composite/{productId}",
                produces = "application/json")
    ProductAggregate getProduct(@PathVariable int productId);
}
