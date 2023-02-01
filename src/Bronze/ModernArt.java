import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class ModernArt {
    public static void main(String[] args) throws IOException {
        Kattio test = new Kattio("art");
        int N = test.nextInt();
        int[][] matrix = new int[N][N];
        HashMap<Point, List> graph = new HashMap<>();  // Tile -> All it's colors
        HashMap<Integer, List<Point>> squares = new HashMap<>();  // Color -> All it's squares
        HashMap<Integer, int[]> corners = new HashMap<>();  // Color -> It's corners
        Set<Integer> colors = new HashSet<>();
        ArrayList<Integer> cantBe = new ArrayList<>();
        for(int i = 0; i < N; i++){
            String line = test.next();
            for(int j = 0; j < N; j++){
                int color = Character.getNumericValue((line.charAt(j)));
                matrix[i][j] = color;
                if(color == 0) continue;
                graph.put(new Point(i, j), new ArrayList<Integer>());
                if(colors.contains(color)) squares.get(color).add(new Point(i, j));
                else{
                    squares.put(color, new ArrayList<Point>());
                    squares.get(color).add(new Point(i, j));
                    colors.add(color);
                }
            }
        }
        for(int color : colors){
            corners.put(color, getExtremes(squares.get(color)));
        }
        for(int color : colors){
            int[] thisCorners = corners.get(color);
            for(int opps : colors){
                if(opps == color) continue;
                int[] oppCorners = corners.get(opps);
                if(thisCorners[0] >= oppCorners[0] && thisCorners[1] <= oppCorners[1] &&
                        thisCorners[2] <= oppCorners[2] && thisCorners[3] >= oppCorners[3]) {
                    cantBe.add(color);
                    break;
                }
            }
            for(int i = thisCorners[0]; i <= thisCorners[1]; i++){
                for(int j = thisCorners[3]; j <= thisCorners[2]; j++){
                    graph.get(new Point(i, j)).add(color);
                }
            }
        }
        here: for(int color : colors){
            int[] thisCorners = corners.get(color);
            for(int i = thisCorners[0]; i <= thisCorners[1]; i++){
                for(int j = thisCorners[3]; j <= thisCorners[2]; j++){
                    if(graph.get(new Point(i, j)).size() > 1 && matrix[i][j] == color) {
                        cantBe.add(color);
                        continue here;
                    }
                }
            }
        }
        int answer = 0;
        for(int color : colors){
            if(!cantBe.contains(color) && color != 0) answer++;
        }
        test.println(answer);
        test.close();
    }
    //Returns max {left, right, up, down}
    public static int[] getExtremes(List<Point> list){
        int left=list.get(0).x,right=list.get(0).x,up=list.get(0).y,down=list.get(0).y;
        for(Point p : list){
            if(p.x > right) right = p.x;
            if(p.x < left) left = p.x;
            if(p.y > up) up = p.y;
            if(p.y < down) down = p.y;
        }
        int[] answer = {left, right, up, down};
        return answer;
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
