import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CowCollege {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(r.readLine());
        int N = Integer.parseInt(st.nextToken());
        long[] tuitions = new long[N];
        st = new StringTokenizer(r.readLine());

        for(int i = 0; i < N; i++){
            tuitions[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(tuitions);

        long moneyMade = Integer.MIN_VALUE, tuition = 0;
        for(int i = 0; i < N; i++){
            long currentTuition = tuitions[i];
            long profit;
            profit = (N-i) * currentTuition;
            if(profit > moneyMade) {
                moneyMade = profit;
                tuition = currentTuition;
            }
        }
        pw.println(moneyMade + " " + tuition);
        pw.close();
    }
}
