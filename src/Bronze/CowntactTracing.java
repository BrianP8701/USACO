import java.io.*;
import java.util.*;

public class CowntactTracing {
    static int N, T;
    static HashMap<Integer, ArrayList<int[]>> map = new HashMap<>();
    static ArrayList<Integer> possibleSuspects = new ArrayList<>();
    static boolean[] cows;
    public static void main(String[] args) throws IOException {
        Kattio test = new Kattio("tracing");
        N = test.nextInt();
        T = test.nextInt();
        cows = new boolean[N];
        String cowLine = test.next();
        for(int i = 0; i < N; i++){
            map.put(i, new ArrayList<>());
            if(cowLine.charAt(i) == '0') cows[i] = false;
            else {
                cows[i] = true;
                possibleSuspects.add(i);
            }
        }
        HashMap<Integer, int[]> timeToHandshake = new HashMap<>();
        int[] allTimes = new int[T];
        for(int i = 0; i < T; i++){
            int time = test.nextInt();
            int[] temp = {test.nextInt()-1, test.nextInt()-1};
            allTimes[i] = time;
            timeToHandshake.put(time, temp);
        }
        Arrays.sort(allTimes);
        for(int i = 0; i < T; i++){
            int t = allTimes[i], x = timeToHandshake.get(t)[0], y = timeToHandshake.get(t)[1];
            int[] a = {t, x}, b = {t, y};
            map.get(x).add(b);
            map.get(y).add(a);
        }

        // Now... the next step is to iterate through my possible suspects and get my answers
        int minK = Integer.MAX_VALUE;
        int maxK = 0;
        Set<Integer> suspectsFound = new HashSet<>();
        int verifiedSuspects = 0;
        boolean infinite = false;

        here: for(int suspect : possibleSuspects){
            // Make sure to throw out cases where root has no neighbors!
            if(map.get(suspect).size() == 0) continue here;
            int currentMinK = 1;
            while(true){
                int code = propagate(suspect, currentMinK);
                if(code == 2) continue here;
                else if(code == 1){
                    currentMinK++;
                } else{
                    if(currentMinK < minK) minK = currentMinK;
                    verifiedSuspects++;
                    if(!infinite) {
                        int kM = findMaxK(suspect, currentMinK);
                        if(kM == 251) infinite = true;
                        if(kM > maxK) maxK = kM;
                    }
                    break;
                }
            }
        }
        if(infinite) {
            test.println(verifiedSuspects + " " + minK + " Infinity");
        } else{
            test.println(verifiedSuspects + " " + minK + " " + maxK);
        }
        test.close();
    }
    public static int findMaxK(int root, int K){
        K = K + 1;
        here: while(true){
            ArrayList<int[]> handshakes = new ArrayList<>();
            Set<Integer> infected = new HashSet<>();
            infected.add(root);
            for(int i = 0; i < K && i < map.get(root).size(); i++){
                handshakes.add(map.get(root).get(i));
                infected.add(map.get(root).get(i)[1]);
                if(!possibleSuspects.contains(map.get(root).get(i)[1])) break here;
            }
            while(!handshakes.isEmpty()){
                ArrayList<int[]> newHandshakes = new ArrayList<>();
                for(int[] hs : handshakes){
                    ArrayList<int[]> tempTimeline = map.get(hs[1]);
                    int timeOfHandShake = hs[0];
                    int count = 0;
                    for(int[] handshake : tempTimeline){
                        if(handshake[0] > timeOfHandShake){
                            count++;
                            infected.add(handshake[1]);
                            if(!possibleSuspects.contains(handshake[1])) break here;
                            newHandshakes.add(handshake);
                        }
                        if(count == K) break;
                    }
                }
                handshakes = newHandshakes;
            }
            K++;
            if(K == 251) break here;
        }
        return K;
    }
    public static int propagate(int root, int K){
        ArrayList<int[]> handshakes = new ArrayList<>();
        Set<Integer> infected = new HashSet<>();
        infected.add(root);
        for(int i = 0; i < K && i < map.get(root).size(); i++){
            handshakes.add(map.get(root).get(i));
            infected.add(map.get(root).get(i)[1]);
            if(!possibleSuspects.contains(map.get(root).get(i)[1])) return 2;
        }
        while(!handshakes.isEmpty()){
            ArrayList<int[]> newHandshakes = new ArrayList<>();
            for(int[] hs : handshakes){
                ArrayList<int[]> tempTimeline = map.get(hs[1]);
                int timeOfHandShake = hs[0];
                int count = 0;
                for(int[] handshake : tempTimeline){
                    if(handshake[0] > timeOfHandShake){
                        count++;
                        infected.add(handshake[1]);
                        if(!possibleSuspects.contains(handshake[1])) return 2;
                        newHandshakes.add(handshake);
                    }
                    if(count == K) break;
                }
            }
            handshakes = newHandshakes;
        }
        if(infected.size() == possibleSuspects.size()) return 3;
        return 1;
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
