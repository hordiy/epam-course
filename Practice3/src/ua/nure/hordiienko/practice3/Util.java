package ua.nure.hordiienko.practice3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
 
public class Util {
     
    private static final String ENC = "Cp1251";
 
    public static String readFile(String path) {
        String r = null;
        try {
            byte[] b = Files.readAllBytes(Paths.get(path));
            r = new String(b, ENC);
        } catch (IOException exp) {
            exp.printStackTrace();
        }
        return r;
    }
 
    public static void main(String[] args) {
        System.out.println(readFile("./Part1.txt"));
        System.out.println(readFile("./Part2.txt"));
        System.out.println(readFile("./Part3.txt"));
    }

}
