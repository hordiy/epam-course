package ua.nure.hordiienko.practice6.part2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public final class Part2 {

    private final int listLength;

    private Part2(int listLength) {
        this.listLength = listLength;
    }

    public static long removeByIndex(List<Integer> list, int k) {
    	long time = System.currentTimeMillis();
		int local = 0;
		for (int i = list.size() - 1; i >= 0; i--) {
			if (list.size() == 1) {
				break;
			}
			local += (k - 1);
			while (local >= list.size()) {
				local = local - list.size();
			}
			list.remove(local);
		}
		return System.currentTimeMillis() - time;
    }

    public static long removeByIterator(List<Integer> list, int k) {
    	long time = System.currentTimeMillis();
        int local = 0;  
        Iterator<Integer> it = list.iterator();
       	while(list.size() > 1) {
       		if(it.hasNext()) {
       			it.next();
                local++;
                if(local == k) {
                	it.remove();
                    local = 0;
                    }
                } else {
                    it = list.iterator();
                }
            }
		return System.currentTimeMillis() - time;
        }

    private List<Integer> getPeopleByArray() {
    	List<Integer> peopleByArray = new ArrayList<>();
        for (int i = 0; i < listLength; i++) {
            peopleByArray.add(i);
        }
        return peopleByArray;
    }

    private List<Integer> getPeopleByLink() {
    	List<Integer> peopleByLink = new LinkedList<>();
        for (int i = 0; i < listLength; i++) {
            peopleByLink.add(i);
        }
        return peopleByLink;
    }
    
    public static void main(String[] args) {
        Part2 part2 = new Part2(10_000);
        final int k = 4;
        
        System.out.println("ArrayList#Index: " + Part2.removeByIndex(part2.getPeopleByArray(), k) + " ms");
        System.out.println("LinkedList#Index: " + Part2.removeByIndex(part2.getPeopleByLink(), k) + " ms");
        System.out.println("ArrayList#Iterator: " + Part2.removeByIterator(part2.getPeopleByArray(), k) + " ms");
        System.out.println("LinkedList#Iterator: " + Part2.removeByIterator(part2.getPeopleByLink(), k) + " ms");
    }

}
