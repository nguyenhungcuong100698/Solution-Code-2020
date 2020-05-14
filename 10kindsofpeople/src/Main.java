import java.io.*;
import java.util.*;

public class Main {

	static int n;
	static int m;
	static Area[][] areas;
	static int[][] dx = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	public static void main(String[] args) throws IOException {
		InputReader scan = new InputReader();
		n = scan.nextInt();
		m = scan.nextInt();
		areas = new Area[n][m];
		for (int i = 0; i < n; i++) {
			char[] row = scan.next().toCharArray();
			for (int j = 0; j < m; j++) {
				areas[i][j] = new Area(row[j] == '1' ? 1 : 0, -1, i, j);
			}
		}
		int query = scan.nextInt();
		int[][] q = new int[query][4];
		for (int i = 0; i < query; i++) {
			q[i] = new int[] { scan.nextInt() - 1, scan.nextInt() - 1, scan.nextInt() - 1, scan.nextInt() - 1 };
		}

		int section = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!areas[i][j].visited) {
					areas[i][j].section = section++;
					bfs(areas[i][j]);
				}
			}
		}

		StringBuilder res = new StringBuilder();
		for (int i = 0; i < query; i++) {
			int x1 = q[i][0], y1 = q[i][1], x2 = q[i][2], y2 = q[i][3];
			if (areas[x1][y1].section == areas[x2][y2].section) {
				if (areas[x1][y1].number == 1) {
					res.append("decimal\n");
				} else {
					res.append("binary\n");
				}
			} else {
				res.append("neither\n");
			}
		}
		System.out.println(res.toString());
	}

	static void bfs(Area a) {
		Queue<Area> q = new LinkedList<Area>();
		q.add(a);
		a.visited = true;
		while (!q.isEmpty()) {
			Area current = q.poll();
			for (int i = 0; i < 4; i++) {
				if (validArea(current.x + dx[i][0], current.y + dx[i][1])) {
					Area newArea = areas[current.x + dx[i][0]][current.y + dx[i][1]];
					if (current.number == newArea.number) {
						newArea.section = current.section;
						q.add(newArea);
						newArea.visited = true;//remember to set visited here. Otherwise we'll have duplicates in queue
											  // do not set visited after polling, duplicates mean TLE
					}
				}
			}
		}
	}

	static boolean validArea(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < m && !areas[x][y].visited;
	}

	static class Area {
		int number;
		int section;
		int x;
		int y;
		boolean visited = false;

		public Area(int num, int section, int x, int y) {
			this.number = num;
			this.section = section;
			this.x = x;
			this.y = y;
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
