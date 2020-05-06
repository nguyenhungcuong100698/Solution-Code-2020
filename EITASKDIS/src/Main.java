import java.io.*;
import java.util.*;

class Main {

	public static void main(String[] args) throws IOException {
		InputReader scan = new InputReader();
		int n = scan.nextInt();
		int m = scan.nextInt();
		PriorityQueue<Person> workers = new PriorityQueue<Person>();
		for (int i = 0; i < n; i++) {
			workers.add(new Person(i, 0));
		}

		ArrayList<Long> works = new ArrayList<Long>();
		for (int i = 0; i < m; i++) {
			works.add(scan.nextLong());
		}

		works.sort((a, b) -> Long.compare(b, a));

		// assigning works
		for (int i = 0; i < m; i++) {
			Person person = workers.poll();
			person.time += works.get(i);
			workers.add(person);
		}

		ArrayList<Person> list = new ArrayList<Person>(workers);
		list.sort((a, b) -> a.id - b.id);

		StringBuilder res = new StringBuilder();
		for (Person p : list) {
			res.append(p.time + " ");
		}
		System.out.println(res.toString());
	}

	static class Person implements Comparable<Person> {
		int id;
		long time;

		public Person(int id, long time) {
			this.id = id;
			this.time = time;
		}

		@Override
		public int compareTo(Person other) {
			if (this.time == other.time) {
				return this.id - other.id;
			}
			return Long.compare(this.time, other.time);
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
