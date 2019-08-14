package ua.nure.hordiienko.practice3;

import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Part1 {
	
	private static final int NUMBER = 13;
	
    private static final Pattern EMAILS = Pattern
            .compile("(([a-z\\u0410-\\u044F]+);"
            		+ "([\\w\\u0410-\\u044F]+\\s[\\w\\u0410-\\u044F]+);"
            		+ "(\\w+@(\\w+.\\w+)))", Pattern.MULTILINE);

    private Part1() {
		
	}
    
    public static String convert1(String in) {
        return in.replaceAll(EMAILS.pattern(), "$2: $4").replaceFirst(".*"
                + System.lineSeparator(), "");
    }

    public static String convert2(String in) {
		String[] a = in.split("\n");
		StringBuilder b = new StringBuilder();
		for(int i = 1; i < a.length; i++) {
			String[] a2 = a[i].split(";");
			String[] a3 = a2[1].split(" ");
			b.append(a3[1] + " " + a3[0] + " (email: " + a2[a2.length-1] + ")\n");
		}
		return b.toString();
    }
    
    public static String convert3(String input) {
        Matcher matcher = EMAILS.matcher(input);
        StringBuilder sb = new StringBuilder("mail.com ==> ");
        StringBuilder bs = new StringBuilder("google.com ==> ");
        while (matcher.find()) {
            String email = matcher.group(5);
            String e = matcher.group(2);
            if ("mail.com".contains(email)) {
            	sb.append(e).append(", ");
            } else {
            	bs.append(e).append(", ");
            }
        }
        StringBuilder clear = new StringBuilder();
        if (sb.length() > NUMBER) {
        	clear.append(sb).replace(clear.length() - 2, clear.length(), System.lineSeparator());
        }
        clear.append(bs);
        
        return clear.replace(clear.length() - 2, clear.length(), "").toString();
    }

    public static String convert4(String in) {
		String[] a = in.split("\n");
		StringBuilder b = new StringBuilder();
		b.append(a[0] + ";Password\n");
		SecureRandom src = new SecureRandom();
		StringBuilder password = new StringBuilder();
		for(int i = 1; i < a.length; i++) {
			while (password.length() < 4) {
				int pass = src.nextInt(9);
				password.append(pass);
			}
			b.append(a[i] + ";" + password + "\n");
		}
		return b.toString();
    }
}