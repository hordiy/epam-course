package ua.nure.hordiienko.practice5;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class Part2 {

	private static String enc = "Cp1251";

	public static String getEncoding() {
		return enc;
	}

	public static void setEncoding(String enc) {
		Part2.enc = enc;
	}

	public static void main(String[] args) {
		
		
		ByteArrayInputStream by;
		try {
			by = new ByteArrayInputStream(System.lineSeparator().getBytes(
					getEncoding()));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return;
		}

		
		while (by.skip(System.lineSeparator().length()) > 0) {
	
			Spam.main(args);
			
			System.setIn(by);
			
			try {
				Thread.sleep(5001);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Spam.setFlag(true);
			System.out.println("Try to send enter to standard input");
			
			by.reset();

		InputStream stdIn = System.in;
		System.setIn(stdIn);
		}
	}
}