package se.magnus.microservices.core.recommendation.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import se.magnus.api.core.recommendation.Recommendation;
import se.magnus.api.core.recommendation.RecommendationService;
import se.magnus.util.exceptions.InvalidInputException;
import se.magnus.util.path.ServiceUtil;

import java.util.Collections;
import java.util.List;

@RestController
public class RecommendationServiceImpl implements RecommendationService {

    private final ServiceUtil serviceUtil;
    private static final Logger LOG = LoggerFactory.getLogger(RecommendationServiceImpl.class);

    @Autowired
    public RecommendationServiceImpl(final ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
    }

    @Override
    public List<Recommendation> getRecommendations(int productId) {
        if (productId < 1) throw new InvalidInputException("Invalid productId: " + productId);

        if (productId == 113) {
            LOG.debug("No recommendations found for productId: {}", productId);
            return Collections.emptyList();
        }
        final String serviceAddress = serviceUtil.getServiceAddress();
        List<Recommendation> recommendations = List.of(
                Recommendation.builder()
                        .productId(productId)
                        .recommendationId(1)
                        .author("Author 1")
                        .rate(1)
                        .content("Content 1")
                        .serviceAddress(serviceAddress)
                        .build(),
                Recommendation.builder()
                        .productId(productId)
                        .recommendationId(2)
                        .author("Author 2")
                        .rate(2)
                        .content("Content 2")
                        .serviceAddress(serviceAddress)
                        .build(),
                Recommendation.builder()
                        .productId(productId)
                        .recommendationId(3)
                        .author("Author 3")
                        .rate(3)
                        .content("Content 3")
                        .serviceAddress(serviceAddress)
                        .build());

        LOG.debug("/recommendations response size: {}", recommendations.size());
        return recommendations;
    }
}
