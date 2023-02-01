

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Problem1 {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(r.readLine());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(r.readLine());
        String line = st.nextToken();
        int firstG = 0, lastG = 0, firstH = 0, lastH = 0;
        boolean foundG = false, foundH = false;
        for(int i = 0; i < N; i++){
            if(line.charAt(i) == 'G'){
                if(foundG) lastG = i + 1;
                else {
                    firstG = i + 1;
                    lastG = i + 1;
                    foundG = true;
                }
            } else{
                if(foundH) lastH = i + 1;
                else {
                    firstH = i + 1;
                    lastH = i + 1;
                    foundH = true;
                }
            }
        }

        int[] upTo = new int[N];
        st = new StringTokenizer(r.readLine());
        for(int i = 0; i < N; i++){
            upTo[i] = Integer.parseInt(st.nextToken());
        }
        boolean gLeader = upTo[firstG-1] >= lastG, hLeader = upTo[firstH-1] >= lastH;
        int count = 0;
        if(gLeader && hLeader) count++;
        for(int i = 0; i < N; i++){
            if(line.charAt(i) == 'G' && hLeader){
                if(i+1 < firstH && upTo[i] >= firstH) count++;
            } else if(gLeader){
                if(i+1 < firstG && upTo[i] >= firstG) count++;
            }
        }
        pw.println(count);
        pw.close();
    }
}
