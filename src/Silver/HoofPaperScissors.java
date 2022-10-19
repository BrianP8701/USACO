import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class HoofPaperScissors {
    public static void main(String[] args) throws IOException {
        Kattio test = new Kattio("hps");
        HashMap<String, Integer> map = new HashMap<>();
        map.put("H", 1); map.put("P", 2); map.put("S", 0);
        int N = test.nextInt();
        int[] arr = new int[N];
        int H = 0, P = 0, S = 0;
        int[] hoof = new int[N];
        int[] paper = new int[N];
        int[] scissor = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = map.get(test.next());
            if(arr[i] == 0) H++;
            else if(arr[i] == 1) P++;
            else S++;
            hoof[i] = H; paper[i] = P; scissor[i] = S;
        }
        int max = 0;
        int[][] matrix = {hoof, paper, scissor};
        int[][] order = {{0, 0}, {1, 1}, {2, 2}, {0, 1}, {0, 2}, {1, 0}, {1, 2}, {2, 0}, {2, 1}};
        for(int i = 0; i < N; i++){
            for(int j = 0; j < 9; j++){
                int temp = matrix[order[j][0]][i] + matrix[order[j][1]][N-1] - matrix[order[j][1]][i];
                if(temp > max) max = temp;
            }
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
