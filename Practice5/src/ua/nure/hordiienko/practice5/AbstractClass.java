package ua.nure.hordiienko.practice5;

import static java.lang.Thread.sleep;

abstract class AbstractClass implements Runnable {

    private static final long SLEEP_TIME = 20;

    @Override
    public void run() {
        long nextTime = System.currentTimeMillis() + SLEEP_TIME;
        while (nextTime > System.currentTimeMillis()) {
            aOperation();
        }
    }

    public abstract void aOperation();

    protected void t() {
    	for (int i = 0; i < Part3.getK(); i++) {
            System.out.println(getFirstCounter() + " " + getSecondCounter());
	        incrFirstCounter();
	        try {
	            sleep(Part3.getT());
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        incrSecondCounter();
    	}
    }

    protected abstract void incrFirstCounter();
    
    protected abstract void incrSecondCounter();

    protected abstract Number getFirstCounter();

    protected abstract Number getSecondCounter();

}
