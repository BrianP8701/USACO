

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MilkingOrder {
    public static void main(String[] args) throws IOException {
        Kattio test = new Kattio("milkorder");
        int N = test.nextInt();
        int M = test.nextInt();
        int K = test.nextInt();
        ArrayList<Integer> statusOrder = new ArrayList<>();
        int[] placement = new int[N+1];
        for(int i  = 0; i < M; i++){
            statusOrder.add(test.nextInt());
        }
        for(int i = 0; i < K; i++){
            int a = test.nextInt();
            placement[test.nextInt()] = a;
        }
        if(statusOrder.contains(1)){
            int index = 1;
            int mostRecentStatusIndex = -1;
            while(!statusOrder.isEmpty()){
                if(index == N){
                    if(mostRecentStatusIndex == -1) mostRecentStatusIndex = 1;
                    while(!statusOrder.isEmpty()){
                        if(placement[mostRecentStatusIndex]==0){
                            placement[mostRecentStatusIndex] = statusOrder.remove(0);
                        }
                        mostRecentStatusIndex++;
                    }
                }
                if(statusOrder.contains(placement[index])){
                    mostRecentStatusIndex = index;
                    int placementIndex = index - 1;
                    while(statusOrder.get(0) != placement[index]){
                        if(placement[placementIndex] == 0){
                            placement[placementIndex] = statusOrder.remove(0);
                        }
                    }
                    statusOrder.remove(0);
                }
                index++;
            }
        } else{
            int index = 1;
            while(index < N + 1){
                if(statusOrder.contains(placement[index])){
                    int statusIndex = statusOrder.indexOf(placement[index]);
                    int placementIndex = index - 1;
                    statusOrder.remove(statusIndex);
                    statusIndex--;
                    while(statusIndex != -1){
                        if(placement[placementIndex] == 0){
                            placement[placementIndex] = statusOrder.remove(statusIndex);
                            statusIndex--;
                        }
                        placementIndex--;
                    }
                }
                index++;
            }
            index = N;
            while(!statusOrder.isEmpty()){
                if(placement[index] == 0){
                    placement[index] = statusOrder.remove(statusOrder.size()-1);
                }
                index--;
            }
            int find = 1;
            while(placement[find] != 0) find++;
            placement[find] = 1;
        }
        for(int i = 1; i < N+1; i++){
            if(placement[i] == 1){
                test.println(i);
                test.close();
            }
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
