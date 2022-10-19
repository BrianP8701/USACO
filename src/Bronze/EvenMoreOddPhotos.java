
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class EvenMoreOddPhotos {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());;
        Stack odd = new Stack();
        Stack even = new Stack();
        for(int i = 0; i < N; i++){
            int x = Integer.parseInt(st.nextToken());
            if(x % 2 == 0) {
                even.add(x);
            } else{
                odd.add(x);
            }
        }
        int count = 0;
        boolean evenSwitch = true;
        while(!odd.isEmpty()){
            if(evenSwitch){
                if(even.isEmpty()){
                    odd.pop();
                    if(odd.isEmpty()){
                        count--;
                        break;
                    } else{
                        odd.pop();
                        count++;
                    }
                } else{
                    even.pop();
                    count++;
                }
            } else{
                odd.pop();
                count++;
            }
            evenSwitch = !evenSwitch;
        }
        if(!even.isEmpty() && evenSwitch) count++;

        System.out.println(count);
    }
}
