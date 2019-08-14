package ua.nure.hordiienko.practice4;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Scanner;


public class Part5 {

	private static final String FILE_NAME = "resources";

	public void output() {
		Scanner s = new Scanner(System.in,"cp1251");
		while (s.hasNext()) {
			try {
				String[] ar = s.nextLine().split(" ");
				if ("stop".equals(ar[0])) {
					break;
				}
				if (ar.length != 2) {
					throw new ArrayIndexOutOfBoundsException();
				}
				Locale loc = new Locale(ar[1].toLowerCase(Locale.ENGLISH));
				ResourceBundle res = ResourceBundle.getBundle(FILE_NAME, loc);
				System.out.println(res.getString(ar[0]));
			} catch (ArrayIndexOutOfBoundsException | MissingResourceException e) {
				System.out.println("No such values");
				continue;
			}
		}
		s.close();
	}

	public static void main(String[] args) {
		new Part5().output();
	}
}