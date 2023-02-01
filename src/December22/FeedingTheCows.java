import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FeedingTheCows {
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
            int gSpot = 0;
            boolean[] Gpatches = new boolean[N];
            while(true){
                if(gSpot >= N) break;
                if(line[gSpot]) {
                    int index = gSpot + K;
                    gSpot = K * 2 + gSpot + 1;
                    if(index >= N-1){
                        Gpatches[N-1] = true;
                        break;
                    }
                    Gpatches[index] = true;
                } else{
                    gSpot++;
                }
            }

            int hSpot = 0;
            boolean[] Hpatches = new boolean[N];
            while(true){
                if(hSpot >= N) break;
                if(!line[hSpot]) {
                    int index = hSpot + K;
                    hSpot = K * 2 + hSpot + 1;
                    if(index >= N-1){
                        if(Gpatches[N-1]){
                            Hpatches[N-2] = true;
                        } else {
                            Hpatches[N - 1] = true;
                        }
                        break;
                    }
                    Hpatches[index] = true;
                } else{
                    hSpot++;
                }
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
