package roberto.day14.thread;

import java.util.logging.Logger;

public class MyThread extends Thread{
	private static Logger log = Logger.getLogger(MyThread.class.getName());
	
	public MyThread(String str) {
		super(str);
	}
	
	
	/*
	 * code to perform this thread*/
	@Override
	public void run() {
		for(int i=0; i<10; i++) {
			if(this.getName().equalsIgnoreCase("thread1"))
				log.info(i + " " + this.getName());
			else
				log.severe(i + " " + this.getName());
			
		try {
			this.sleep((long) (Math.random()*3000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
		if(this.getName().equalsIgnoreCase("thread1"))
			log.info(this.getName() + "end his job");
		else
			log.severe(this.getName() + "end his job");
	}
}
