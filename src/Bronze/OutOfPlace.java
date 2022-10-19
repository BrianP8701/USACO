import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class OutOfPlace {
    static HashSet<String> lala = new HashSet<>();
    public static void main(String[] args) throws IOException {
        Kattio test = new Kattio("outofplace");
        int N = test.nextInt();
        int[] arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = test.nextInt();
        }
        int[] sorted = Arrays.copyOf(arr, N);
        Arrays.sort(sorted);
        int x = 0;
        for(int i = 0; i < N; i++){
            //if(sorted[i] != arr[i]) wrongs.add(i);
            if(sorted[i] != arr[i]) x++;
        }
        test.println(x - 1);
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
