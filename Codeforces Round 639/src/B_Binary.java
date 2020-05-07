import java.io.*;
import java.util.*;

public class B_Binary {

	public static void main(String[] args) throws IOException {
		InputReader scan = new InputReader();
		int t = scan.nextInt();
		while (t-- > 0) {
			long n = scan.nextLong();
			int count = 0;
			while (n > 1) {
				n -= BinarySearch(n);
				count++;
			}
			System.out.println(count);
		}

	}

	static long BinarySearch(long n) {
		long left = 1, right = 26000;
		while (left < right) {
			long mid = (left + right) / 2;
			long cards = calCards(mid);
			if (cards > n) {
				if (calCards(mid - 1) <= n) {
					return calCards(mid - 1);
				}
				right = mid;
			} else {
				left = mid;
			}
		}
		return calCards(left);
	}

	static long calCards(long height) {
		return (3 * height * height + height) / 2;
	}

	static class InputReader {

		InputStream is = System.in;
		byte[] inbuf = new byte[1 << 25];
		int lenbuf = 0, ptrbuf = 0;

		public InputReader() throws IOException {
			lenbuf = is.read(inbuf);
		}

		public int readByte() {

			if (ptrbuf >= lenbuf) {
				return -1;
			}

			return inbuf[ptrbuf++];
		}

		public boolean hasNext() {
			int t = skip();

			if (t == -1) {
				return false;
			}
			ptrbuf--;
			return true;
		}

		public boolean isSpaceChar(int c) {
			return !(c >= 33 && c <= 126);
		}

		public int skip() {
			int b;
			while ((b = readByte()) != -1 && isSpaceChar(b))
				;
			return b;
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}

		public char nextChar() {
			return (char) skip();
		}

		public String next() {
			int b = skip();
			StringBuilder sb = new StringBuilder();
			while (!(isSpaceChar(b))) {
				sb.appendCodePoint(b);
				b = readByte();
			}
			return sb.toString();
		}

		public int nextInt() {
			int num = 0, b;
			boolean minus = false;
			while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
				;
			if (b == '-') {
				minus = true;
				b = readByte();
			}

			while (true) {
				if (b >= '0' && b <= '9') {
					num = num * 10 + (b - '0');
				} else {
					return minus ? -num : num;
				}
				b = readByte();
			}
		}

		public long nextLong() {
			long num = 0;
			int b;
			boolean minus = false;
			while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
				;
			if (b == '-') {
				minus = true;
				b = readByte();
			}

			while (true) {
				if (b >= '0' && b <= '9') {
					num = num * 10 + (b - '0');
				} else {
					return minus ? -num : num;
				}
				b = readByte();
			}
		}

	}

}
