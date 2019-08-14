package ua.nure.hordiienko.practice6.part4;

public class Part4 {

	public static void main(String[] args) {
		Range myList = new Range(3, 10);
		myList.forEach(System.out::print);
		System.out.println();
		myList = new Range(3, 10, true);
		myList.forEach(System.out::print);
	}

}