package se.magnus.microservices.composite.product;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import se.magnus.api.core.product.Product;
import se.magnus.api.core.recommendation.Recommendation;
import se.magnus.api.core.review.Review;
import se.magnus.microservices.composite.product.services.ProductCompositeIntegration;
import se.magnus.util.exceptions.NotFoundException;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=RANDOM_PORT)
public class ProductCompositeServiceApplicationTests {

	private static final int PRODUCT_ID_OK = 1;
	private static final int PRODUCT_ID_NOT_FOUND = 2;
	private static final int PRODUCT_ID_INVALID = 3;

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private ProductCompositeIntegration productCompositeIntegration;

	@Test
	public void contextLoads() {
	}

	@Before
	public void setUp() {
		when(productCompositeIntegration.getProduct(PRODUCT_ID_OK)).thenReturn(
				Product.builder()
						.productId(PRODUCT_ID_OK)
						.name("name-"+ PRODUCT_ID_OK)
						.weight(1)
						.serviceAddress("mock-address")
						.build());
		when(productCompositeIntegration.getRecommendations(PRODUCT_ID_OK)).thenReturn(
				List.of(
						Recommendation.builder()
								.productId(PRODUCT_ID_OK)
								.recommendationId(1)
								.author("Author 1")
								.rate(1)
								.content("Content 1")
								.serviceAddress("mock-address")
								.build(),
						Recommendation.builder()
								.productId(PRODUCT_ID_OK)
								.recommendationId(2)
								.author("Author 2")
								.rate(2)
								.content("Content 2")
								.serviceAddress("mock-address")
								.build(),
						Recommendation.builder()
								.productId(PRODUCT_ID_OK)
								.recommendationId(3)
								.author("Author 3")
								.rate(3)
								.content("Content 3")
								.serviceAddress("mock-address")
								.build()));
		when(productCompositeIntegration.getReviews(PRODUCT_ID_OK)).thenReturn(List.of(
				Review.builder()
						.productId(PRODUCT_ID_OK)
						.reviewId(1)
						.author("Author 1")
						.subject("Subject 1")
						.content("Content 1")
						.serviceAddress("mock-address")
						.build(),
				Review.builder()
						.productId(PRODUCT_ID_OK)
						.reviewId(1)
						.author("Author 2")
						.subject("Subject 2")
						.content("Content 2")
						.serviceAddress("mock-address")
						.build(),
				Review.builder()
						.productId(PRODUCT_ID_OK)
						.reviewId(1)
						.author("Author 3")
						.subject("Subject 3")
						.content("Content 3")
						.serviceAddress("mock-address")
						.build()));
		when(productCompositeIntegration.getProduct(PRODUCT_ID_NOT_FOUND)).thenThrow(new
				NotFoundException("NOT FOUND "+ PRODUCT_ID_NOT_FOUND));
		when(productCompositeIntegration.getProduct(PRODUCT_ID_INVALID)).thenThrow(new
				NotFoundException("INVALID PRODUCT "+ PRODUCT_ID_INVALID));
	}

	@Test
	public void getProductById() {
		webTestClient
			.get()
			.uri("/product-composite/" + PRODUCT_ID_OK)
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$.productId").isEqualTo(PRODUCT_ID_OK)
			.jsonPath("$.recommendations.length()").isEqualTo(3)
			.jsonPath("$.reviews.length()").isEqualTo(3);
	}

	@Test
	public void getProductNotFound() {
		webTestClient
				.get()
				.uri("/product-composite/" + PRODUCT_ID_NOT_FOUND)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isNotFound()
				.expectBody()
				.jsonPath("$.path").isEqualTo("/product-composite/" + PRODUCT_ID_NOT_FOUND)
				.jsonPath("$.message").isEqualTo("NOT FOUND "+ PRODUCT_ID_NOT_FOUND);
	}
}
