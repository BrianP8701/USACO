import java.io.*;
import java.util.*;

public class BucketBrigade {
    public static void main(String[] args) throws IOException {
        Kattio test = new Kattio("buckets");
        int Lx = 0, Ly = 0, Bx = 0, By = 0, Rx = 0, Ry = 0;
        for(int i = 0; i < 10; i++){
            String s = test.next();
            for(int j = 0; j < 10; j++){
                String ss = String.valueOf(s.charAt(j));
                if(ss.equals("L")){
                    Lx = j; Ly = i;
                }
                if(ss.equals("R")){
                    Rx = j; Ry = i;
                }
                if(ss.equals("B")){
                    Bx = j; By = i;
                }
            }
        }
        int answer = Math.abs(Lx - Bx) + Math.abs(Ly - By) - 1;
        if(By == Ly && By == Ry && ((Lx < Rx && Rx < Bx) || (Bx < Rx && Rx < Lx))){
            answer += 2;
        }
        if(Bx == Lx && Bx == Rx && ((Ly < Ry && Ry < By) || (By < Ry && Ry < Ly))){
            answer += 2;
        }
        test.println(answer);
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
