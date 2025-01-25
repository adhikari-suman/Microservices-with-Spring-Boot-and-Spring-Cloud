package np.com.suman_adhikari.microservices.core.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

    @Autowired WebTestClient webClient;

    @Test
    void contextLoads() {
    }

    @Test
    void getProductById() {
        int productId = 1;

        webClient.get()
                 .uri("/product/{id}", productId)
                 .exchange()
                 .expectStatus()
                 .isOk()
                 .expectHeader()
                 .contentType(MediaType.APPLICATION_JSON)
                 .expectBody()
                 .jsonPath("$.productId")
                 .isEqualTo(productId);
    }

    @Test
    void getProductInvalidParameterString() {
        webClient.get()
                 .uri("/product/no-integer")
                 .exchange()
                 .expectStatus()
                 .isEqualTo(HttpStatus.BAD_REQUEST)
                 .expectHeader()
                 .contentType(MediaType.APPLICATION_JSON)
                 .expectBody()
                 .jsonPath("$.path")
                 .isEqualTo("/product/no-integer")
                 .jsonPath("$.message")
                 .isEqualTo("Type mismatch.");
    }

    @Test
    void getProductNotFound() {
        int productIdNotFound = 13;

        webClient.get()
                 .uri("/product/{id}", productIdNotFound)
                 .exchange()
                 .expectStatus()
                 .isNotFound()
                 .expectHeader()
                 .contentType(MediaType.APPLICATION_JSON)
                 .expectBody()
                 .jsonPath("$.path")
                 .isEqualTo("/product/" + productIdNotFound)
                 .jsonPath("$.message")
                 .isEqualTo("No product found for productId: " + productIdNotFound);
    }

	@Test
	void getProductInvalidParameterNegativeValue() {
		int productIdInvalid = -1;

		webClient.get()
				 .uri("/product/{id}", productIdInvalid)
				 .exchange()
				 .expectStatus()
				 .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY)
				 .expectHeader()
				 .contentType(MediaType.APPLICATION_JSON)
				 .expectBody()
				 .jsonPath("$.path")
				 .isEqualTo("/product/" + productIdInvalid)
				 .jsonPath("$.message")
				 .isEqualTo("Invalid productId: " + productIdInvalid);
	}

}
