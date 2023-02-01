import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Drought {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(r.readLine());
        int T = Integer.parseInt(st.nextToken());

        here: for(int j = 0; j < T; j++){
            st = new StringTokenizer(r.readLine());
            int N = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(r.readLine());
            long[] arr = new long[N];

            for(int i = 0; i < N; i++){
                arr[i] = Long.parseLong(st.nextToken());
            }


            long count = 0;
            long min = arr[0];
            for(int i = 1; i < N; i++){
                long b = arr[i];
                if(min > b){
                    if(i % 2 == 1){
                        pw.println(-1);
                        continue here;
                    } else {
                        count += (i/2) * (min - b) * 2;
                        min = b;
                    }
                } else if(min < b){
                    if(i == N-1){
                        pw.println(-1);
                        continue here;
                    }
                    count += (b-min) * 2;
                    arr[i] = min;
                    arr[i+1] -= (b-min);
                    if(arr[i+1] < 0) {
                        pw.println(-1);
                        continue here;
                    }
                }
            }


            pw.println(count);
        }
        pw.close();
    }
}
