import java.io.*;
import java.util.*;
public class MilkPails {
    static int X, Y, M;
    static boolean[][] matrix;
    static Queue<int[]> queue = new LinkedList();
    static ArrayList<int[]> visited = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        Kattio test = new Kattio("pails");
        X = test.nextInt();
        Y = test.nextInt();
        matrix = new boolean[X+1][Y+1];
        int K = test.nextInt();
        int M = test.nextInt();
        int[] first = {0,0};
        queue.add(first);
        matrix[0][0] = true;
        while(K > 0){
            Queue temp = new LinkedList();
            while(!queue.isEmpty()){
                int[] current = queue.poll();
                ArrayList<int[]> neighbors = getNeighbors(current);
                for(int[] arr : neighbors){
                    if(!matrix[arr[0]][arr[1]]){
                        temp.add(arr);
                        visited.add(arr);
                    }
                    matrix[arr[0]][arr[1]] = true;
                }
            }
            K--;
            queue = temp;
        }
        int min = Integer.MAX_VALUE;
        for(int[] arr : visited){
            if(Math.abs(M - arr[0] - arr[1]) < min) min = Math.abs(M - arr[0] - arr[1]);
        }
        test.println(min);
        test.close();
    }
// 14, 0
// 14, 50
    public static ArrayList<int[]> getNeighbors(int[] arr){
        int x = arr[0], y = arr[1];
        ArrayList<int[]> list = new ArrayList<>();
        if(x < X) {
            int[] a = {X, y};
            int z = X - x;
            list.add(a);
            if(z <= y){
                int[] b = {X, y - z};
                list.add(b);
            } else{
                int[] b = {y, 0};
                int[] c = {y + x, 0};
                list.add(b);
                list.add(c);
            }
        }
        if(y < Y) {
            int[] a = {x, Y};
            int z = Y - y;
            list.add(a);
            if(z <= x){
                int[] b = {x - z, Y};
                list.add(b);
            } else{
                int[] b = {0, x};
                int[] c = {0, x + y};
                list.add(b);
                list.add(c);
            }
        }
        int[] a = {0, y};
        int[] b = {x, 0};
        list.add(a);
        list.add(b);
        return list;
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