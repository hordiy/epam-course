package ua.nure.hordiienko.practice1;

public class Part6 {

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[] sArr = new int[n];
		int nNumb = 2;
		int coun = 0;
		boolean isSimp;

		while (coun < n) {
			int i = 0;
			isSimp = true;

			while (isSimp && i < coun) {
				if (nNumb % sArr[i] == 0) {
					isSimp = false;
				}
				i++;
			}
			if (isSimp) {
				sArr[coun] = nNumb;
				if (sArr[sArr.length-1] == nNumb) {
				System.out.println(sArr[coun]);
				} else {
					System.out.print(sArr[coun] + " ");
				}
				coun++;
				nNumb++;
	
			} else {
				nNumb++;
			}
		}
	}
}
