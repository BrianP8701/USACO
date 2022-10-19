import java.awt.*;
import java.io.IOException;
import java.util.*;

public class test {


    // My merge sort implementation
    public static void main(String[] args) throws IOException {
        Grapha g = new Grapha();
    }

    public static int[] mergeSort(int[] arr){
        if(arr.length < 2) return arr;
        int len = arr.length;
        int mid = len / 2;
        int[] left = mergeSort(Arrays.copyOfRange(arr, 0, mid));
        int[] right = mergeSort(Arrays.copyOfRange(arr, mid, len));
        int rightIndex = 0, leftIndex = 0, answerIndex = 0;
        int[] answer = new int[len];
        while(rightIndex < right.length || leftIndex < left.length){
            if(leftIndex == left.length || rightIndex < right.length && right[rightIndex] <= left[leftIndex]){
                answer[answerIndex] = right[rightIndex];
                rightIndex++;
            } else{
                answer[answerIndex] = left[leftIndex];
                leftIndex++;
            }
            answerIndex++;
        }
        return answer;
    }


}
