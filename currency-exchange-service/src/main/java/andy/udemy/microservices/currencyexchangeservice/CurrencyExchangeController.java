package andy.udemy.microservices.currencyexchangeservice;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

	private final Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
	@Autowired
	Environment environment;
	@Autowired
	CurrencyExchangeRepository repository;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {

		logger.info("retrieveExchangeValue called with {} to {}", from, to);

		CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);
		if(currencyExchange == null) {
			throw new NoSuchElementException(String.format("Unable to find data for %s to %s", from, to));
		}
		String port = environment.getProperty("local.server.port");
		currencyExchange.setEnviroment(port);
		return currencyExchange;
	}
}
