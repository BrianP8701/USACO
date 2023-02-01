import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Photoshoot {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int N = Integer.parseInt(r.readLine());
        String x = r.readLine();
        List<Integer> guernseyCows = new ArrayList<>();
        for(int i = 0 ; i < N; i++){
            if(x.charAt(i) == 'G') guernseyCows.add(i+1);
        }
        for(int i = 0; i < guernseyCows.size(); i++){
            int index = guernseyCows.get(i);
            if(index % 2 == 1){
                int odd = 1;
                int even = 0;
                int weWentUpTo = 1;
                for(int j = i+1; j < guernseyCows.size(); j++){
                    if(guernseyCows.get(j) % 2 == 1) odd++;
                    else even++;
                    weWentUpTo = j;
                    if(even > odd) break;
                }
                while (weWentUpTo >= 0){
                    if(guernseyCows.get(weWentUpTo) % 2 == 1) break;
                    weWentUpTo--;
                }

            }
        }
    }

}

