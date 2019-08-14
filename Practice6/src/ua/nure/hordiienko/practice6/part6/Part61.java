package ua.nure.hordiienko.practice6.part6;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;
public class Part61 {
	
	private int count = 1;
    private final int place;
        
    public Part61(int p) {
    	this.place = p;
    }
          
    public int getCount() {
        return count;
    }
   
    public Part61 setCount(int c) {
    	this.count = c;      
    	return this;
    }
	
	 public static void getRFrequecy(String[] arr) {
		 final HashMap<String, Part61> wordCounts = new HashMap<>();
	        for (int place = 0; place < arr.length; place++) {
	            String w = arr[place];
	            Part61 countWithPlace = wordCounts.get(w);
	            if (countWithPlace == null) {
	                wordCounts.put(w, new Part61(place));
	            } else {
	                countWithPlace.setCount(countWithPlace.getCount()+1);
	            }
	        }
	        TreeMap<String, Part61> sortedWords = new TreeMap<>(new Comparator<String>() {
	            @Override
	            public int compare(String a, String b) {
	                Part61 countWithPlaceA = wordCounts.get(a);
	                Part61 countWithPlaceB = wordCounts.get(b);
	                int c = countWithPlaceB.count - countWithPlaceA.count;
	                if (c == 0) {
	                    return countWithPlaceA.place - countWithPlaceB.place;
	                } else {
	                    return c;
	                }
	            }
	        });
	        sortedWords.putAll(wordCounts);
	        TreeSet<String> firstStrings = new TreeSet<>(new Comparator<String>() {
	            @Override
	            public int compare(String o1, String o2) {
	                return o2.compareTo(o1);
	            }
	        });
	        int i = 0;
	        for (Entry<String, Part61> entry : sortedWords.entrySet()) {
	        	String s = entry.getKey();
	            if (i == 3) {
	                break;
	            }
	            i++;
	            firstStrings.add(s);
	        }
	        for (String s : firstStrings) {
	            System.out.println(s + " ==> " + sortedWords.get(s).getCount());
	        }
	    }
}