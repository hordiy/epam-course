package ua.nure.hordiienko.practice6.part6;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public final class Part63 {
	
	private Part63() {
	}
	
	public static void getRDublicates(String[] arr) {
		final Map<String, Integer> wordCounts = new LinkedHashMap<>();
        for (int place = 0; place < arr.length; place++) {
            String w = arr[place];
            Integer countWithPlace = wordCounts.get(w);
            if (countWithPlace == null) {
                wordCounts.put(w, 1);
            } else {
            	countWithPlace += 1;
                wordCounts.put(w, countWithPlace);
            }
        }
        int i = 0;
        for (Map.Entry<String, Integer> wordCount : wordCounts.entrySet()) {
            if (i == 3) {
                break;
            }
            if (wordCount.getValue() > 1) {
                i++;
                System.out.println(new StringBuilder(
                        wordCount.getKey()).reverse().toString().toUpperCase(Locale.CANADA));
            }
        }
    }
}
