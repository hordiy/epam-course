package ua.nure.hordiienko.practice1;

public class Part7 {
	
	public static final int S_FOUR = 64;
    public static final int T_SIX = 26;
    
    public static int str2int(String numb) {
	    int digit = 0;
	    
	    for (int i = 1, j = numb.length(); j > 0; i++, j--) {
	      digit += (numb.charAt(numb.length() - i) - S_FOUR) * Math.pow(T_SIX, numb.length() - (double)j);
	    }
	    return digit;
  }

    public static String int2str(int num) {
	    StringBuilder cha = new StringBuilder();
	    StringBuilder chaMir = new StringBuilder();
	    int m;
	    int div = num;
	    while (div != 0) {
	      m = div % T_SIX;
	      if (m == 0) {
	        cha.append("Z");
	        div = (div - 1) / T_SIX;
	      } else {
	        cha.append((char) (m + S_FOUR));
	        div = (div - m) / T_SIX;
	      }
	    }
	
	    for (int i = 0; i < cha.length(); i++) {
	      chaMir.append(cha.charAt(cha.length() - i - 1));
	    }
	    return chaMir.toString();
  }

    public static String rightColumn(String number) {
	    String cha = "";
	    int numb;
	    numb = str2int(number);
	    numb++;
	    cha = int2str(numb);
	    return cha;
  }

    public static void main(String[] args) {
	  
	  final String transf = " ==> ";
	  
	  System.out.println("A" + transf + str2int("A") + transf + int2str(str2int("A")));
	  System.out.println("B" + transf + str2int("B") + transf + int2str(str2int("B")));
	  System.out.println("Z" + transf + str2int("Z") + transf + int2str(str2int("Z")));
	  System.out.println("AA" + transf + str2int("AA") + transf + int2str(str2int("AA")));
	  System.out.println("AZ" + transf + str2int("AZ") + transf + int2str(str2int("AZ")));
	  System.out.println("BA" + transf + str2int("BA") + transf + int2str(str2int("BA")));
	  System.out.println("ZZ" + transf + str2int("ZZ") + transf + int2str(str2int("ZZ")));
	  System.out.println("AAA" + transf + str2int("AAA") + transf + int2str(str2int("AAA")));
    
  }
}
