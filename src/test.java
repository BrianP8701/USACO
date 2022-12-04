import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class test {
    public static void main(String[] args) {
        //int[] arr = {7, 25, 3, 38, 0, 0, 0, 0, 1, 3, 13};  90
        //int[] arr = {25, 1, 6, 0, 3, 14, 93}; 142
        int[] arr = {9, 36, 26, 5, 15, 13, 7, 0, 2, 29, 39, 7, 2, 23};
        int N = arr.length;
        int remainder = 0;
        int possible = arr[0];
        int possibleIndex = 1;
        for (int i = 0; i < N; i++) {
            if (remainder + arr[i] > possible || i == N-1 && remainder + arr[i] != possible) {
                possible += arr[possibleIndex];
                possibleIndex++;
                remainder = 0;
                i = -1;
            } else if (remainder + arr[i] == possible) {
                remainder = 0;
            } else {
                remainder += arr[i];
            }
        }
        System.out.println(possible);
    }
}

