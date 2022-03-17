package se.magnus.microservices.composite.product.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import se.magnus.api.core.product.Product;
import se.magnus.api.core.product.ProductService;
import se.magnus.api.core.recommendation.Recommendation;
import se.magnus.api.core.recommendation.RecommendationService;
import se.magnus.api.core.review.Review;
import se.magnus.api.core.review.ReviewService;
import java.util.List;


@Component
public class ProductCompositeIntegration implements ProductService, RecommendationService, ReviewService {

    private final String productServiceUrl;
    private final String recommendationServiceUrl;
    private final String reviewServiceUrl;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private static final Logger LOG = LoggerFactory.getLogger(ProductCompositeIntegration.class);


    @Autowired
    public ProductCompositeIntegration(RestTemplate restTemplate,
                                       ObjectMapper objectMapper,
                                       @Value("${app.product-service.host}") String productServiceHost,
                                       @Value("${app.product-service.port}") String productServicePort,
                                       @Value("${app.review-service.host}") String reviewServiceHost,
                                       @Value("${app.review-service.port}") String reviewServicePort,
                                       @Value("${app.recommendation-service.host}") String recommendationServiceHost,
                                       @Value("${app.recommendation-service.port}") String recommendationServicePort) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.productServiceUrl = "http://" + productServiceHost + ":" + productServicePort + "/product";
        this.reviewServiceUrl = "http://" + reviewServiceHost + ":" + reviewServicePort + "/review?productId=";
        this.recommendationServiceUrl = "http://" + recommendationServiceHost + ":" + recommendationServicePort + "/recommendation?productId=";
    }

    @Override
    public Product getProduct(int productId) {
        return null;
    }

    @Override
    public List<Recommendation> getRecommendations(int productId) {
        return null;
    }

    @Override
    public List<Review> getReviews(int productId) {
        return null;
    }
}
