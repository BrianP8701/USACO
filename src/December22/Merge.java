import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Merge {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(r.readLine());
        int T = Integer.parseInt(st.nextToken());
        for(int t = 0; t < T; t++){
            st = new StringTokenizer(r.readLine());
            int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
            String input = r.readLine();
            boolean[] line = new boolean[N];
            for(int i = 0; i < N; i++){
                if(input.charAt(i) == 'G'){
                    line[i] = true;
                }
            }

            // Boolean array with Guernseys being true
            // Guernsey's first
            int spot = 0, GSpot = 0, HSpot = 0;
            boolean[] Gpatches = new boolean[N];
            boolean[] Hpatches = new boolean[N];
            while(true){
                if(spot >= N) break;
                // If we have a Guernsey
                if(line[spot]) {
                    int index = spot + K;
                    GSpot = K * 2 + GSpot + 1;
                    if(index >= N-1){
                        if(Hpatches[N-1]){
                            Gpatches[N-2] = true;
                        } else {
                            Gpatches[N - 1] = true;
                        }
                    } else Gpatches[index] = true;
                } else{ // Else, we have a Holstein
                    int index = HSpot + K;
                    HSpot = K * 2 + HSpot + 1;
                    if(index >= N-1){
                        if(Gpatches[N-1]){
                            Hpatches[N-2] = true;
                        } else {
                            Hpatches[N - 1] = true;
                        }
                    }
                    Hpatches[index] = true;
                }
                if(GSpot < HSpot) spot = GSpot;
                else spot = HSpot;
            }

            int numOfPatches = 0;
            String answer = "";
            for(int i = 0; i < N; i++){
                if(Gpatches[i]){
                    answer += "G";
                    numOfPatches++;
                } else if(Hpatches[i]){
                    answer += "H";
                    numOfPatches++;
                } else{
                    answer += ".";
                }
            }
            pw.println(numOfPatches);
            pw.println(answer);
        }
        pw.close();
    }
}
