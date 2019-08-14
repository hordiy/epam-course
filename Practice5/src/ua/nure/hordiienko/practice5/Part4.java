package ua.nure.hordiienko.practice5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Part4 {
	
	private int[][] m;
	
	public int[][] fillMatrix() {
		List<String> myList = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader("part4.txt"))) {
			while (br.ready()) {
				myList.add(br.readLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		int lin = myList.size();
		int col = myList.get(0).split(" ").length;
		m = new int[lin][col];
		
		for (int i = 0; i < lin; i++) {
			for (int j = 0; j < col; j++) {
				String[] s = myList.get(i).split(" ");
				m[i][j] = Integer.parseInt(s[j]);
			}
		}
		return m.clone();
	}

	public int findMaxValue(int[][] m) {
		int ro = m.length;
		int co = m[0].length;
        int max = m[0][0];
        for (int a = 0; a < ro; a++) {
            for (int b = 0; b < co; b++) {
            	try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
                if (m[a][b] > max) {
                    max = m[a][b];
                }
            }
        }
        return max;
	}

	public int findMax(int[] ar) {
		int max = ar[0];
		for(int i = 0; i < ar.length; i++) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(ar[i] > max) {
				max = ar[i];
			}
		}
		return max;
	}

	public int findMaxValueWithThreads(int[][] m) {
		int mLength = m.length;
		int[] re = new int[mLength];
		ExecutorService executor = Executors.newFixedThreadPool(mLength);
		for(int i = 0; i < m.length; i++) {
			int tIndex = i;
			Runnable th = new Runnable() {
				public void run() {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					re[tIndex] = findMax(m[tIndex]);
				}
			};
			executor.execute(th);
		}
		executor.shutdown();
		while(!executor.isTerminated()) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return findMax(re);
	}

	public static void main(String[] args) {
		Part4 part4 = new Part4();
		part4.fillMatrix();
		long start = System.currentTimeMillis();
		int max = part4.findMaxValueWithThreads(part4.m);
		long end = System.currentTimeMillis();
		long time = end - start;
		long start2 = System.currentTimeMillis();
		int max2 = part4.findMaxValue(part4.m);
		long end2 = System.currentTimeMillis();
		long time2 = end2 - start2;
		
		System.out.println(max);
		System.out.println(time);
		System.out.println(max2);
		System.out.println(time2);
	}
}