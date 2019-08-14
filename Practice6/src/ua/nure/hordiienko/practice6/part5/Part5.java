package ua.nure.hordiienko.practice6.part5;

public class Part5 {

	public static void main(String[] args) {
		Tree<Integer> t = new Tree<>();
		System.out.println(t.add(3));
		System.out.println(t.add(3));
		System.out.println("~~~~~~~");
		t.add(new Integer[]{1, 2, 5, 4, 6, 0});
		t.print();
		System.out.println("~~~~~~~");
		System.out.println(t.remove(5));
		System.out.println(t.remove(5));
		System.out.println("~~~~~~~");
		t.print();
	}

}