import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class SleepingInClass {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(r.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int j = 0; j < T; j++) {
            st = new StringTokenizer(r.readLine());
            int N = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(r.readLine());
            int[] arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int remainder = 0;
            int possible = arr[0];
            int possibleIndex = 1;
            for (int i = 0; i < N; i++) {
                if (remainder + arr[i] > possible || i == N-1 && remainder + arr[i] != possible) {
                    possible += arr[possibleIndex];
                    possibleIndex++;
                    remainder = 0;
                    i = -1;
                } else if (remainder + arr[i] == possible) {
                    remainder = 0;
                } else {
                    remainder += arr[i];
                }
            }

            int sum = -1;
            int count = 0;
            for(int i = 0; i < N; i++){
                if(sum != -1) count++;
                if(sum == -1) sum++;
                sum += arr[i];
                if(sum == possible){
                    sum = -1;
                }
            }
            pw.println(count);
        }
        pw.close();
    }
}
