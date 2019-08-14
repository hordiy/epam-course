package ua.nure.hordiienko.practice6.part1;

import java.io.Serializable;
import java.util.Comparator;

public class Word {

	private String v;

	private int f;

	Word(String v) {
		this.v = v;
		f = 1;
	}

	String getV() {
		return v;
	}

	public void setV(String v) {
		this.v = v;
	}

	int getF() {
		return f;
	}

	void setF(int f) {
		this.f = f;
	}


	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Word w1 = (Word)o;

		return f == w1.f && v.equals(w1.v);
	}


	@Override
	public int hashCode() {
		int result = v.hashCode();
		result = 31 * result + f;
		return result;
	}

	static class CompareByWord implements Comparator<Word>, Serializable {

		private static final long serialVersionUID = 1727493271463817297L;

		@Override
		public int compare(Word o1, Word o2) {
			return o1.getV().compareTo(o2.getV());
		}
	}

	static class CompareByFrequency implements Comparator<Word>, Serializable{
		
		private static final long serialVersionUID = -7452373168225289069L;

		@Override
		public int compare(Word o1, Word o2) {
			int comparation = -o1.getF() + o2.getF();
			if (comparation == 0) {
				return new CompareByWord().compare(o1, o2);
			}
			return comparation;
		}
	}
}