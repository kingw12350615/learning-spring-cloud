package andy.udemy.microservices.currencyexchangeservice;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;

@RestController
public class CircuitBreakController {

	private final Logger logger = LoggerFactory.getLogger(CircuitBreakController.class);

	private AtomicLong atomicLong = new AtomicLong();

	//@Retry(name = "sample-api", fallbackMethod = "hardcodeResponse")
//	@CircuitBreaker(name = "default", fallbackMethod = "hardcodeResponse")
//	@RateLimiter(name = "default", fallbackMethod = "hardcodeResponse")
	@Bulkhead(name = "default", fallbackMethod = "hardcodeResponse")
	@GetMapping("/sample-api")
	public String sampleApi() {

		logger.info("Sample api call received. {}", atomicLong.getAndIncrement());

		// let program fail here
		if(true) {
			throw new RuntimeException("Service is unavaliable");
		}

		return "smaple-api";
	}

	public String hardcodeResponse(Exception e) {
		return "This is hardcoded response. error: " + e.getMessage();
	}
}
