import java.io.*;
import java.util.*;

public class Main {

	static int tankSize = 200;
    static int lastLocation = 0;
    static int currentGas = tankSize / 2;
    static Stack<Station> passedStations = new Stack<Station>();
    static int money = 0;

    public static void main(String[] args) throws IOException {
    	InputReader scan = new InputReader();
        int destination = scan.nextInt();

        int location = 0, price = 0;
        while (scan.hasNext()) {
            location = scan.nextInt();
            price = scan.nextInt();
            currentGas -= (location - lastLocation);
            if (currentGas < 0) {
                System.out.println("Impossible");
                return;
            }

            refund(price);
        
            money += (tankSize - currentGas) * price;
            passedStations.add(new Station(price, tankSize - currentGas));
            currentGas = tankSize;
            lastLocation = location;
        }
        currentGas -= (destination - location);
        currentGas -= tankSize / 2;
        refund(0);
        System.out.println(currentGas < 0 ? "Impossible" : money);
    }

    static void refund(int price) {
        Station refundStation;
        while (currentGas > 0 && !passedStations.isEmpty() && (refundStation = passedStations.peek()).price > price) {
            int refundGas = Math.min(currentGas, refundStation.refund);
            money -= refundGas * refundStation.price;
            currentGas -= refundGas;
            passedStations.pop();
        }
    }

    static class Station {
        public int price;
        public int refund = 0;

        public Station(int price, int refund) {
            this.price = price;
            this.refund = refund;
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
