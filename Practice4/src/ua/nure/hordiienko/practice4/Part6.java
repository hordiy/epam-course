package ua.nure.hordiienko.practice4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {
	
	private static final String ENC = "Cp1251";
	
	private static final String LATN = "[a-zA-Z]+";
    private static final String CYRL = "[\\u0410-\\u044F¨¸¯¿²³ªº¥´']+";
    
    private static final String[] L = {"latn", "Latn"};
	private static final String[] C = {"cyrl", "Cyrl"};

	private String fileName;

	Part6(String fileName) {
		setFileName(fileName);
	}

	public String getFileName() {
		return fileName;
	}

	public final void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
    public String getString() throws IOException {
		StringBuilder sb = new StringBuilder();
		try {
			List<String> strings = Files.readAllLines(new File(getFileName()).toPath(), Charset.forName(ENC));
			for (String s : strings) {
				sb.append(s).append(" ");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public String getCharValue() throws IOException {
		StringBuilder sb = new StringBuilder();
		Pattern p = Pattern.compile(LATN);
		Matcher m = p.matcher(getString());
		while (m.find()) {
			sb.append(m.group() + " ");
		}
		return sb.toString().substring(0, sb.length() - 1);

	}

	public String getStringValue() throws IOException {
		StringBuilder sb = new StringBuilder();
		Pattern p = Pattern.compile(CYRL);
		Matcher m = p.matcher(getString());
		while (m.find()) {
			sb.append(m.group() + " ");
		}
		return sb.toString().substring(0, sb.length() - 1);

	}
	
	public void input() throws IOException {
		Scanner s = new Scanner(System.in, ENC);
		while (s.hasNext()) {
			String str = s.nextLine();
			if (str.equals(L[0]) || str.equals(L[1])) {
				System.out.println(str + ": " + getCharValue());
			} else if (str.equals(C[0]) || str.equals(C[1])) {
				System.out.println(str + ": " + getStringValue());
			} else if ("stop".equalsIgnoreCase(str)) {
				return;
			} else {
				System.out.println(str + ": " + "Incorrect input");
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Part6 part6 = new Part6("part6.txt");
		part6.input();
	}
}
