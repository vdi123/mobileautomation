package mobile.core.utils;

public class Sleeper {

	public static void sleepTightInSeconds(long timeoutInSeconds) {
		sleepTight(timeoutInSeconds * 1000L);
	}

	public static void sleepTight(long timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
