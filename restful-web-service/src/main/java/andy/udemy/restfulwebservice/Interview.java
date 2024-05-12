package andy.udemy.restfulwebservice;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 *
 */
public class Interview {

	public static void main(String[] args) {

		long period = 2000;

		HelloPrinter printer = new HelloPrinter();



		TimerTask TimerTask = new TimerTask(){

			@Override
			public void run() {
				printer.doPrint();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		};


		System.out.println("Timer executed");
		new Timer().schedule(TimerTask, 0, period);


		System.out.println("ScheduledThreadPool executed");
		ScheduledExecutorService service = Executors.newScheduledThreadPool(2);

//		for (int i = 0; i < 3; i++) {
//			service.scheduleAtFixedRate(TimerTask, 0, period, TimeUnit.MILLISECONDS);
//		}

	}

	public static class HelloPrinter{
		public void doPrint() {
			System.out.println(String.format("Hello, %d", System.currentTimeMillis()));
		}
	}
}
