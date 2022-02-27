package se.magnus.microservices.core.review.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import se.magnus.api.core.review.Review;
import se.magnus.api.core.review.ReviewService;
import se.magnus.util.exceptions.InvalidInputException;
import se.magnus.util.path.ServiceUtil;
import java.util.Collections;
import java.util.List;

@RestController
public class ReviewServiceImpl implements ReviewService {

    private static final Logger LOG = LoggerFactory.getLogger(ReviewServiceImpl.class);
    private final ServiceUtil serviceUtil;

    @Autowired
    public ReviewServiceImpl(final ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
    }

    @Override
    public List<Review> getReviews(int productId) {
        if (productId < 1) throw new InvalidInputException("Invalid productId: " + productId);

        if (productId == 213) {
            LOG.debug("No reviews found for productId: {}", productId);
            return Collections.emptyList();
        }
        final String serviceAddress = serviceUtil.getServiceAddress();
        List<Review> reviews = List.of(
                Review.builder()
                    .productId(productId)
                    .reviewId(1)
                    .author("Author 1")
                    .subject("Subject 1")
                    .content("Content 1")
                    .serviceAddress(serviceAddress)
                    .build(),
                Review.builder()
                        .productId(productId)
                        .reviewId(1)
                        .author("Author 2")
                        .subject("Subject 2")
                        .content("Content 2")
                        .serviceAddress(serviceAddress)
                        .build(),
                Review.builder()
                        .productId(productId)
                        .reviewId(1)
                        .author("Author 3")
                        .subject("Subject 3")
                        .content("Content 3")
                        .serviceAddress(serviceAddress)
                        .build());

        LOG.debug("/reviews response size: {}", reviews.size());
        return reviews;
    }
}
