package andy.udemy.microservices.limitservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "limits-service")
@Component
public class Configuration {

	private int minimun;
	private int maximun;

	public int getMinimun() {
		return minimun;
	}
	public void setMinimun(int minmun) {
		this.minimun = minmun;
	}
	public int getMaximun() {
		return maximun;
	}
	public void setMaximun(int maximun) {
		this.maximun = maximun;
	}


}
