import java.util.ArrayList;

public class test{
    public static void main(String[] args) {
        String str = "MMO";
        if(str.contains("MOO")){
            System.out.println(str.length() - 3);

        }
        String str1 = str.substring(1);
        if(str1.contains("OO")){
            System.out.println(str.length() - 2);

        }
        String str2 = str.substring(0, str.length()-1);
        if(str1.contains("MO")){
            System.out.println(str.length() - 2);

        }
        String str3 = str.substring(1, str.length()-1);
        if(str.length() < 3 || !str3.contains("O")){
            System.out.println(-1);

        } else{
            if(str.equals("OOM")) System.out.println(2);
            else if(str.equals("OOMO")) System.out.println(3);
        }
    }
}