

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Problem3 {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(r.readLine());
        int N = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(r.readLine());
            String str = st.nextToken();

            if(str.length() < 3) {
                pw.println(-1);
                continue;
            }
            if(str.contains("MOO")){
                pw.println(str.length() - 3);
                continue;
            }
            String str1 = str.substring(1);
            if(str1.contains("OO")){
                pw.println(str.length() - 2);
                continue;
            }
            String str2 = str.substring(0, str.length()-1);
            if(str2.contains("MO")){
                pw.println(str.length() - 2);
                continue;
            }
            String str3 = str.substring(1, str.length()-1);
            if(!str3.contains("O")){
                pw.println(-1);
                continue;
            }
            pw.println(str.length() - 1);
        }

        pw.close();
    }
}
