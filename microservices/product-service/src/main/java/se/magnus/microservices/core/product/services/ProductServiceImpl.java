package se.magnus.microservices.core.product.services;

import jdk.internal.org.jline.utils.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import se.magnus.api.core.product.Product;
import se.magnus.api.core.product.ProductService;
import se.magnus.util.exceptions.InvalidInputException;
import se.magnus.util.exceptions.NotFoundException;
import se.magnus.util.path.ServiceUtil;

@RestController
public class ProductServiceImpl implements ProductService {

    private static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ServiceUtil serviceUtil;

    @Autowired
    public ProductServiceImpl(final ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
    }

    @Override
    public Product getProduct(int productId) {
        LOG.debug(String.format("/product return the product fpr id %s", productId));

        if (productId < 1) {
            throw new InvalidInputException(String.format("Invalid product id, %s", productId));
        }
        if (productId == 13) {
            throw new NotFoundException(String.format("No product found for productId %s", productId));
        }
        return new Product(productId, "name-"+ productId,123, serviceUtil.getServiceAddress());
    }
}
