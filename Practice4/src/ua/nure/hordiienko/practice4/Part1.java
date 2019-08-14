package ua.nure.hordiienko.practice4;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Part1 {
	
	public static final String FILE= "part1.txt";
    public static final String ENC = "Cp1251";
    public static final int NUMBER = 100;
	
    private Part1() {
    }

    public static void main(String[] args) throws IOException {
        List<String> strings = Files.readAllLines(new File(FILE).toPath(), Charset.forName(ENC));

        ByteArrayOutputStream out = new ByteArrayOutputStream(NUMBER);
        PrintStream o = System.out;
        System.setOut(new PrintStream(out,true, Charset.defaultCharset().name()));
        input(strings, System.out);

        String clear = new String(out.toByteArray(), Charset.forName(Part1.ENC));

        System.setOut(o);
        System.out.print(clear);
    }

    public static void input(Iterable<String> strings , OutputStream out) throws IOException {
        StringBuffer sb = new StringBuffer();
        for (String s : strings) {
            sb.append(s).append(System.lineSeparator());
        }

        Matcher m = Pattern.compile("[\\w\\u0410-\\u044F]{3,}",
                Pattern.MULTILINE
                        | Pattern.UNICODE_CHARACTER_CLASS
                        | Pattern.CASE_INSENSITIVE).matcher(sb);

        sb = new StringBuffer();
        while (m.find()) {
        	char[] stringArray = m.group().trim().toCharArray();
        	if (stringArray.length > 3) {
        		for (int i = 0; i < stringArray.length; i++) {
        			if (Character.isLowerCase(stringArray[i])) {
        				stringArray[i] = Character.toUpperCase(stringArray[i]);
        			} else {
        				stringArray[i] = Character.toLowerCase(stringArray[i]);
        			}
        		}
        	}
        	String word = new String(stringArray);
            m.appendReplacement(sb, word);
        }
        m.appendTail(sb);

        out.write(sb.toString().getBytes(ENC));
    }

}