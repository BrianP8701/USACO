import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Visits {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(r.readLine());
        int N = Integer.parseInt(st.nextToken());

        HashMap<Integer, Node> cowNodes = new HashMap<>();
        long totalMoos = 0;

        for(int cow = 1; cow <= N; cow++){
            cowNodes.put(cow, new Node(cow, null));
        }
        int chain = 1;
        for(int cow = 1; cow <= N; cow++){
            st = new StringTokenizer(r.readLine());
            int friend = Integer.parseInt(st.nextToken());
            long moos = Integer.parseInt(st.nextToken());
            Node friendNode = cowNodes.get(friend);
            Node cowNode = cowNodes.get(cow);
            if(friendNode.next == null ) {
                cowNode.next = friendNode;
                friendNode.prev.add(cowNode);
                cowNode.moos = moos;
                totalMoos += moos;

                pw.println(totalMoos);
                // Edge case: If cow has multiple prev then we remove all, leaving the greatest one
                if(cowNode.prev.size() > 1){
                    long greatest = cowNode.prev.get(cowNode.prev.size()-1).moos;
                    for(int i = cowNode.prev.size() - 2; i >= 0; i--){
                        Node m = cowNode.prev.get(i);
                        if(m.moos > greatest){
                            greatest = m.moos;
                            totalMoos -= greatest;
                            cowNode.prev.remove(i+1);
                        } else{
                            totalMoos -= m.moos;
                            cowNode.prev.remove(i);
                            m.next = null;
                            m.moos = 0;
                        }
                    }
                }
            } else if(friendNode.prev.size() == 0){

            }else{
                Node oldPrev = friendNode.prev.get(0);
                if(oldPrev.moos < moos){
                    oldPrev.next = null;
                    totalMoos -= oldPrev.moos;
                    totalMoos += moos;
                    pw.println(totalMoos);
                    friendNode.prev.remove(0);
                    friendNode.prev.add(cowNode);
                    cowNode.next = friendNode;
                    cowNode.moos = moos;
                }
            }
        }
        pw.println(totalMoos);
        pw.close();
    }
    public static class Node{
        int cow, chain = 0;
        Node next;
        ArrayList<Node> prev = new ArrayList<>();
        long moos;
        public Node(int cow, Node next){
            this.cow = cow;
            this.next = next;
        }
    }
}
