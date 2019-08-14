package ua.nure.hordiienko.practice1;

public class Part3 {

	public static void main(String[] args) {
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals(args[args.length-1])) {
				System.out.println(args[i]);
			} else {
			System.out.print(args[i] + " ");
			}
		}
	}
}
