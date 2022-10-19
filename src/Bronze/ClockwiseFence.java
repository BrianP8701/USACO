import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ClockwiseFence {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('N', 1);
        map.put('E', 2);
        map.put('S', 3);
        map.put('W', 4);
        for(int i = 0; i < N; i++) {
            String trail = br.readLine();
            int lefts = 0, rights = 0;
            int prevDirection = 0;
            for(int j = 0; j < trail.length(); j++) {
                char curr = trail.charAt(j);
                int direction = map.get(curr);
                if(direction == prevDirection) continue;
                else if(prevDirection != 0){
                    if(prevDirection == 1){
                        if(direction == 4) lefts++;
                        else rights++;
                    } else if(prevDirection == 2){
                        if(direction == 1) lefts++;
                        else rights++;
                    } else if(prevDirection == 3){
                        if(direction == 2) lefts++;
                        else rights++;
                    }else {
                        if(direction == 3) lefts++;
                        else rights++;
                    }
                }
                if(prevDirection == 0){
                    prevDirection = direction;
                }
                prevDirection = direction;
            }
            if(lefts > rights){
                System.out.println("CCW");
            } else{
                System.out.println("CW");
            }
        }
    }
}
