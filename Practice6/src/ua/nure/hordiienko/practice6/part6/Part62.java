package ua.nure.hordiienko.practice6.part6;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Part62 {
	
	private final int length;
    private final int place;
   
    public Part62(int l, int p) {
        this.length = l;
        this.place = p;
    }
    
    public int getLenth() {
        return length;
    }
	
	public static void getRLength(String[] arr) {
		 final HashMap<String, Part62> wordCounts = new HashMap<>();
	        for (int place = 0; place < arr.length; place++) {
	            String w = arr[place];
            wordCounts.put(w, new Part62(w.length(), place));
        }
        TreeMap<String, Part62> sortedWords = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                Part62 countWithPlaceA = wordCounts.get(a);
                Part62 countWithPlaceB = wordCounts.get(b);
                int len = countWithPlaceB.length - countWithPlaceA.length;
                if (len == 0) {
                    return countWithPlaceA.place - countWithPlaceB.place;
                } else {
                    return len;
                }
            }
        });
        sortedWords.putAll(wordCounts);
        int i = 0;
        for (Entry<String, Part62> entry : sortedWords.entrySet()) {
        	String s = entry.getKey();
            if (i == 3) {
                break;
            }
            i++;
            System.out.println(s + " ==> " + sortedWords.get(s).getLenth());
        }
    
    }
}

