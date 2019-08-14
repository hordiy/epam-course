package ua.nure.hordiienko.practice4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class Parser implements Iterable<String> {

	private static final String REG = "\\p{javaUpperCase}.*?\\.";

	private String enc = "Cp1251";

	private Matcher m;

	public Parser(File file) {
		StringBuilder sb = new StringBuilder();
		try (Scanner in = new Scanner(file, getEncod())){
			while (in.hasNextLine()) {
				sb.append(in.nextLine()).append(" ");
				sb.delete(sb.length() - 1, sb.length());
				Pattern p = Pattern.compile(REG);
				setMatcher(p.matcher(sb));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}

	public final String getEncod() {
		return enc;
	}

	public Matcher getMatcher() {
		return m;
	}

	public final void setMatcher(Matcher match) {
		this.m = match;
	}

	@Override
	public Iterator<String> iterator() {
		return new ParserIterator(getMatcher());
	}

	private static class ParserIterator implements Iterator<String> {

		private Matcher m;

		public ParserIterator(Matcher matcher) {
			this.m = matcher;
		}

		public Matcher getMatcher() {
			return this.m;
		}

		@Override
		public boolean hasNext() {
			return getMatcher().find();
		}

		@Override
		public String next() {
			if (getMatcher().group() == null) {
				throw new NoSuchElementException();
			}
			return getMatcher().group();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

}