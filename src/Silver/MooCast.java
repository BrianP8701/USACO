import java.awt.*;
import java.io.*;
import java.util.*;

public class MooCast {
    static HashMap<Point, ArrayList<Point>> neighbor = new HashMap<>();
    public static void main(String[] args) throws IOException {
        Kattio test = new Kattio("moocast");
        int N = test.nextInt();
        HashMap<Point, Integer> Power = new HashMap<>();
        HashSet<Point> set = new HashSet<>();
        HashMap<Point, ArrayList<Point>> map = new HashMap<>();
        for(int i = 0; i < N; i++){
            Point p = new Point(test.nextInt(), test.nextInt());
            Power.put(p, test.nextInt());
            set.add(p);
            map.put(p, new ArrayList<Point>());
        }
        for(Point p : set){
            for(Point q : set){
                if(distance(p, q) <= Power.get(p)){
                    map.get(p).add(q);
                }
            }
        }
        int max = 0;
        for(Point p : set){
            HashSet<Point> reached = new HashSet<>();
            Queue<Point> queue = new LinkedList<>();
            queue.add(p);
            while(!queue.isEmpty()){
                Point curr = queue.poll();
                for(Point q : map.get(curr)){
                    if(!reached.contains(q)){
                        queue.add(q);
                        reached.add(q);
                    }
                }
            }
            if(max < reached.size()) max = reached.size();
        }
        test.println(max);
        test.close();
    }
    static double distance(Point a, Point b){
        return Math.sqrt(Math.abs(a.x - b.x) * Math.abs(a.x - b.x) + Math.abs(a.y - b.y) * Math.abs(a.y - b.y));
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
