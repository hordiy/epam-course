package ua.nure.hordiienko.practice3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Part4 {

	public static final int NUMBER = 16;
	
    public static void main(String[] args) {
        try {
            System.out.println(hash("asdf", "MD5"));
            System.out.println(hash("asdf", "SHA-256"));
        } catch (NoSuchAlgorithmException ex) {
           System.out.println(ex.getMessage());
        }
    }

    public static String hash(String in, String alg) throws NoSuchAlgorithmException {
        MessageDigest d = MessageDigest.getInstance(alg);
        d.update(in.getBytes());

        byte[] hash = d.digest();
        char[] chars = new char[hash.length * 2];

        for (int i = 0; i < hash.length; i++) {
            toChars(hash[i], chars, i);
        }

        return new String(chars);
    }

    private static void toChars(byte b, char[] chars, int i) {
        int up = b & 0b1111_0000;
        up = Math.abs(up / NUMBER);
        chars[i * 2] = toHex(up);

        int low = b & 0b0000_1111;
        chars[i * 2 + 1] = toHex(low);
    }

    private static char toHex(int a) {
        return (a < 10) ? (char) (a + '0') : (char) (a + 'A' - 10);
    }
}
