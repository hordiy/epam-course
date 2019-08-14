package ua.nure.hordiienko.practice4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Scanner;


public class Part2 {

	private SecureRandom random = new SecureRandom();

	private String enc = "Cp1251";
	
	private String inFName;

	private String outFName;
	
	private int n = 10;
	
	private static final int MAX = 50;

	
	Part2(String inFileName, String outFileName, String enc) {
		setInFileName(inFileName);
		setOutFileName(outFileName);
		setEnc(enc);
	}

	
	public SecureRandom getRandom() {
		return random;
	}


	public String getEnc() {
		return enc;
	}

	
	public final void setEnc(String enc) {
		this.enc = enc;
	}


	public String getInFileName() {
		return inFName;
	}

	
	public final void setInFileName(String inFileName) {
		this.inFName = inFileName;
	}

	
	public String getOutFileName() {
		return outFName;
	}

	
	public final void setOutFileName(String outFileName) {
		this.outFName = outFileName;
	}

	
	public int getN() {
		return n;
	}

	
	public int getMax() {
		return MAX;
	}

	
	public String sortNumbers(String str) {
		String[] ar = str.split(" ");
		int[] ar2 = new int[ar.length];
		for (int i = 0; i < ar.length; i++) {
			ar2[i] = Integer.valueOf(ar[i]);
		}
		Arrays.sort(ar2);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < ar.length; i++) {
			sb.append(ar2[i] + " ");
		}
		return sb.toString();
	}

	
	public String fillRandomNumbers() {
		int count = 0;
		StringBuilder sb= new StringBuilder();
		while (count != getN()) {
			sb.append(calculateRandomInt() + " ");
			count++;
		}

		return sb.toString().substring(0, sb.length()-1);
	}

	
	public int calculateRandomInt() {
		return getRandom().nextInt(getMax());

	}

	
	public void writeInFile() {
		try {
			PrintWriter pw = new PrintWriter(new File(getInFileName()),
					getEnc());
			pw.write(fillRandomNumbers());
			pw.close();
		} catch (FileNotFoundException|UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	
	public void writeOutFile() {
		try (Scanner in = new Scanner(new File(getInFileName()), getEnc());
				PrintWriter pw = new PrintWriter(new File(getOutFileName()),
						getEnc())){
			while (in.hasNextLine()) {
				pw.write(sortNumbers(in.nextLine()));
			}
		} catch (FileNotFoundException|UnsupportedEncodingException|IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	
	public String output() {
		String str = null;
		try (Scanner in = new Scanner(new File(getInFileName()), getEnc());
				Scanner out = new Scanner(new File(getOutFileName()), getEnc())){
			StringBuilder sb = new StringBuilder();
			while (in.hasNextLine()) {
				sb.append("input: " + in.nextLine() + System.lineSeparator());
			}
			while (out.hasNextLine()) {
				sb.append("output: " + out.nextLine());
			}
			str = sb.toString().substring(0, sb.length() - 1);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		return str;
	}

	
	public static void main(String[] args) {
		Part2 part2 = new Part2("part2.txt", "part2_sorted.txt", "Cp1251");
		part2.writeInFile();
		part2.writeOutFile();
		System.out.println(part2.output());

	}
}