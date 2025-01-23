package np.com.suman_adhikari.api.core.review;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ReviewService {


    @GetMapping(
            value = "/review",
            produces = "application/json")
    public List<Review> getReviews(
        @RequestParam(name = "productId", required = true) int productId);
}
