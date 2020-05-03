import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		int n = scan.nextInt();
		if (n < 3) {
			System.out.println(0);
			return;
		}
		int[] roofs = new int[n];
		int highestRoof = 0, lowestRoof = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			roofs[i] = scan.nextInt();
			if (i != 0 && i != n - 1) {
				highestRoof = Math.max(highestRoof, roofs[i]);
				lowestRoof = Math.min(lowestRoof, roofs[i]);
			}
		}
		long standingWater = 0;
		if (highestRoof == lowestRoof) {
			int min = Math.min(roofs[0], roofs[n - 1]);
			if (highestRoof < min) {
				standingWater = (min - highestRoof) * (long)(n - 2);
			} else {
				standingWater = 0;
			}
			System.out.println(standingWater);
			return;
		}

		roofs[0] = Math.min(roofs[0], highestRoof);
		roofs[n - 1] = Math.min(roofs[n - 1], highestRoof);

		int mark = 0;
		for (int i = 1; i < n - 1; i++) {
			if (roofs[i] < roofs[mark]) {
				standingWater += (roofs[mark] - roofs[i]);
			} else {
				mark = i;
			}
		}

		if (roofs[n - 1] < roofs[mark]) {
			int j = n - 1;
			for (int i = n - 2; i > mark; i--) {
				if (roofs[i] < roofs[j]) {
					standingWater -= (roofs[mark] - roofs[j]);
				} else {
					standingWater -= (roofs[mark] - roofs[i]);
					j = i;
				}
			}
		}
		System.out.println(standingWater);
	}

	static FastInputReader scan = new FastInputReader(System.in);

	static class FastInputReader {
		byte[] inbuf = new byte[1 << 25];
		int lenbuf = 0, ptrbuf = 0;
		InputStream is;

		public FastInputReader(InputStream stream) {
			is = stream;
		}

		int readByte() {
			if (lenbuf == -1)
				throw new InputMismatchException();
			if (ptrbuf >= lenbuf) {
				ptrbuf = 0;
				try {
					lenbuf = is.read(inbuf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (lenbuf <= 0)
					return -1;
			}
			return inbuf[ptrbuf++];
		}

		public boolean hasNext() {
			return ptrbuf + 3 < lenbuf;
		}

		boolean isSpaceChar(int c) {
			return !(c >= 33 && c <= 126);
		}

		int skip() {
			int b;
			while ((b = readByte()) != -1 && isSpaceChar(b))
				;
			return b;
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

		public char[] next(int n) {
			char[] buf = new char[n];
			int b = skip(), p = 0;
			while (p < n && !(isSpaceChar(b))) {
				buf[p++] = (char) b;
				b = readByte();
			}
			return n == p ? buf : Arrays.copyOf(buf, p);
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		char nextChar() {
			return (char) skip();
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

		long nextLong() {
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