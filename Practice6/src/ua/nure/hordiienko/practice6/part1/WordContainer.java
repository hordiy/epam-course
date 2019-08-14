package ua.nure.hordiienko.practice6.part1;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class WordContainer extends TreeSet<Word> {

	private static final long serialVersionUID = -5005974906420218868L;


	public WordContainer(Comparator<? super Word> comparator) {
        super(comparator);
    }

    public static void main(String[] args) {
        Part1.wordCounter(System.in, System.out);
    }

    @Override
    public boolean add(Word w) {
        if (!contains(w)) {
            super.add(w);
            return false;
        }
        Iterator<Word> i = iterator();
        while (i.hasNext()) {
            Word next = i.next();
            if (comparator().compare(next,w) == 0) {
                next.setF(next.getF() + 1);
                return true;
            }
        }
        return false;
    }


    public Iterator<Word> frequencyIterator() {
        TreeSet<Word> wor = new TreeSet<>(new Word.CompareByFrequency());
        for (Iterator<Word> i = iterator(); i.hasNext(); ) {
            Word next = i.next();
            wor.add(next);
        }
        return wor.iterator();
    }
}