

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Lemonade {
    public static void main(String[] args) throws IOException {
        Kattio io = new Kattio("lemonade");
        int N = io.nextInt();
        int num = 0;
        int[] arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = io.nextInt();
        }
        Arrays.sort(arr);
        for(int i = N - 1; i >= 0; i--){
            int cow = arr[i];
            if(cow >= num) num++;
        }
        io.println(num);
        io.close();
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
