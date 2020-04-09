import java.util.Scanner;

public class B {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
		StringBuilder res = new StringBuilder();
		while (t-- > 0) {
			int n = scan.nextInt();
			int[] a = new int[n];
			int[] b = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = scan.nextInt();
			}
			for (int i = 0; i < n; i++) {
				b[i] = scan.nextInt();
			}
			if (a[0] != b[0]) {
				// result => NO
				res.append("NO\n");
				continue;
			}

			boolean IsOneAppeared = false;
			boolean IsMinusOneAppeared = false;
			boolean possible = true;
			for (int i = 0; i < n; i++) {
				if (b[i] - a[i] > 0 && !IsOneAppeared) {
					possible = false;
					break;
				}
				if (b[i] - a[i] < 0 && !IsMinusOneAppeared) {
					possible = false;
					break;
				}
				if (a[i] == 1) {
					IsOneAppeared = true;
				}
				if (a[i] == -1) {
					IsMinusOneAppeared = true;
				}
			}
			res.append(possible ? "YES\n" : "NO\n");
		}
		System.out.println(res.toString());
	}

}
