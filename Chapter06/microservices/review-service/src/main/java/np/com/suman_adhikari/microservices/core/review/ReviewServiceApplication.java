package np.com.suman_adhikari.microservices.core.review;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("np.com.suman_adhikari")
@SpringBootApplication
public class ReviewServiceApplication {
	private static final Logger LOG = LoggerFactory.getLogger(ReviewServiceApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(ReviewServiceApplication.class, args);

		String mysqlUri = ctx.getEnvironment().getProperty("spring.datasource.url");
		LOG.info("Connected to MySQL: " + mysqlUri);
	}
}
