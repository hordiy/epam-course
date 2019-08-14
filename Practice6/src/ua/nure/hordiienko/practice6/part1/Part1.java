package ua.nure.hordiienko.practice6.part1;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Scanner;

public final class Part1 {

	private Part1() {
	}

	public static void main(String[] args) {
		wordCounter(new ByteArrayInputStream(("asd 43 asdf asd 43\n" + 
				"434 asdf\n" + 
				"kasdf asdf stop asdf\n" + 
				"stop").getBytes()), System.out);
	}

	public static void wordCounter(InputStream in, PrintStream out) {
		Scanner scanner = new Scanner(in);
		WordContainer wordContainer = new WordContainer(new Word.CompareByWord());
		while (scanner.hasNext()) {
			String words = scanner.nextLine();
			for (String word : words.split(" ")) {
				if ("stop".equals(word)) {
					break;
				} else {
					wordContainer.add(new Word(word));
				}
			}
		}

		Iterator<Word> iterator = wordContainer.frequencyIterator();
		while (iterator.hasNext()) {
			Word w = iterator.next();
			out.printf("%s : %s%n", w.getV(), w.getF());
		}
	}
}
	