import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class WalkingHome {
    static boolean[][] field;
    static WHNode[][] fieldNode;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(r.readLine());
        int T = Integer.parseInt(st.nextToken());
        for(int t = 0; t < T; t++){
            st = new StringTokenizer(r.readLine());
            N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            field = new boolean[N][N];
            fieldNode = new WHNode[N][N];
            String row;
            for(int i = 0; i < N; i++){
                st = new StringTokenizer(r.readLine());
                row = st.nextToken();
                for(int j = 0; j < N; j++){
                    if(row.charAt(j) == '.') field[i][j] = true;
                    else field[i][j] = false;
                }
            }
            WHNode root = new WHNode(0, 0);
            propagate(root);
            int answer = 0;
            if(root.down != null) answer += leak(root.down, K, false, true);
            if(root.right != null) answer += leak(root.right, K, true, false);
            pw.println(answer);
        }
        pw.close();
    }

    public static int leak(WHNode node, int K, boolean goingRight, boolean goingDown) {
        if (node.x == N - 1 && node.y == N - 1) return 1;
        if (node.down == null && node.right == null) return 0;
        int answer = 0;
        if (node.right != null) {
            if (goingRight) {
                answer += leak(node.right, K, true, false);
            } else if(K != 0) {
                    answer += leak(node.right, K - 1, true, false);
            }
        }
        if (node.down != null) {
            if (goingDown) {
                answer += leak(node.down, K, false, true);
            } else if(K != 0){
                    answer += leak(node.down, K - 1, false, true);
            }
        }
        return answer;
    }
    public static void propagate(WHNode node){
        if(node.x != N-1) {
            if (fieldNode[node.x + 1][node.y] != null) {
                node.right = fieldNode[node.x + 1][node.y];
            } else if (field[node.x + 1][node.y]) {
                node.right = new WHNode(node.x + 1, node.y);
                fieldNode[node.x + 1][node.y] = node.right;
                propagate(node.right);
            }
        }
        if(node.y != N-1) {
            if (fieldNode[node.x][node.y + 1] != null) {
                node.down = fieldNode[node.x][node.y + 1];
            } else if (field[node.x][node.y + 1]) {
                node.down = new WHNode(node.x, node.y + 1);
                fieldNode[node.x][node.y + 1] = node.down;
                propagate(node.down);
            }
        }
    }
    static class WHNode{
        WHNode down = null;
        WHNode right = null;
        int x, y;
        WHNode(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}




