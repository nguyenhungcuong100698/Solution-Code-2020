import java.util.*;
import java.io.*;

public class B {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
		while (t-- > 0) {
			int n = scan.nextInt();
			int x = scan.nextInt();
			ArrayList<Integer> poor = new ArrayList<Integer>();
			long sum = 0;
			for (int i = 0; i < n; i++) {
				int savings = scan.nextInt();
				if (savings >= x) {
					sum += savings;
				} else {
					poor.add(savings);
				}
			}
			int count = n - poor.size();
			poor.sort((a, b) -> b - a);
			for (int i : poor) {
				if ((double)(sum + i)/(count + 1) >= x) {
					sum += i;
					count++;
				} else {
					break;
				}
			}
			System.out.println(count);
		}
	}

}
