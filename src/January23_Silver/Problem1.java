

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Problem1 {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(r.readLine());
        int N = Integer.parseInt(st.nextToken());

        here: for(int q = 0; q < N; q++){
            st = new StringTokenizer(r.readLine());
            String input = st.nextToken();
            st = new StringTokenizer(r.readLine());
            String output = st.nextToken();

            int len = input.length();

            HashMap<Character, Character> map = new HashMap<>();
            HashMap<Character, Character> reverseMap = new HashMap<>();
            int count = 0;
            HashSet<Character> accountedFor = new HashSet<>();
            for(int i = 0; i < len; i++){
                char a = input.charAt(i), b = output.charAt(i);
                if(map.containsKey(a)){
                    if(map.get(a) != b) {
                        pw.println(-1);
                        continue here;
                    }
                } else if(a == b) {
                    map.put(a, b);
                    reverseMap.put(a, b);
                }else if(reverseMap.containsKey(a) && !accountedFor.contains(b)){
                    count += 2;
                    accountedFor.add(a);
                    map.put(a, b);
                    reverseMap.put(b, a);
                }  else{
                    map.put(a, b);
                    reverseMap.put(b, a);
                    count++;
                }
            }
            pw.println(count);
        }
        pw.close();
    }
}
