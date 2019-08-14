package ua.nure.hordiienko.practice4;

import java.io.File;


public class Part4 {


	private String fileName;

	Part4(String filename) {
		setFileName(filename);
	}

	public String getFileName() {
		return fileName;
	}

	public final void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void out() {
		Parser pars = new Parser(new File(getFileName()));
		for (String s : pars) {
			System.out.println(s);
		}

	}

	public static void main(String[] args) {
		new Part4("part4.txt").out();
	}
}