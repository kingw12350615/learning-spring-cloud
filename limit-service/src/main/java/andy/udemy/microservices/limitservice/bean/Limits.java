package andy.udemy.microservices.limitservice.bean;

public class Limits {

	private int minimun;

	private int maximum;

	public Limits(int minimun, int maxmium) {
		super();
		this.minimun = minimun;
		this.maximum = maxmium;
	}

	public int getMinimun() {
		return minimun;
	}

	public void setMinimun(int minimun) {
		this.minimun = minimun;
	}

	public int getMaxmium() {
		return maximum;
	}

	public void setMaxmium(int maxmium) {
		this.maximum = maxmium;
	}


}
