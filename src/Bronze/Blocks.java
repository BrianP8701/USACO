import java.util.*;

public class Blocks {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        scan.nextLine();
        ArrayList<Set> list = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            String s = scan.nextLine();
            Set<String> set = new HashSet<>();
            for(int j = 0; j < 6; j++){
                set.add(String.valueOf(s.charAt(j)));
            }
            list.add(set);
        }
        for(int i = 0; i < N; i++){
            if(canMake(list, scan.nextLine())){
                System.out.println("YES");
            } else{
                System.out.println("NO");
            }
        }
    }
    public static boolean canMake(ArrayList<Set> list, String str){
        if(str.length() == 0) return true;
        String currentLetter = String.valueOf(str.charAt(0));
        str = str.substring(1);
        ArrayList<Integer> indices = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).contains(currentLetter)) indices.add(i);
        }
        if(indices.size() == 0) return false;
        boolean answer = false;
        for(int indexToRemove : indices){
            ArrayList<Set> temp = new ArrayList<>(list);
            temp.remove(indexToRemove);
            if(canMake(temp, str)) answer = true;
        }
        return answer;
    }
}
