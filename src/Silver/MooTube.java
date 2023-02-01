import java.io.*;
import java.util.*;

public class MooTube {
    static HashMap<Integer, List<int[]>> graph = new HashMap<>();
    public static void main(String[] args) throws IOException {
        Kattio test = new Kattio("mootube");
        int N = test.nextInt(), Q = test.nextInt();
        for(int i = 0; i < N-1; i++){
            int a = test.nextInt(), b = test.nextInt(), r = test.nextInt();
            int[] A = {a, r}, B = {b, r};
            if(graph.containsKey(a)){
                graph.get(a).add(B);
            } else{
                ArrayList<int[]> temp = new ArrayList<>();
                temp.add(B);
                graph.put(a, temp);
            }
            if(graph.containsKey(b)){
                graph.get(b).add(A);
            } else{
                ArrayList<int[]> temp = new ArrayList<>();
                temp.add(A);
                graph.put(b, temp);
            }
        }
        visited = new boolean[N];

        for(int i = 0; i < Q; i++){
            // I want a list of all relevance values for nodes connected to this node.
            // Each path needs to maintain its minimum relevance
            int k = test.nextInt(), v = test.nextInt();
            visited[v-1] = true;
            test.println(BFS(v, Integer.MAX_VALUE, k));
            visited = new boolean[N];
        }

        test.close();
    }

    static boolean[] visited;
    public static int BFS(int node, int minK, int KK){
        // node, relevance
        List<int[]> neighbors = graph.get(node);
        int count = 0;
        for(int[] neighbor : neighbors){
            if(!visited[neighbor[0]-1]){
                visited[neighbor[0]-1] = true;
                int k = neighbor[1];
                if(k > minK) k = minK;
                if(k >= KK) {
                    count++;
                    count += BFS(neighbor[0], k, KK);
                }
            }
        }
        return count;
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
