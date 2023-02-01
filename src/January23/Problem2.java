

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Problem2 {
    static AC[] list;
    static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(r.readLine());
        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[] stalls = new int[100];
        list = new AC[M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(r.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
            for(int j = a-1; j < b; j++){
                stalls[j] += c;
            }
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(r.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), power = Integer.parseInt(st.nextToken()), money = Integer.parseInt(st.nextToken());
            list[i] = new AC(a, b, power, money);
        }

        pw.println(propagate(0, 0, stalls));

        pw.close();

    }
    public static int propagate(int index, int cost, int[] stalls){
        // Use AC, or don't use AC. If at any point we've reached our goal just return that number.
        // Using AC

        if(done(stalls)) return cost;

        if(index == M) return Integer.MAX_VALUE;
        AC curr = list[index];
        int[] newStalls = stalls.clone();
        for(int i = curr.a-1; i < curr.b; i++){
            newStalls[i] -= curr.power;
        }
        int costA = propagate(index+1, cost + curr.money, newStalls), costB = propagate(index+1, cost, stalls);
        return Math.min(costA, costB);
    }
    public static boolean done(int[] stalls){
        for(int i = 0; i < 100; i++){
            if(stalls[i] > 0) return false;
        }
        return true;
    }
    static class AC{
        int a, b, power, money;
        AC(int a, int b, int power, int money){
            this.a = a;
            this.b = b;
            this.power = power;
            this.money = money;
        }
    }
}
