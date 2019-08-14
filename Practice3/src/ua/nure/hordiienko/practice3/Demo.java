// encoding = Cp1251

package ua.nure.hordiienko.practice3;

import java.security.NoSuchAlgorithmException;


public final class Demo {

    private static final String FOLDER = "./";
    private static final String INPUT_PART_1 = Util.readFile(FOLDER + "part1.txt");
    private static final String INPUT_PART_2 = Util.readFile(FOLDER + "part2.txt");
    private static final String INPUT_PART_3 = Util.readFile(FOLDER + "part3.txt");
    private static final String INPUT_PART_4 = Util.readFile(FOLDER + "part4.txt");

    private Demo() {

    }

    public static void main(String[] args) throws NoSuchAlgorithmException {

        System.out.println(Part1.convert1(INPUT_PART_1));
        System.out.println();

        System.out.println(Part1.convert2(INPUT_PART_1));
        System.out.println();

        System.out.println(Part1.convert3(INPUT_PART_1));
        System.out.println();

        System.out.println(Part1.convert4(INPUT_PART_1));
        System.out.println();

        System.out.println(Part2.convert(INPUT_PART_2));
        System.out.println();

        System.out.println(Part3.convert(INPUT_PART_3));
        System.out.println();

        Part4.main(args);
        System.out.println();

        for (int i = 1; i <= 10; i++) {
            String s = Part5.decimal2Roman(i);
            System.out.println(i + " ====> " + s + " ====> " + Part5.roman2Decimal(s));
        }
        System.out.println();
        
        System.out.println(Part6.convert(INPUT_PART_4));
        System.out.println();

    }

}