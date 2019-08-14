package ua.nure.hordiienko.practice3;

public final class Part2 {
	
	private Part2() {
		
	}

	public String findLongest(String[] arr) {
		String longW = arr[0].trim();
		StringBuilder b = new StringBuilder();
		for(int i = 1; i < arr.length; i++) {
			if(longW.length() < arr[i].trim().length()) {
				longW = arr[i].trim();
			}
		}
		for(int i = 0; i < arr.length; i++) {
        	if((arr[i].length() == longW.length()) && (!b.toString().contains(arr[i]))) {
        		b.append(arr[i] + ", ");
        	}
        }
		b.deleteCharAt(b.lastIndexOf(","));
		return b.toString();
	}

	public String findShortest(String[] arr) {
        String shortW = arr[0].trim();
        StringBuilder b = new StringBuilder();
        for (int i = 1; i < arr.length; i++) {
          if (shortW.length() > arr[i].trim().length()) {
        	  shortW = arr[i].trim();
          }
        }

        for(int i = 0; i < arr.length; i++) {
        	if((arr[i].length() == shortW.length()) && (!b.toString().contains(arr[i]))) {
        			b.append(arr[i] + ", ");
        		}
        	}
        b.deleteCharAt(b.lastIndexOf(","));
        return b.toString();
  }


	public static String convert(String input) {
		Part2 part2 = new Part2();
		StringBuilder min = new StringBuilder();
		StringBuilder max = new StringBuilder();
		String[] words = input.split("\\s*(\\s|,|!|'|\\.)\\s*");
		min.append("Min: ").append(part2.findShortest(words)).deleteCharAt(min.lastIndexOf(" "));
		max.append("Max: ").append(part2.findLongest(words)).deleteCharAt(max.lastIndexOf(" "));
		return min + "\n" + max;
	}

}