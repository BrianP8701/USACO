import java.util.ArrayList;
import java.util.HashMap;

public class Grapha {
    // time to make the shortest path algorithm again
    // Bi-directional graph
    HashMap<Integer, ArrayList<Integer>> graph = new HashMap<Integer, ArrayList<Integer>>();
    int root;
    public void add(int a, int b){
        if(!graph.containsKey(a)){
            graph.put(a, new ArrayList<Integer>(b));
        } else{
            graph.get(a).add(b);
        }
        if(!graph.containsKey(b)){
            graph.put(b, new ArrayList<Integer>(a));
        } else{
            graph.get(b).add(a);
        }
    }
    public void remove(int a){
        if(graph.containsKey(a)){
            ArrayList<Integer> temp = graph.get(a);
            for(Integer i : temp){
                graph.remove(i);
            }
            graph.remove(a);
        }
    }

    public static void main(String[] args) {
        System.out.println("Here");
    }



}
