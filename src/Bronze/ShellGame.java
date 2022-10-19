import java.io.*;
import java.util.StringTokenizer;

public class ShellGame {
    public static void main(String[] args) throws IOException {
        Kattio test = new Kattio("shell");
        int N = test.nextInt();
        int[][] matrix = new int[N][3];
        for(int i = 0; i < N; i++){
            matrix[i][0] = test.nextInt();
            matrix[i][1] = test.nextInt();
            matrix[i][2] = test.nextInt();
        }
        int max = 0;
        for(int i = 1; i < 4; i++){
            int sum = 0;
            int correctAnswer = i;
            for(int j = 0; j < N; j++){
                if(matrix[j][0] == correctAnswer) correctAnswer = matrix[j][1];
                else if(matrix[j][1] == correctAnswer) correctAnswer = matrix[j][0];
                if(correctAnswer == matrix[j][2]) sum++;
            }
            if(sum > max) max = sum;
        }
        test.println(max);
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
