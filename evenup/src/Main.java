import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		Stack<Integer> st = new Stack<>();
		for (int i = 0; i < n; i++) {
			int number = scan.nextInt();
			if (!st.isEmpty() && (number & 1) == (st.peek() & 1)) {
				st.pop();
			} else {
				st.add(number);
			}
		}

		System.out.println(st.size());
	}

}
