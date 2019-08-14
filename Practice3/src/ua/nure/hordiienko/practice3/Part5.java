package ua.nure.hordiienko.practice3;

public final class Part5 {
		
    private static final String[] ROMA =
            new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    private static final int[] ARAB =
            new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    private Part5() {
		throw new IllegalStateException("Utility class");
	}
    
    public static String decimal2Roman(int d) {
        int x = d;

        StringBuilder s = new StringBuilder();

        for (int i = 0; i < ROMA.length; i++) {
            while (x >= ARAB[i]) {
                s.append(ROMA[i]);
                x -= ARAB[i];
            }
        }
        return s.toString();
    }

    public static int roman2Decimal(String d) {
        StringBuilder ro = new StringBuilder(d);

        int ar = 0;
        int num = 0;
        
        while (ro.length() != 0) {
            
            if (ro.indexOf(ROMA[num]) == 0) {
                ar += ARAB[num];
                ro.delete(0, ROMA[num].length());
                continue;
            }
            num++;
        }
        return ar;
    }

}
