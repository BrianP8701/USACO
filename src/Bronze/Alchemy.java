import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Alchemy {
    static int N;
    static HashMap<Integer, Integer> metalCount = new HashMap<>();
    static HashMap<Integer, int[]> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(r.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(r.readLine());
        for(int i = 0; i < N; i++){
            metalCount.put(i+1, Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(r.readLine());
        int K = Integer.parseInt(st.nextToken());
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(r.readLine());
            int product = Integer.parseInt(st.nextToken());
            int J = Integer.parseInt(st.nextToken());
            int[] ingredients = new int[J];
            for(int j = 0; j < J; j++){
                ingredients[j] = Integer.parseInt(st.nextToken());
            }
            map.put(product, ingredients);
        }

        int count = 0;
        while(true){
            if(!make(N)) break;
            count++;
        }
        count += metalCount.get(N);
        pw.println(count);
        pw.close();
    }
    static public boolean make(int x){
        int[] ingredients = map.get(x);
        if(ingredients == null) return false;

        for (int ingredient : ingredients) {
            if(metalCount.get(ingredient) == 0){
                if(!make(ingredient)) return false;
                metalCount.put(ingredient, 0);
            } else{
                metalCount.put(ingredient, metalCount.get(ingredient)-1);
            }
        }
        return true;
    }
}
