import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Triangles {
    public static void main(String[] args) throws IOException {
        Kattio test = new Kattio("triangles");
        int N = test.nextInt();
        ArrayList<Point> allPoints = new ArrayList<>();
        // Vertical would be
        HashMap<Integer, ArrayList<Point>> vertical = new HashMap<>();
        // Horizontal would be
        HashMap<Integer, ArrayList<Point>> horizontal = new HashMap<>();
        for(int i = 0; i < N ; i++){
            int a = test.nextInt(), b = test.nextInt();
            Point p = new Point(a, b);
            allPoints.add(p);
            if(horizontal.containsKey(a)){
                horizontal.get(a).add(p);
            } else{
                ArrayList<Point> temp = new ArrayList<>();
                temp.add(p);
                horizontal.put(a, temp);
            }
            if(vertical.containsKey(b)){
                vertical.get(b).add(p);
            } else{
                ArrayList<Point> temp = new ArrayList<>();
                temp.add(p);
                vertical.put(b, temp);
            }
        }
        int max = 0;
        for(Point a : allPoints){
            ArrayList<Point> x = horizontal.get(a.x);
            ArrayList<Point> y = vertical.get(a.y);
            Point xMax = a;
            Point yMax = a;
            for(Point q : y){
                if(Math.abs(a.x - q.x) > Math.abs(a.x - xMax.x)){
                    xMax = q;
                }
            }
            for(Point q : x){
                if(Math.abs(a.y - q.y) > Math.abs(a.y - yMax.y)){
                    yMax = q;
                }
            }
            int area = Math.abs(a.x - xMax.x) * Math.abs(a.y - yMax.y);
            if(area > max) max = area;
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
