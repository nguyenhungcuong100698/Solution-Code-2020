import java.util.Scanner;

public class B {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
		StringBuilder out = new StringBuilder();
		while (t-- > 0) {
			StringBuilder res = new StringBuilder();
			int n = scan.nextInt();
			int total = 0;
			int[] ar = new int[n + 1];
			for (int i = 1; i <= n; i++) {
				ar[i] = scan.nextInt();
			}

			int[] mark = new int[n + 1];
			int[] count = new int[n + 1];
			int maxNumber = 0;
			
			for (int i = 1; i <= n; i++) {
				count[ar[i]]++;
				maxNumber = Math.max(maxNumber, ar[i]);
				if (count[ar[i]] > 1) {
					break;
				}
				if (maxNumber == i) {
					mark[i] = maxNumber;
				}
			}

			count = new int[n + 1];
			maxNumber = 0;
			for (int j = n; j > 0; j--) {
				count[ar[j]]++;
				if (count[ar[j]] > 1) {
					break;
				}
				maxNumber = Math.max(maxNumber, ar[j]);
				if (maxNumber == (n - j + 1) && mark[j - 1] != 0) {
					total++;
					res.append(mark[j - 1] + " " + maxNumber + "\n");
				}
			}

			out.append(total + "\n" + res.toString());
		}
		System.out.println(out.toString());
		scan.close();
	}

}
