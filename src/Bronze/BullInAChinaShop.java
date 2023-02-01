import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BullInAChinaShop {
    static int N;
    static int[][] original;
    public static void main(String[] args) throws IOException {
        Kattio test = new Kattio("bcs");
        N = test.nextInt();
        int K = test.nextInt();
        original = new int[N][N];
        for(int i = 0; i < N; i++){
            String s = test.next();;
            for(int j = 0; j < N; j++){
                if(s.charAt(j) == '.') {
                    original[i][j] = 0;
                } else{
                    original[i][j] = 1;
                }
            }
        }
        List<int[][]> allFragments = new ArrayList<>();
        for(int image = 0; image < K; image++){

            // Reads in fragment
            int[][] fragment = new int[N][N];
            for(int i = 0; i < N; i++){
                String s = test.next();
                for(int j = 0; j < N; j++){
                    if(s.charAt(j) == '.') {
                        fragment[i][j] = 0;
                    } else{
                        fragment[i][j] = 1;
                    }
                }
            }



            allFragments.add(fragment);
        }

        test.close();
    }
    static Point topRightCorner(int[][] matrix){
        int rightMin = Integer.MAX_VALUE, topMax = Integer.MIN_VALUE;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){

            }
        }
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
