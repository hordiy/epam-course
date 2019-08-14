package ua.nure.hordiienko.practice6.part3;

import ua.nure.hordiienko.practice6.part3.Parking;

public final class Part3 {

	private Part3() {
	}

	public static void main(String[] args) {
		Parking parking = new Parking(2);
		try {
			parking.arrive(5);
		} catch (IllegalArgumentException ex) {
			System.out.println("IllegalArgumentException");
		}

		try {
			parking.depart(5);
		} catch (IllegalArgumentException ex) {
			System.out.println("IllegalArgumentException");
		}
		
	
	}

}
