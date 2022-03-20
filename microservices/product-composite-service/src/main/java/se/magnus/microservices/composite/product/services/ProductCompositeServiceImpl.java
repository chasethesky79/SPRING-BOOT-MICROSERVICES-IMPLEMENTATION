package se.magnus.microservices.composite.product.services;

import org.springframework.web.bind.annotation.RestController;
import se.magnus.api.composite.ProductAggregate;
import se.magnus.api.composite.ProductCompositeService;
import se.magnus.api.composite.RecommendationSummary;
import se.magnus.api.composite.ReviewSummary;
import se.magnus.api.composite.ServiceAddresses;
import se.magnus.api.core.product.Product;
import se.magnus.api.core.recommendation.Recommendation;
import se.magnus.api.core.review.Review;
import se.magnus.util.path.ServiceUtil;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductCompositeServiceImpl implements ProductCompositeService {

    private final ProductCompositeIntegration productCompositeIntegration;
    private final ServiceUtil serviceUtil;

    public ProductCompositeServiceImpl(final ProductCompositeIntegration productCompositeIntegration,
                                       final ServiceUtil serviceUtil) {
        this.productCompositeIntegration = productCompositeIntegration;
        this.serviceUtil = serviceUtil;
    }

    @Override
    public ProductAggregate getProduct(int productId) {
        final Product product = productCompositeIntegration.getProduct(productId);
        final List<Review> reviews = productCompositeIntegration.getReviews(productId);
        final List<Recommendation> recommendationList = productCompositeIntegration.getRecommendations(productId);
        final List<ReviewSummary> reviewSummaries = reviews == null
                ? Collections.emptyList()
                : reviews.stream().map(review ->
                new ReviewSummary(review.getReviewId(),
                        review.getAuthor(),
                        review.getSubject()))
                .collect(Collectors.toList());
        final List<RecommendationSummary> recommendationSummaries = recommendationList == null
                ? Collections.emptyList()
                : recommendationList.stream().map(recommendation ->
                        new RecommendationSummary(recommendation.getRecommendationId(),
                                recommendation.getAuthor(),
                                recommendation.getRate()))
                .collect(Collectors.toList());
        final String name = product.getName();
        final int weight = product.getWeight();

        final ServiceAddresses serviceAddresses = new ServiceAddresses(
                serviceUtil.getServiceAddress(), product.getServiceAddress(),
                reviews == null || reviews.isEmpty() ? "" : reviews.get(0).getServiceAddress(),
                recommendationList == null || recommendationList.isEmpty() ? "" : recommendationList.get(0).getServiceAddress());
        return new ProductAggregate(productId, name, weight, recommendationSummaries, reviewSummaries, serviceAddresses);
    }
}
