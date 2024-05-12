package andy.udemy.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {

	@Autowired
	CurrencyExchangeProxy currencyExchangeProxy;

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {

		HashMap<String, Object> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		String uri = "http://currency-exchange:8000/currency-exchange/from/{from}/to/{to}";

		ResponseEntity<CurrencyConversion> rsEntity = restTemplate.getForEntity(uri, CurrencyConversion.class, uriVariables);

		CurrencyConversion body = rsEntity.getBody();

		CurrencyConversion currencyConversion = new CurrencyConversion(body.getId(), from, to, quantity, body.getConversionMultiple(), quantity.multiply(body.getConversionMultiple()), body.getEnviroment());

		return currencyConversion;
	}

	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionFeign(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {

		CurrencyConversion body = currencyExchangeProxy.retrieveExchangeValue(from, to);

		CurrencyConversion currencyConversion = new CurrencyConversion(body.getId(), from, to, quantity, body.getConversionMultiple(), quantity.multiply(body.getConversionMultiple()), body.getEnviroment());

		return currencyConversion;
	}
}
