import java.io.*;
import java.util.*;

public class YearOfTheCow {
    public static void main(String[] args) {
        List<String> calendar = List.of("Ox", "Tiger", "Rabbit", "Dragon", "Snake", "Horse", "Goat", "Monkey", "Rooster", "Dog", "Pig", "Rat");
        Scanner scan = new Scanner(System.in);
        HashMap<String, Integer> years = new HashMap<>();

        // Dealing with first row
        String name1 = scan.next();
        scan.next(); scan.next();
        boolean temp = scan.next().equals("next") ? true : false;
        int difference = calendar.indexOf(scan.next());
        scan.next(); scan.next();
        String name2 = scan.next();
        int n = scan.nextInt();
        years.put(name2, 0);


        for(int i = 0; i < n; i++){

        }
    }
}
