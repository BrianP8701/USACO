import java.awt.*;
import java.io.*;
import java.util.*;

public class SwitchingOnTheLights {

    public static void main(String[] args) throws IOException {
        Kattio test = new Kattio("lightson");
        int N = test.nextInt();
        int M = test.nextInt();
        boolean[][] matrix = new boolean[N][N];
        HashMap<Point, ArrayList<Point>> lightSwitches = new HashMap<>();
        for(int i = 0; i < M; i++){
            Point p = new Point(test.nextInt()-1, test.nextInt()-1);
            Point q = new Point(test.nextInt()-1, test.nextInt()-1);
            if(lightSwitches.containsKey(p)){
                lightSwitches.get(p).add(q);
            } else{
                ArrayList<Point> temp = new ArrayList<>();
                temp.add(q);
                lightSwitches.put(p,temp);
            }
        }
        Queue<Point> edgeCases = new LinkedList<>();
        edgeCases.add(new Point(0,0));
        int count = 0;
        int previous;
        matrix[0][0] = true;
        HashSet<Point> roomsAvailableAndOn = new HashSet<>();
        roomsAvailableAndOn.add(new Point(0,0));
        while(true){
            previous = roomsAvailableAndOn.size();
            HashSet<Point> temp = (HashSet<Point>) roomsAvailableAndOn.clone();
            for(Point p : roomsAvailableAndOn){
                ArrayList<Point> neighbors = getNeighbors(p, N);
                for(Point q : neighbors){
                    if(matrix[q.x][q.y]){
                        edgeCases.add(q);
                        temp.add(q);
                    }
                }
            }
            roomsAvailableAndOn = temp;
            if(roomsAvailableAndOn.size() == previous) count++;
            else count = 0;
            while(!edgeCases.isEmpty()){
                ArrayList<Point> switches = lightSwitches.get(edgeCases.poll());
                if(switches != null) {
                    for (Point p : switches) {
                        matrix[p.x][p.y] = true;
                    }
                }
            }
            if(count == 5) break;
        }
        int ans = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(matrix[i][j]) ans++;
            }
        }
        test.println(ans);
        test.close();
    }

    public static ArrayList<Point> getNeighbors(Point p, int N){
        ArrayList<Point> ans = new ArrayList<>();
        if(p.x > 0) ans.add(new Point(p.x-1,p.y));
        if(p.y > 0) ans.add(new Point(p.x,p.y-1));
        if(p.x < N-1) ans.add(new Point(p.x+1,p.y));
        if(p.y < N-1) ans.add(new Point(p.x,p.y+1));
        return ans;
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
