package ua.nure.hordiienko.practice5;

public class Part6 {
	
	private static final Object M = new Object();
	
	public static void main(String[] args) throws InterruptedException{
		Thread t = new Thread() {
			public void run() {
				try {

					while (!isInterrupted()) {
						M.toString();
					}
					interrupted();
					
					synchronized(M) {
						M.wait(400);
						
						M.wait();
					}
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		};
				
		t.start();
		
		Thread.sleep(200);
		t.interrupt();
		Thread.sleep(200);
		
		synchronized(M) {
			M.notifyAll();
			System.out.println(t.getState());
		}
		
		Thread.sleep(400);
		System.out.println(t.getState());
		
		synchronized(M) {
			M.notifyAll();
		}
		
		t.join();
		
		System.out.println(t.getState());
	}
}
