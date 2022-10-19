import java.io.*;
import java.util.StringTokenizer;

public class TamingTheHerd {
    public static void main(String[] args) throws IOException {
        Kattio test = new Kattio("taming");
        int N = test.nextInt();
        int[] arr = new int[N];
        int shouldBe = 1;
        int y = test.nextInt();
        here: if(y == -1 || y == 0){
            arr[0] = -1;
             for(int i = 1; i < N; i++){
                int x = test.nextInt();
                if(x == shouldBe){
                    arr[i] = x;
                    shouldBe++;
                    for(int j = i; x > 0 && j > 0; j--){
                        if(arr[j] == -1 || arr[j] == x) {
                            arr[j] = x;
                            x--;
                        } else{
                            test.println(-1);
                            break here;
                        }
                    }
                } else if(x == 0){
                    arr[i] = 0;
                    shouldBe = 1;
                }else if(x == -1){
                    arr[i] = -1;
                    shouldBe++;
                } else if(x > shouldBe){
                    test.println(-1);
                    break here;
                } else{
                    shouldBe = x+1;
                    arr[i] = x;
                    for(int j = i; x > 0 && j > 0; j--){
                        if(arr[j] == -1 || arr[j] == x) {
                            arr[j] = x;
                            x--;
                        } else{
                            test.println(-1);
                            break here;
                        }
                    }
                }
            }
             int min = 1; int max = 1;
             for(int i = 1; i < N; i++){
                 if(arr[i] == -1 || arr[i] == 0) max++;
                 if((arr[i] == 1 && arr[i-1] != 0) && i > 1 || arr[i] == 0) min++;
             }
             test.println(min + " " + max);
        } else{
            test.println(-1);
        }
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
