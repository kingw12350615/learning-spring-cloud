package andy.udemy.restfulwebservice.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import andy.udemy.restfulwebservice.versioning.PersonV2.Name;

@RestController
public class VersioningPersonController {

	@GetMapping("/v1/person")
	public PersonV1 getV1PersonByPathVersionig() {
		return new PersonV1("Andy Rau");
	}

	@GetMapping("/v2/person")
	public PersonV2 getV2PersonByPathVersionig() {
		return new PersonV2(new Name("Andy", "Rau"));
	}

	@GetMapping(value = "/person", params = "version=1")
	public PersonV1 getV1PersonByParameterVersionig() {
		return new PersonV1("Andy Rau");
	}

	@GetMapping(value = "/person", params = "version=2")
	public PersonV2 getV2PersonByParameterVersionig() {
		return new PersonV2(new Name("Andy", "Rau"));
	}

	@GetMapping(value = "/person", headers = "X-API-VERSION=1")
	public PersonV1 getV1PersonByHeaderVersionig() {
		return new PersonV1("Andy Rau");
	}

	@GetMapping(value = "/person", headers = "X-API-VERSION=2")
	public PersonV2 getV2PersonByHeaderVersionig() {
		return new PersonV2(new Name("Andy", "Rau"));
	}

	@GetMapping(value = "/person", produces = "application/vnd.company.app-v1+json")
	public PersonV1 getV1PersonByAcceptHeader() {
		return new PersonV1("Andy Rau");
	}

	@GetMapping(value = "/person", produces = "application/vnd.company.app-v2+json")
	public PersonV2 getV2PersonByAcceptHeader() {
		return new PersonV2(new Name("Andy", "Rau"));
	}


}
