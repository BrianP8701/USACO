

import java.io.*;
import java.util.*;

public class HighCardWins {
    public static void main(String[] args) throws IOException {
        Kattio test = new Kattio("highcard");
        int N = test.nextInt();
        boolean[] elsieOwns = new boolean[N * 2 + 1];
        for(int i = 0; i < N; i++){
            elsieOwns[test.nextInt()] = true;
        }
        ArrayList<Integer> elsie = new ArrayList<>();
        ArrayList<Integer> bessie = new ArrayList<>();
        for(int i = 1; i <= N * 2; i++){
            if(elsieOwns[i]) elsie.add(i);
            else bessie.add(i);
        }
        int wins = 0;
        int bIndex = 0, eIndex = 0;
        while(bIndex < N && eIndex < N){
            if(bessie.get(bIndex) > elsie.get(eIndex)){
                wins++;
                bIndex++;
                eIndex++;
            } else{
                bIndex++;
            }
        }
        test.println(wins);
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
