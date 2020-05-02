import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		InputReader scan = new InputReader();
		int n = scan.nextInt();
		HashMap<Integer, Hunter> negatives = new HashMap<Integer, Hunter>();
		HashMap<Integer, Hunter> positives = new HashMap<Integer, Hunter>();
		long sumNegative = 0, sumPositive = 0;
		for (int i = 0; i < n; i++) {
			int location = scan.nextInt();
			if (location == 0) {
				continue;
			}
			if (location < 0) {
				sumNegative -= location;
				Hunter current = negatives.get(location);
				if (current == null) {
					current = new Hunter(-location);
				}
				current.quantity++;
				negatives.put(location, current);
			} else {
				sumPositive += location;
				Hunter current = positives.get(location);
				if (current == null) {
					current = new Hunter(location);
				}
				current.quantity++;
				positives.put(location, current);
			}
		}

		ArrayList<Hunter> listNegatives = new ArrayList<Hunter>(negatives.values());
		ArrayList<Hunter> listpositives = new ArrayList<Hunter>(positives.values());

		listNegatives.sort((a, b) -> Long.compare(a.location, b.location));
		listpositives.sort((a, b) -> Long.compare(a.location, b.location));
		
		long result = Math.min(Calculate(listNegatives) + sumPositive, Calculate(listpositives) + sumNegative);
		System.out.println(result);
	}

	static long Calculate(ArrayList<Hunter> hunters) {
		if (hunters.isEmpty())
			return 0;
		long distanceToZero = 0, distanceToCurrent = 0;
		int i = 0, j = 1, countHunters = 0;
		Hunter last, current, other;
		for (; j < hunters.size(); j++) {
			current = hunters.get(j);// current hunter will be the portal
			last = hunters.get(j - 1);
			countHunters += last.quantity;
			distanceToCurrent += (current.location - last.location) * countHunters;
			while (i < j && current.location - (other = hunters.get(i)).location >= other.location) {// other hunter
																										// rather going
																										// to
				distanceToZero += other.location * other.quantity; // the Zero portal than the current hunter
				distanceToCurrent -= (current.location - other.location) * other.quantity;
				countHunters -= other.quantity;
				i++;
			}
			current.result = distanceToCurrent + distanceToZero;
		}

		long min = hunters.get(hunters.size() - 1).result;
		distanceToCurrent = 0;
		countHunters = 0;
		j = hunters.size() - 2;
		for (; j >= i; j--) {
			current = hunters.get(j);
			last = hunters.get(j + 1);
			countHunters += last.quantity;
			distanceToCurrent += (last.location - current.location) * countHunters;
			min = Math.min(min, current.result + distanceToCurrent);
		}
		return min;
	}

	static class Hunter {
		long location;
		int quantity = 0;
		long result = 0;

		public Hunter(int l) {
			this.location = l;
		}
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

		public char[] ns(int n) {
			char[] buf = new char[n];
			int b = skip(), p = 0;
			while (p < n && !(isSpaceChar(b))) {
				buf[p++] = (char) b;
				b = readByte();
			}
			return n == p ? buf : Arrays.copyOf(buf, p);
		}

		public char[][] nm(int n, int m) {
			char[][] map = new char[n][];
			for (int i = 0; i < n; i++) {
				map[i] = ns(m);
			}
			return map;
		}

		public int[] na(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
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