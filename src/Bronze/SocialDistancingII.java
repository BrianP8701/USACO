import java.io.*;
import java.util.*;

public class SocialDistancingII {
    public static void main(String[] args) throws IOException {
        Kattio test = new Kattio("socdist2");
        int N = test.nextInt();
        HashMap<Integer, int[]> cowToPos = new HashMap<>();
        HashMap<Integer, Integer> posToCow = new HashMap<>();
        int[] pos = new int[N];
        ArrayList<Integer> sickCows = new ArrayList<>();

        for(int i = 0; i < N; i++){
            int[] temp = {test.nextInt(), test.nextInt()};
            cowToPos.put(i, temp);
            posToCow.put(temp[0], i);
            if(temp[1] == 1) {
                sickCows.add(temp[0]);
            }
            pos[i] = temp[0];
        }

        // Get R
        Arrays.sort(pos);
        int R = Integer.MAX_VALUE;
        for(int i = 0; i < N-1; i++){
            if(cowToPos.get(posToCow.get(pos[i]))[1] != cowToPos.get(posToCow.get(pos[i+1]))[1]){
                int r = pos[i + 1] - pos[i] - 1;
                if(r < R) R = r;
            }
        }

        Collections.sort(sickCows);
        int sC = sickCows.size() - 1;
        int[] distBetween = new int[sC];
        for(int i = 0; i < sC; i++){
            distBetween[i] = sickCows.get(i+1) - sickCows.get(i);
        }
        int count = 1;
        for(int i = 0; i < sC; i++){
            if(distBetween[i] > R) count++;
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
