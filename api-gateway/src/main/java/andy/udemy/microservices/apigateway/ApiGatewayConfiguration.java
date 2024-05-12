package andy.udemy.microservices.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

	/**
	 * Define the rule of routing
	 */
	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				// for test
				.route(p -> p.path("/get")
						.filters(t ->
							t.addRequestHeader("MyHeader1", "ValueOfHeader1")
							 .addRequestHeader("MyHeader2", "ValueOfHeader2")
						)
						.uri("http://httpbin.org:80"))
				// define currency-exchange rule
				.route(p -> p.path("/currency-exchange/**")
						.uri("lb://currency-exchange"))
				// define currency-conversion rule
				.route(p -> p.path("/currency-conversion/**")
						.uri("lb://currency-conversion"))
				.route(p -> p.path("/currency-conversion-feign/**")
						.uri("lb://currency-conversion"))
				// define role of URL rewrite and routing
				.route(p -> p.path("/currency-conversion-new/**")
						// (?<segment>) is Regex capture group , we capture it replace ${segment} with
						.filters(t -> t.rewritePath("/currency-conversion-new/(?<segment>.*)", "/currency-conversion/${segment}"))
						.uri("lb://currency-conversion"))
				.build();
	}

}
