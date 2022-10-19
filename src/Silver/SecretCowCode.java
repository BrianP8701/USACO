

import java.io.*;
import java.util.StringTokenizer;

public class SecretCowCode {
    public static void main(String[] args) throws IOException {
        Kattio test = new Kattio("cowcode");
        String s = test.next();
        long index = test.nextInt();
        int size = s.length();
        while(index >= s.length()){
            size *= 2;
        }
        int newIndex = simplifyIndex(s.length(), index, size);
        if(newIndex <= size){
            test.println(s.charAt(newIndex - 1));
        } else{
            int newSize = s.length();
            // We want first letter of k
            int k = 1;
            while(newIndex < newSize){
                newSize *= 2;
                k++;
            }
            int count = 1;
            while(s.length() < k){
                String top = s.charAt(s.length()-1) + s.substring(0, s.length()-1);
                count++;
            }
            test.println(s.charAt(s.length()-k+count-1));
        }
        test.close();
    }
    static int simplifyIndex(int wordLength, long currentIndex, int currentSize){
        while(currentSize > currentIndex * 2) currentSize /= 2;
        int firstNumSecondHalf = currentSize / 2 + 1;
        if(currentIndex == firstNumSecondHalf) return (int)currentIndex;
        if(currentIndex <= wordLength) return (int)currentIndex;
        return simplifyIndex(wordLength, currentIndex - currentSize / 2 - 1, currentSize / 2);
    }
    static class Kattio extends PrintWriter {
        private BufferedReader r;
        private StringTokenizer st;

        public Kattio() { this(System.in, System.out); }
        public Kattio(InputStream i, OutputStream o) {
            super(o);
            r = new BufferedReader(new InputStreamReader(i));
        }
        // USACO-style file input
        public Kattio(String problemName) throws IOException {
            super(new FileWriter(problemName + ".out"));
            r = new BufferedReader(new FileReader(problemName + ".in"));
        }

        // returns null if no more input
        public String next() {
            try {
                while (st == null || !st.hasMoreTokens())
                    st = new StringTokenizer(r.readLine());
                return st.nextToken();
            } catch (Exception e) { }
            return null;
        }

        public int nextInt() { return Integer.parseInt(next()); }
        public double nextDouble() { return Double.parseDouble(next()); }
        public long nextLong() { return Long.parseLong(next()); }
    }
}
