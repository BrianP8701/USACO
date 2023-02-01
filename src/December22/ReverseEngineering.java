import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class ReverseEngineering {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(r.readLine());
        int T = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(r.readLine());
        for(int t = 0; t < T; t++){
            st = new StringTokenizer(r.readLine());
            int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

            // All rules, old cases and answers
            Set<int[]> allRules = new HashSet<>();
            ArrayList<int[]> oldCases = new ArrayList<>();
            ArrayList<Integer> oldAnswers = new ArrayList<>();

            for(int m = 0; m < M; m++){
                // Get Input
                st = new StringTokenizer(r.readLine());
                String str = st.nextToken();
                int[] current = new int[N];
                for(int i = 0; i < N; i++){
                    current[i] = Character.getNumericValue(str.charAt(i));
                }
                int answer = Integer.parseInt(st.nextToken());

                // Apply old rules to current case
                ArrayList<int[]> removeTheseRules = new ArrayList<>();
                for(int[] rule : allRules){
                    if(current[rule[0]] == rule[1] && answer != rule[2]) removeTheseRules.add(rule);
                }
                for(int[] rule : removeTheseRules){
                    allRules.remove(rule);
                }

                // Make new rules, and add them if they satisfy all old cases
                here: for(int i = 0; i < N; i++){
                    int[] rule = {i, current[i], answer};
                    for(int j = 0; j < oldCases.size(); j++){
                        int[] oldCase = oldCases.get(j);
                        if(oldCase[i] == current[i] && oldAnswers.get(j) != answer) continue here;
                    }
                    allRules.add(rule);
                }
                oldCases.add(current);
                oldAnswers.add(answer);
            }

            // Now iterate over all rules and see if they provide right answer for each case.
            // If rules, didn't provide an answer, all leftovers should be the same.
            int leftover = -1;
            boolean lie = false;
            for(int i = 0; i < oldCases.size(); i++){
                int answer = -1;
                int[] current = oldCases.get(i);
                for(int[] rule : allRules){
                    if(current[rule[0]] == rule[1]){
                        answer = rule[2];
                        break;
                    }
                }
                if(answer == -1) {
                    if(leftover == -1) leftover = oldAnswers.get(i);
                    else if(leftover != oldAnswers.get(i)) {
                        lie = true;
                        break;
                    }
                }
            }
            if(lie) pw.println("LIE");
            else pw.println("OK");
            if(t != T-1) st = new StringTokenizer(r.readLine());
        }
        pw.close();
    }
}
