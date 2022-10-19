

import java.io.*;
import java.util.StringTokenizer;

public class MadScientist {
    public static void main(String[] args) throws IOException {
        Kattio test = new Kattio("breedflip");
        int N = test.nextInt();
        String A = test.next(), B = test.next();
        boolean[] correct = new boolean[N];
        boolean[] delivery = new boolean[N];
        for(int i = 0; i < N; i++){
            String s = String.valueOf(A.charAt(i));
            correct[i] = s.equals("G") ? true : false;
        }
        for(int i = 0; i < N; i++){
            String s = String.valueOf(B.charAt(i));
            delivery[i] = s.equals("G") ? true : false;
        }
        int min = 0;
        boolean swapping = false;
        for(int i = 0; i < N; i++){
            if(correct[i] == (delivery[i]) && swapping){
                min++;
                swapping = false;
            }
            if(!correct[i] == (delivery[i])) swapping = true;
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
