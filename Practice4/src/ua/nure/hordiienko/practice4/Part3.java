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


public class Part3 {

	private static final String ENC = "Cp1251";
	
	private static final String INTEGER = "(^|\\s)(\\d+)(\\s|$)";
    private static final String DOUBLE = "(^|\\s)([\\d+]*\\.\\d*)(\\s|$)";
    private static final String CHAR = "(?i)(^|(?<=\\s))[\\u0410-\\u044Fa-zA-Z]($|(?=\\s))";
    private static final String STRING = "[\\u0410-\\u044Fa-zA-Z]{2,}";
	
	private String fileName;

	Part3(String fileName) {
		setFileName(fileName);
	}

	public String getFileName() {
		return fileName;
	}

	public final void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
    public String getString() throws IOException {
		StringBuilder s = new StringBuilder();
		try {
			List<String> strings = Files.readAllLines(new File(getFileName()).toPath(), Charset.forName(ENC));
			for (String s1 : strings) {
				s.append(s1).append(" ");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return s.toString();
	}

	public String getIntegerValue() throws IOException {
		StringBuilder sb = new StringBuilder();
		Pattern p = Pattern.compile(INTEGER);
		Matcher m = p.matcher(getString());
		while (m.find()) {
			sb.append(m.group(2) + " ");
		}
		return sb.toString().substring(0, sb.length() - 1);
	}

	public String getDoubleValue() throws IOException {
		StringBuilder sb = new StringBuilder();
		Pattern p = Pattern.compile(DOUBLE);
		Matcher m = p.matcher(getString());
		while (m.find()) {
			sb.append(m.group(2) + " ");
		}
		return sb.toString().substring(0, sb.length() - 1);

	}

	public String getCharValue() throws IOException {
		StringBuilder sb = new StringBuilder();
		Pattern p = Pattern.compile(CHAR);
		Matcher m = p.matcher(getString());
		while (m.find()) {
			sb.append(m.group() + " ");
		}
		return sb.toString().substring(0, sb.length() - 1);

	}

	public String getStringValue() throws IOException {
		StringBuilder sb = new StringBuilder();
		Pattern p = Pattern.compile(STRING);
		Matcher m = p.matcher(getString());
		while (m.find()) {
			sb.append(m.group() + " ");
		}
		return sb.toString().substring(0, sb.length() - 1);

	}

	public void input() throws IOException {
		Scanner input = new Scanner(System.in, ENC);
		while (input.hasNext()) {
			String str = input.nextLine();
				try {
					switch(str) {
					case "int":
						System.out.println(getIntegerValue());
						continue;
					case "double":
						System.out.println(getDoubleValue());
						continue;
					case "char":
						System.out.println(getCharValue());
						continue;
					case "String":
						System.out.println(getStringValue());
						continue;
					case "stop":
						return;
					default:
						System.out.println("Incorrect input");
					}
				} catch(StringIndexOutOfBoundsException ex) {
					System.out.println("No such values");
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Part3 part3 = new Part3("part3.txt");
		part3.input();
	}
}