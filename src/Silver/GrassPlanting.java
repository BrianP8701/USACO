import java.io.*;
import java.util.*;

public class GrassPlanting {
    public static void main(String[] args) throws IOException {
        Kattio test = new Kattio("planting");
        int N = test.nextInt();
        int[] grass = new int[N+1];
        for(int i = 0; i < N-1; i++){
            int a = test.nextInt(), b = test.nextInt();
            grass[a]++; grass[b]++;
        }
        int grassMax = 0;
        for(int i = 1; i <= N; i++){
            grassMax = Math.max(grassMax, grass[i]);
        }
        test.println(grassMax+1);
        test.close();
    }
    static class Kattio extends PrintWriter {
        private BufferedReader r;
        private StringTokenizer st;

        public Kattio() { this(System.in, System.out); }
        public Kattio(InputStream i, OutputStream o) {
            super(o);
            r = new BufferedReader(new InputStreamReader(i));
        }
        // USACO-style file input
        public Kattio(String problemName) throws IOException {
            super(new FileWriter(problemName + ".out"));
            r = new BufferedReader(new FileReader(problemName + ".in"));
        }

        // returns null if no more input
        public String next() {
            try {
                while (st == null || !st.hasMoreTokens())
                    st = new StringTokenizer(r.readLine());
                return st.nextToken();
            } catch (Exception e) { }
            return null;
        }

        public int nextInt() { return Integer.parseInt(next()); }
        public double nextDouble() { return Double.parseDouble(next()); }
        public long nextLong() { return Long.parseLong(next()); }
    }
}
