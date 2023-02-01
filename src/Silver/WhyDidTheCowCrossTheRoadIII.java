import java.io.*;
import java.util.*;

public class WhyDidTheCowCrossTheRoadIII {
    public static void main(String[] args) throws IOException {
        Kattio test = new Kattio("countcross");
        int N = test.nextInt();
        int K = test.nextInt();
        int R = test.nextInt();
        Graph graph = new Graph();
        ArrayList<Node> allNodes = new ArrayList<>();
        ArrayList<Node> cows = new ArrayList<>();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                Node current = new Node(i, j);
                allNodes.add(current);
                ArrayList<Node> neighbors = new ArrayList<>();
                if(i > 0) neighbors.add(new Node(i-1, j));
                if(i < N-1) neighbors.add(new Node(i+1, j));
                if(j < N-1) neighbors.add(new Node(i, j+1));
                if(j > 0) neighbors.add(new Node(i, j-1));
                graph.adjacencyList.put(current, neighbors);
            }
        }
        for(int i = 0 ; i < R; i++){
            graph.removeEdge(test.nextInt()-1, test.nextInt()-1, test.nextInt()-1, test.nextInt()-1);
        }
        for(int i = 0; i < K; i++){
            cows.add(new Node(test.nextInt()-1, test.nextInt()-1));
        }
        ArrayList<Integer> cowPerSections = new ArrayList<>();
        int cow = 0;
        // BFS, removing nodes we see from allNodes, and adding to cows when we se a cow laddy.
        // When BFS is done, if we have more nodes left, repeat :)

        while(true){
            ArrayList<Node> neighbors = graph.adjacencyList.get(allNodes.get(0));
            if(cows.contains(allNodes.get(0))) {
                cow++;
                cows.remove(allNodes.get(0));
            }
            ArrayList<Node> visitedNodes = new ArrayList<>();
            visitedNodes.add(allNodes.get(0));
            for(Node n : neighbors){
                visitedNodes.add(n);
                if(cows.contains(n)) {
                    cow++;
                    cows.remove(n);
                }
                allNodes.remove(n);
            }
            allNodes.remove(0);
            while(neighbors.size() != 0){
                ArrayList<Node> newNeighbors = new ArrayList<>();
                for(Node neighbor : neighbors){
                    allNodes.remove(neighbor);
                    for(Node neighborsNeighbor : graph.adjacencyList.get(neighbor)){
                        if(cows.contains(neighborsNeighbor)) {
                            cow++;
                            cows.remove(neighborsNeighbor);
                        }
                        if(!visitedNodes.contains(neighborsNeighbor)){
                            visitedNodes.add(neighborsNeighbor);
                            newNeighbors.add(neighborsNeighbor);
                        }
                    }

                }
                neighbors = newNeighbors;
            }
            cowPerSections.add(cow);
            cow = 0;
            if(allNodes.size() == 0) break;
        }
        int answer = 0;
        for(int i = 0; i < cowPerSections.size() - 1; i++){
            for(int j = i + 1; j < cowPerSections.size(); j++){
                answer += cowPerSections.get(i) * cowPerSections.get(j);
            }
        }
        test.println(answer);
        test.close();
    }
    static class Graph{
        HashMap<Node, ArrayList<Node>> adjacencyList = new HashMap<Node, ArrayList<Node>>();
        void removeEdge(int X, int Y, int x, int y){
            Node a = new Node(X, Y);
            Node b = new Node(x, y);
            if(adjacencyList.containsKey(a)){
                adjacencyList.get(a).remove(b);
            }
            if(adjacencyList.containsKey(b)){
                adjacencyList.get(b).remove(a);
            }
        }
    }
    static class Node{
        int x, y, hashCode;
        Node(int x, int y){
            this.x = x;
            this.y = y;
            this.hashCode = Objects.hash(x, y);
        }
        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Node that = (Node) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return this.hashCode;
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
