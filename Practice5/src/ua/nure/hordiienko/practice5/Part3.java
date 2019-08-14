package ua.nure.hordiienko.practice5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public final class Part3 {
	
	private ExecutorService executors;
    private int n;
	private static int k;
	private static int t;
	
	public Part3(int n, int k, int t) {
    	this.n = n;
    	Part3.setK(k);
    	Part3.setT(t);
    }
	
	public static int getK() {
		return k;
	}

	public static void setK(int k) {
		Part3.k = k;
	}

	public static int getT() {
		return t;
	}

	public static void setT(int t) {
		Part3.t = t;
	}

    public static void main(String[] args) {  
    	
    	Part3 obj = new Part3(5, 5, 20);
    	obj.testSync();
    }
    
    
    public void test() {
    	executors = Executors.newFixedThreadPool(n);
    	for (int i = 0; i < k; i++) {
    		executors.execute(new NonSynchronizedThread());
    	}
    	executors.shutdown();
        try {
        	executors.awaitTermination(3000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
    }
    
    public void testSync() {
    	executors = Executors.newFixedThreadPool(n);
    	for (int i = 0; i < k; i++) {
    		executors.execute(new SynchronizedThread());
    	}
      	executors.shutdown();
        try {
        	executors.awaitTermination(3000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}
    }
}
