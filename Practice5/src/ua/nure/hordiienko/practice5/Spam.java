package ua.nure.hordiienko.practice5;

import java.io.IOException;

public class Spam extends Thread {

	private static boolean f;

	private static String[] mes = new String[] { "@@@", "bbbbbbb" };
    private static int[] tim = new int[] { 333, 222 };

	public static boolean isFlag() {
		return f;
	}

	public static void setFlag(boolean f) {
		Spam.f = f;
	}

	public void run() {
		while (true) {
			try {
				for (int i = 0; i < mes.length; i++) {
					System.out.println(mes[i]);
					Thread.sleep(tim[i]);
					if (f) {
						break;
					}
				}
				break;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	public static void main(String[] args) {
		final Spam spa = new Spam();
		spa.setDaemon(true);
		spa.start();

		new Thread() {
			public void run() {
				byte[] bu = new byte[10];
				int co;
				try {
					do {

						while ((co = System.in.read(bu)) == -1) {
							if (co == -1) {
								continue;
							}
						}
					} while (!System.lineSeparator().equals(
							new String(bu, 0, co, "Cp1251")));
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("ENTER has been obtained");
			}
		}.start();
		System.exit(0);
	}
}