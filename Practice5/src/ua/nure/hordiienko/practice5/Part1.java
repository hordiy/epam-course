package ua.nure.hordiienko.practice5;

public class Part1 {

	public static void main(String[] args) {

		Thread thread = new Thread() {
			public void run() {
				for(int i = 0; i < 3; i++) {
					try {
						Thread.sleep(333);
						System.out.println(Thread.currentThread().getName());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		thread.start();

		Thread runnable = new Thread(new Runnable() {
			public void run() {
				for(int i = 0; i < 3; i++) {
					try {
						Thread.sleep(333);
						System.out.println(Thread.currentThread().getName());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		runnable.start();
	}
}