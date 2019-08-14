package ua.nure.hordiienko.practice6.part6;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class Part6 {
    
	private String fName;
	private String[] ar;
	
	private Part6() {	
	}
	
	public String[] getAr() {
		return ar.clone();
	}

	public void setAr(String[] ar) {
		this.ar = ar.clone();
	}
    
    public static void main(String[] args) {
        new Part6().cons(args[0], args[1], args[2], args[3]);
    }
    
    private boolean cons(String input, String fName, String t, String op) {
        if (!("--input".equals(input) || "-i".equals(input))) {
            System.err.println("Wrong operation");
            return false;
        }
        if (!("--task".equals(t) || "-t".equals(t))) {
            System.err.println("Wrong task");
            return false;
        }
        this.fName = fName;
        initialize();
        switch (op) {
            case "frequency":
                Part61.getRFrequecy(getAr());
                break;
            case "length":
                Part62.getRLength(getAr());
                break;
            case "duplicates":
                Part63.getRDublicates(getAr());
                break;
            default:
                return false;
        }
        return true;
    }
   
    private String getInput() {
        StringBuilder sb = new StringBuilder();
        try (Scanner file = new Scanner(new File(fName), "CP1251")) {
            while (file.hasNext()) {
                sb.append(file.next()).append(" ");
            }
        } catch (FileNotFoundException e) {
            System.err.println(String.format("File: %s not found", fName));
        }
        return sb.toString();
    }
    
    public void initialize() {
        StringBuilder sb = new StringBuilder();
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(getInput());
        while (m.find()) {
            sb.append(m.group() + " ");
        }
        setAr(sb.toString().split(" "));
    }
}