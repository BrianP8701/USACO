import java.io.*;
import java.util.*;

public class WhyDidTheCowCrossTheRoad {
    public static void main(String[] args) throws IOException {
        Kattio test = new Kattio("helpcross");
        int C = test.nextInt(), N = test.nextInt();
        int[] chickens = new int[C];
        int[] start = new int[N];
        int[] end = new int[N];
        boolean[] marked = new boolean[N];
        for(int i = 0; i < C; i++){
            chickens[i] = test.nextInt();
        }
        Arrays.sort(chickens);
        for(int i = 0; i < N; i++){
            start[i] = test.nextInt();
            end[i] = test.nextInt();
        }
        int count = 0;
        for(int i = 0; i < C; i++){
            int time = -1;
            for(int j = 0; j < N; j++){
                if(!marked[j] && chickens[i] >= start[j] && chickens[i] <= end[j]){
                    if(time < 0 || end[j] < end[time]) time = j;
                }
            }
            if(time >= 0){
                marked[time] = true;
                count++;
            }
        }
        test.println(count);
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
