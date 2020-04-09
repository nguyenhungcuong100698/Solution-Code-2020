import java.util.*;

public class A {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		StringBuilder res = new StringBuilder();
		int t = scan.nextInt();
		while (t-- > 0) {
			int n = scan.nextInt();
			int x = scan.nextInt();

			boolean[] mark = new boolean[300];
			for (int i = 0; i < n; i++) {
				int place = scan.nextInt();
				mark[place] = true;
			}

			for (int i = 1; i < 300; i++) {
				if (mark[i] == false) {
					x--;
				}

				if (x == 0) {
					while (mark[i + 1]) {
						i++;
					}
					res.append(i + "\n");
				}
			}
		}
		System.out.println(res.toString());
	}

}
