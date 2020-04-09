import java.util.Scanner;

public class A {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
		StringBuilder res = new StringBuilder();
		while (t-- > 0) {
			int row = scan.nextInt();
			int col = scan.nextInt();
			StringBuilder out = new StringBuilder();
			out.append("W");
			for (int i = 1; i < col; i++) {
				out.append("B");
			}
			String blacks = "";
			for (int i = 0; i < col; i++) {
				blacks += "B";
			}
			for (int i = 1; i < row; i++) {
				out.append("\n"+blacks);
			}
			res.append(out+"\n");
		}
		System.out.println(res.toString());
	}

}
