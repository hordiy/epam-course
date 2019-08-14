package ua.nure.hordiienko.practice6;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import ua.nure.hordiienko.practice6.part1.Part1;
import ua.nure.hordiienko.practice6.part2.Part2;
import ua.nure.hordiienko.practice6.part3.Part3;
import ua.nure.hordiienko.practice6.part4.Part4;
import ua.nure.hordiienko.practice6.part5.Part5;
import ua.nure.hordiienko.practice6.part6.Part6;


public class Demo {

	public static void main(String[] args) throws IOException {

		try {
			System.setIn(new ByteArrayInputStream(
					"asd 43 asdf asd 43 434 asdf kasdf asdf stop asdf stop".getBytes(System
							.getProperty("file.encoding"))));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.setIn(System.in);
		System.out.println("=========================== Part1");
		Part1.main(args);

		System.out.println("=========================== Part2");
		Part2.main(args);

		System.out.println("=========================== Part3");
		Part3.main(args);
		System.out.println();

		System.out.println("=========================== Part4");
		Part4.main(args);

		System.out.println("=========================== Part5");
		Part5.main(args);

		System.out.println("=========================== Part6");
		System.out.println("---------------------------frequency");
		Part6.main(new String[] {"--input", "part6.txt", "--task", "frequency"});
		System.out.println("---------------------------length");
		Part6.main(new String[] {"--input", "part6.txt", "--task", "length"});
		System.out.println("---------------------------duplicates");
		Part6.main(new String[] {"--input", "part6.txt", "--task", "duplicates"});
	}
}
