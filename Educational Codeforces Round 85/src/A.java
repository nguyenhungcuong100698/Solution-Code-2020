import java.util.*;
import java.io.*;

public class A {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
		StringBuilder res = new StringBuilder();
		while (t-- > 0) {
			int n = scan.nextInt();
			int lastPlays = 0, lastClears = 0;
			boolean possible = true;
			for (int i = 0; i < n; i++) {
				int plays = scan.nextInt();
				int clears = scan.nextInt();
				if (plays < lastPlays || clears < lastClears || plays - lastPlays < clears - lastClears) {
					possible = false;
				}
				lastPlays = plays;
				lastClears = clears;
			}
			res.append(possible ? "YES\n" : "NO\n");
		}
		System.out.println(res.toString());
	}

}
