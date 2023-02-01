import java.io.*;
import java.util.StringTokenizer;

public class Race {
    public static void main(String[] args) throws IOException {
        Kattio test = new Kattio("race");
        int K = test.nextInt();
        int N = test.nextInt();
        double s = quadratic(1, 1, -2*K);
        int straightUp = 0;
        if(s % 1 == 0) straightUp = (int)s;
        else straightUp = (int)s + 1;
        for(int i = 0; i < N; i++){
            int X = test.nextInt();
            if(straightUp <= X) {
                test.println(straightUp);
            } else {
                double b = Math.sqrt(2*K + X*X - X);
                if (b % 1 == 0) test.println(2 * (int)b - X);
                else test.println(2 * (int)(b - b % 1) - X + 1);
            }
        }
        test.close();
    }
    public static int prefixSum(int start, int end){
        return (end*end - start*start + start + end)/2;
    }
    public static double quadratic(int a, int b, int c){
        double firstRoot = 0, secondRoot = 0;
        double determinant = (b*b)-(4*a*c);
        double sqrt = Math.sqrt(determinant);
        if(determinant>0){
            firstRoot = (-b + sqrt)/(2*a);
            // secondRoot = (-b - sqrt)/(2*a);
            return firstRoot;
        }else{
            return (-b + sqrt)/(2*a);
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
