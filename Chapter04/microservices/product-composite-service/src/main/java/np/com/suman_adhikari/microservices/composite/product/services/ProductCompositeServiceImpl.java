package np.com.suman_adhikari.microservices.composite.product.services;

import np.com.suman_adhikari.api.composite.product.*;
import np.com.suman_adhikari.api.core.product.Product;
import np.com.suman_adhikari.api.core.recommendation.Recommendation;
import np.com.suman_adhikari.api.core.review.Review;
import np.com.suman_adhikari.api.exceptions.NotFoundException;
import np.com.suman_adhikari.microservices.util.http.ServiceUtil;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductCompositeServiceImpl implements ProductCompositeService {

    private final ServiceUtil                 serviceUtil;
    private       ProductCompositeIntegration integration;

    public ProductCompositeServiceImpl(
            ServiceUtil serviceUtil, ProductCompositeIntegration integration
                                      ) {
        this.serviceUtil = serviceUtil;
        this.integration = integration;
    }

    @Override
    public ProductAggregate getProduct(int productId) {
        Product product = integration.getProduct(productId);

        if (product == null) {
            throw new NotFoundException("No product found for productId: " + productId);
        }

        List<Review>         reviews         = integration.getReviews(productId);
        List<Recommendation> recommendations = integration.getRecommendations(productId);

        return createProductAggregate(product, recommendations, reviews, serviceUtil.getServiceAddress());
    }

    private ProductAggregate createProductAggregate(
            Product product, List<Recommendation> recommendations, List<Review> reviews, String serviceAddress
                                                   ) {
        // 1. Setup product info
        int    productId = product.getProductId();
        String name      = product.getName();
        int    weight    = product.getWeight();

        // 2. Copy summary recommendation info, if available
        List<RecommendationSummary> recommendationSummaries = (recommendations == null) ?
                                                              null :
                                                              recommendations.stream()
                                                                             .map((r) -> new RecommendationSummary(
                                                                                     r.getRecommendationId(),
                                                                                     r.getAuthor(), r.getRate()
                                                                             ))
                                                                             .toList();

        // 3. Copy summary review info, if available
        List<ReviewSummary> reviewSummaries = (reviews == null) ?
                                              null :
                                              reviews.stream()
                                                     .map(r -> new ReviewSummary(r.getReviewId(), r.getAuthor(),
                                                                                 r.getSubject()
                                                     ))
                                                     .toList();

        // 4. Create info regarding the involved microservices addresses
        String productAddress = product.getServiceAddress();
        String reviewAddress = (reviews != null && !reviews.isEmpty()) ? reviews.get(0).getServiceAddress() : "";
        String recommendationAddress = (recommendations != null && !recommendations.isEmpty()) ? recommendations.get(0).getServiceAddress() : "";
        ServiceAddresses serviceAddresses = new ServiceAddresses(serviceAddress, productAddress, reviewAddress, recommendationAddress);


        return new ProductAggregate(productId, name, weight, recommendationSummaries, reviewSummaries, serviceAddresses);
    }

}
