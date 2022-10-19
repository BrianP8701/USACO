import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CircularBarn {
    public static void main(String[] args) throws IOException {
        Kattio test = new Kattio("cbarn");
        int N = test.nextInt();
        int[] rooms = new int[N];
        for(int i = 0; i < N; i++){
            rooms[i] = test.nextInt();
        }
        // Trying different starting points
        int min = Integer.MAX_VALUE;
        here: for(int start = 0; start < N; start++){
            if(rooms[start] == 0) continue;
            int energyConsumed = 0;
            int room = start;
            Queue<Integer> queue = new LinkedList<>();
            //  Looping through the barn
            for(int j = 0; j < N; j++){
                if(room >= N) room = 0;
                int num = rooms[room];
                Queue<Integer> temp = new LinkedList<>();
                // Incrementing each cow
                while(!queue.isEmpty()){
                    temp.add(queue.poll()+1);
                }
                while(!temp.isEmpty()){
                    queue.add(temp.poll());
                }
                for(int i = 0; i < num; i++){
                    queue.add(0);
                }
                if(queue.isEmpty()) continue here;
                int x = queue.poll();
                energyConsumed += x * x;
                room++;
            }
            if(energyConsumed < min && energyConsumed != 0) min = energyConsumed;
        }
        test.println(min);
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
