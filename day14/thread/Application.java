package roberto.day14.thread;

public class Application {
	public static void main(String[] args) {
		MyThread th1 = new MyThread("thread1");
		MyThread th2 = new MyThread("thread2");
		th1.start();
		th2.start();
	}
}
