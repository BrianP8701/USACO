


public class Marathon {
    public static void main(String[] args) throws IOException {
        Kattio test = new Kattio("marathon");
        int N = test.nextInt();
        int maxDifference = Integer.MIN_VALUE;
        int[][] checkpoints = new int[N][2];
        for(int i = 0; i < N; i++){
            int[] temp = {test.nextInt(), test.nextInt()};
            checkpoints[i] = temp;
        }
        int totalDistance = distance(checkpoints[N-2][0], checkpoints[N-2][1], checkpoints[N-1][0], checkpoints[N-1][1]);
        for(int i = 0; i < N-2; i++){
            int[] p1 = checkpoints[i], p2 = checkpoints[i+1], p3 = checkpoints[i+2];
            totalDistance += distance(p1[0], p1[1], p2[0], p2[1]);
            int d1 = distance(p1[0], p1[1], p2[0], p2[1]) + distance(p2[0], p2[1], p3[0], p3[1]);
            int d2 = distance(p1[0], p1[1], p3[0], p3[1]);
            if(d1 - d2 > maxDifference){
                maxDifference = d1-d2;
            }
        }
        if(maxDifference > 0) totalDistance -= maxDifference;
        test.println(totalDistance);
        test.close();
    }
    public static int distance(int x, int y, int a, int b){
        return Math.abs(x-a) + Math.abs(y-b);
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
