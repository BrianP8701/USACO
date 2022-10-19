import java.io.*;
import java.util.*;

public class ClosingTheFarm {
    public static void main(String[] args) throws IOException {
        Kattio test = new Kattio("closing");
        int N = test.nextInt();
        int M = test.nextInt();
        Graph graph = new Graph(N);
        for(int i = 0; i < M; i++){
            int a = test.nextInt(), b = test.nextInt();
            graph.addEdge(a, b);
        }
        for(int i = 0; i < N; i++){
            if(graph.isConnected()) test.println("YES");
            else test.println("NO");
            int c = test.nextInt();
            graph.remove(c);
        }
        test.close();
    }
    static class Graph{
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        ArrayList<Integer> nodesLeft = new ArrayList<>();
        int nodes;
        public Graph(int n){
            nodes = n;
            for(int i = 0; i < n; i++){
                nodesLeft.add(i);
                adj.add(new ArrayList<>());
            }
        }
        public void addEdge(int a, int b){
            adj.get(a - 1).add(b - 1);
            adj.get(b - 1).add(a - 1);
        }
        public void remove(int a){
            ArrayList<Integer> temp = (ArrayList)adj.get(a-1).clone();
            for(Integer i : temp){
                adj.get(i).remove(adj.get(i).indexOf(a-1));
            }
            adj.get(a-1).clear();
            nodesLeft.remove(nodesLeft.indexOf(a-1));
        }
        public boolean isConnected(){
            if(nodesLeft.size()==0) return true;
            Stack<Integer> stack = new Stack<>();
            boolean[] arr = new boolean[nodes];
            int current = nodesLeft.get(0);
            arr[current] = true;
            stack.add(current);
            while(!stack.isEmpty()){
                current = stack.pop();
                for(Integer i : adj.get(current)){
                    if(arr[i]) continue;
                    arr[i] = true;
                    stack.add(i);
                }
            }
            boolean answer = true;
            for(int i = 0; i < nodesLeft.size(); i++){
                int x = nodesLeft.get(i);
                if(!arr[x]) answer = false;
            }
            return answer;
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
