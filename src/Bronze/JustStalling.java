import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class JustStalling {
    static PrintWriter pw = new PrintWriter(System.out);
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(r.readLine());
        int N = Integer.parseInt(st.nextToken());
        ArrayList<Long> cows = new ArrayList<>();
        ArrayList<Long> stalls =  new ArrayList<>();
        st = new StringTokenizer(r.readLine());
        for(int i = 0; i < N; i++){
            cows.add(Long.parseLong(st.nextToken()));
        }
        st = new StringTokenizer(r.readLine());
        for(int i = 0; i < N; i++){
            stalls.add(Long.parseLong(st.nextToken()));
        }
        pw.println(iLoveRecursion(cows, stalls, 0));
        pw.close();
    }

    public static int iLoveRecursion(ArrayList<Long> cows, ArrayList<Long> stalls, int sum){
        if(stalls.size() == 0) {
            return 1;
        }
        long currentStall = stalls.get(0);

        ArrayList<Long> sStalls = (ArrayList<Long>) stalls.clone();
        ArrayList<Long> sCows = (ArrayList<Long>) cows.clone();
        Collections.sort(sStalls);
        Collections.sort(sCows);
        for(int i = 0; i < sStalls.size(); i++){
            if(sCows.get(i) > sStalls.get(i)) return 0;
        }
        ArrayList<Long> newStalls = (ArrayList<Long>) stalls.clone();
        int newSum = sum;
        newStalls.remove(0);
        for(long cow : cows) {
            if(cow <= currentStall) {
                ArrayList<Long> newCows = (ArrayList<Long>) cows.clone();
                newCows.remove(cows.indexOf(cow));
                newSum += iLoveRecursion(newCows, newStalls, sum);
            }
        }
        if(newSum == 0) return 0;
        return newSum;
    }
}
