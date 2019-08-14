package ua.nure.hordiienko.practice6.part4;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Range implements Iterable<Integer> {
	
	private int[] arr;
	private boolean rev;
	private int n;
	private int m;
	
	public Range(int a, int b) {
		setA(a);
		setB(b);
		setRev(false);
		arr = new int[getB() - getA() + 1];
		fillArray();
	}
	
	public Range(int start, int end, boolean rev) {
		setA(start);
		setB(end);
		setRev(rev);
		arr = new int[getB() - getA() + 1];
		fillArray();
	}

	public int[] getArr() {
		return Arrays.copyOf(arr, arr.length);
	}

	public boolean isRev() {
		return rev;
	}

	public final void setRev(boolean rev) {
		this.rev = rev;
	}

	public final int getA() {
		return n;
	}

	public final void setA(int start) {
		this.n = start;
	}

	public final int getB() {
		return m;
	}

	public final void setB(int end) {
		this.m = end;
	}

	public final void fillArray() {
		int loc = getA();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = loc;
			loc++;
		}
	}

	public void output() {
		Iterator<Integer> it = iterator();
		while (it.hasNext()) {
			System.out.print(it.next() + " ");
		}
	}

	@Override
	public Iterator<Integer> iterator() {
		return new RangeIterator();
	}

	private class RangeIterator implements Iterator<Integer> {

		private int p;
		
		RangeIterator() {
			if (rev) {
				setPointer(arr.length);
			} else {
				setPointer(-1);
			}
		}

		public int getPointer() {
			return p;
		}

		public final void setPointer(int pointer) {
			this.p = pointer;
		}


		@Override
		public boolean hasNext() {
			if (rev) {
				return getPointer() - 1 >= 0;
			} else {
				return getPointer() + 1 < arr.length; 
			}
		}

		@Override
		public Integer next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			if (rev) {
				setPointer(getPointer() - 1);
				return arr[p];
			} else {
				setPointer(getPointer() + 1);
				return arr[p];
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}
}