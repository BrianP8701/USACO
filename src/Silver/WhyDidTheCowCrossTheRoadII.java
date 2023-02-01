import java.io.*;
import java.util.StringTokenizer;

public class WhyDidTheCowCrossTheRoadII {
    public static void main(String[] args) throws IOException {
        Kattio test = new Kattio("maxcross");
        int N = test.nextInt(), K = test.nextInt(), B = test.nextInt();
        boolean[] road = new boolean[N];
        for(int i = 0; i < B; i++){
            road[test.nextInt()-1] = true;
        }

        int[] arr = new int[N];
        int track = 0;
        for(int i = 0; i < N; i++){
            if(road[i]) track++;
            arr[i] = track;
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < N-K+1; i++){
            int count = arr[i+K-1] - arr[i];
            if(count < min) min = count;
        }
        test.println(min);
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
