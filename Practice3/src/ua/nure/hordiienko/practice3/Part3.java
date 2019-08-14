package ua.nure.hordiienko.practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Part3 {

	private Part3() {
		throw new IllegalStateException("Utility class");
	}
	public static String convert(String input) {
		String[] array = input.split("\n");
		StringBuilder sb = new StringBuilder();
		for (String str : array) {
			sb.append(str).append(" 0 ");
		}
		
		String str = sb.toString();
		Pattern p = Pattern.compile("\\p{Punct}|[\\S&&\\P{Punct}]+");
		Matcher m = p.matcher(str);
		
		StringBuffer result = new StringBuffer();
		while (m.find()) {

			char[] stringArray = m.group().trim().toCharArray();
			if (stringArray.length >= 3) {
				if (Character.isLowerCase(stringArray[0])) {
					stringArray[0] = Character.toUpperCase(stringArray[0]);
				} else {
					stringArray[0] = Character.toLowerCase(stringArray[0]);
				}
			}
			
			String word = new String(stringArray);
			m.appendReplacement(result, word);
		}
		m.appendTail(result);
		
		String s1 = result.toString();
		return s1.replaceAll(" 0 ", "\n");	
	}
}