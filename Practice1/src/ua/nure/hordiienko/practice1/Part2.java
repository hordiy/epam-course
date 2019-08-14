package ua.nure.hordiienko.practice1;

public class Part2 {

	public static void main(String[] args) {
		int sum = 0;
		for (String num : args) {
			int a = Integer.parseInt(num);
			sum += a;
		}
		System.out.println(sum);
		
	}
}
