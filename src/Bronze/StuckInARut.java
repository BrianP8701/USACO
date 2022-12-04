import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class StuckInARut {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(r.readLine());
        int N = Integer.parseInt(st.nextToken());
        HashMap<Integer, Cow> map = new HashMap<>();
        List<Cow> xCows = new ArrayList<>();
        List<Cow> yCows = new ArrayList<>();
        List<Cow> rangeList = new ArrayList<>();
        List<Cow> infinityList = new ArrayList<>();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(r.readLine());
            String d = st.nextToken();
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            Cow curr = new Cow(x, y, getDirection(d));
            xCows.add(curr); yCows.add(curr);
            map.put(i, curr);
        }
        Collections.sort(xCows, new XComparator());
        Collections.sort(yCows, new YComparator());
        for(int i = 0; i < N; i++){
            Cow cow = map.get(i);
            if(cow.direction == 0){
                int index = yCows.indexOf(cow) + 1;
                while(true){
                    if(index == yCows.size()) {
                        infinityList.add(cow);
                        break;
                    }
                    Cow target = yCows.get(index);
                    if(Math.abs(target.x - cow.x) > Math.abs(target.y - cow.y) || Math.abs(target.x - cow.x) == Math.abs(target.y - cow.y)) {
                        index++;
                        continue;
                    }
                    if(target.direction == 1 && target.x < cow.x){
                        cow.range = Math.abs(cow.y - target.y);
                        rangeList.add(cow);
                        break;
                    }
                    if(target.direction == 3 && target.x > cow.x){
                        cow.range = Math.abs(cow.y - target.y);
                        rangeList.add(cow);
                        break;
                    }
                    index++;
                }
            } else if(cow.direction == 2){
                int index = yCows.indexOf(cow) - 1;
                while(true){
                    if(index == -1) {
                        infinityList.add(cow);
                        break;
                    }
                    Cow target = yCows.get(index);
                    if(Math.abs(target.x - cow.x) > Math.abs(target.y - cow.y) || Math.abs(target.x - cow.x) == Math.abs(target.y - cow.y)) {
                        index--;
                        continue;
                    }
                    if(target.direction == 1 && target.x < cow.x){
                        cow.range = Math.abs(cow.y - target.y);
                        rangeList.add(cow);
                        break;
                    }
                    if(target.direction == 3 && target.x > cow.x){
                        cow.range = Math.abs(cow.y - target.y);
                        rangeList.add(cow);
                        break;
                    }
                    index--;
                }
            } else if(cow.direction == 1){
                int index = xCows.indexOf(cow) + 1;
                while(true){
                    if(index == xCows.size()) {
                        infinityList.add(cow);
                        break;
                    }
                    Cow target = xCows.get(index);
                    if(Math.abs(target.y - cow.y) > Math.abs(target.x - cow.x) || Math.abs(target.x - cow.x) == Math.abs(target.y - cow.y)) {
                        index++;
                        continue;
                    }
                    if(target.direction == 0 && target.y < cow.y){
                        cow.range = Math.abs(cow.x - target.x);
                        rangeList.add(cow);
                        break;
                    }
                    if(target.direction == 2 && target.y > cow.y){
                        cow.range = Math.abs(cow.x - target.x);
                        rangeList.add(cow);
                        break;
                    }
                    index++;
                }
            } else{
                int index = xCows.indexOf(cow) - 1;
                while(true){
                    if(index == -1) {
                        infinityList.add(cow);
                        break;
                    }
                    Cow target = xCows.get(index);
                    if(Math.abs(target.y - cow.y) > Math.abs(target.x - cow.x) || Math.abs(target.x - cow.x) == Math.abs(target.y - cow.y)) {
                        index--;
                        continue;
                    }
                    if(target.direction == 0 && target.y < cow.y){
                        cow.range = Math.abs(cow.x - target.x);
                        rangeList.add(cow);
                        break;
                    }
                    if(target.direction == 2 && target.y > cow.y){
                        cow.range = Math.abs(cow.x - target.x);
                        rangeList.add(cow);
                        break;
                    }
                    index--;
                }
            }
        }
        Collections.sort(rangeList, new RangeComparator());
        List<Cow> newList = new ArrayList<Cow>(rangeList);
        newList.addAll(infinityList);
        for(Cow cow : newList){
            if(cow.direction == 0){
                int index = yCows.indexOf(cow) + 1;
                while(true){
                    if(index == yCows.size()) {
                        infinityList.add(cow);
                        break;
                    }
                    Cow target = yCows.get(index);
                    if(Math.abs(target.x - cow.x) > Math.abs(target.y - cow.y) || Math.abs(target.x - cow.x) == Math.abs(target.y - cow.y) ||
                            (Math.abs(target.y-cow.y) > target.range && target.range != -1)) {
                        cow.range = -1;
                        index++;
                        continue;
                    }
                    if(target.direction == 1 && target.x < cow.x){
                        cow.range = Math.abs(cow.y - target.y);
                        rangeList.add(cow);
                        break;
                    }
                    if(target.direction == 3 && target.x > cow.x){
                        cow.range = Math.abs(cow.y - target.y);
                        rangeList.add(cow);
                        break;
                    }
                    index++;
                }
            }
            else if(cow.direction == 2){
                int index = yCows.indexOf(cow) - 1;
                while(true){
                    if(index == -1) {
                        infinityList.add(cow);
                        break;
                    }
                    Cow target = yCows.get(index);
                    if(Math.abs(target.x - cow.x) > Math.abs(target.y - cow.y) || Math.abs(target.x - cow.x) == Math.abs(target.y - cow.y) ||
                            (Math.abs(target.y-cow.y) > target.range && target.range != -1)) {
                        cow.range = -1;
                        index--;
                        continue;
                    }
                    if(target.direction == 1 && target.x < cow.x){
                        cow.range = Math.abs(cow.y - target.y);
                        rangeList.add(cow);
                        break;
                    }
                    if(target.direction == 3 && target.x > cow.x){
                        cow.range = Math.abs(cow.y - target.y);
                        rangeList.add(cow);
                        break;
                    }
                    index--;
                }
            } else if(cow.direction == 1){
                int index = xCows.indexOf(cow) + 1;
                while(true){
                    if(index == xCows.size()) {
                        infinityList.add(cow);
                        break;
                    }
                    Cow target = xCows.get(index);
                    if(Math.abs(target.y - cow.y) > Math.abs(target.x - cow.x) || Math.abs(target.x - cow.x) == Math.abs(target.y - cow.y) ||
                            (Math.abs(target.x-cow.x) > target.range && target.range != -1)) {
                        cow.range = -1;
                        index++;
                        continue;
                    }
                    if(target.direction == 0 && target.y < cow.y){
                        cow.range = Math.abs(cow.x - target.x);
                        rangeList.add(cow);
                        break;
                    }
                    if(target.direction == 2 && target.y > cow.y){
                        cow.range = Math.abs(cow.x - target.x);
                        rangeList.add(cow);
                        break;
                    }
                    index++;
                }
            } else {
                int index = xCows.indexOf(cow) - 1;
                while (true) {
                    if (index == -1) {
                        infinityList.add(cow);
                        break;
                    }
                    Cow target = xCows.get(index);
                    if (Math.abs(target.y - cow.y) > Math.abs(target.x - cow.x) || Math.abs(target.x - cow.x) == Math.abs(target.y - cow.y) ||
                            (Math.abs(target.x-cow.x) > target.range && target.range != -1)) {
                        cow.range = -1;
                        index--;
                        continue;
                    }
                    if (target.direction == 0 && target.y < cow.y) {
                        cow.range = Math.abs(cow.x - target.x);
                        rangeList.add(cow);
                        break;
                    }
                    if (target.direction == 2 && target.y > cow.y) {
                        cow.range = Math.abs(cow.x - target.x);
                        rangeList.add(cow);
                        break;
                    }
                    index--;
                }
            }
        }
        for(int i = 0; i < N; i++){
            if(map.get(i).range == -1){
                pw.println("Infinity");
            } else{
                pw.println(map.get(i).range);
            }
        }
        pw.close();
    }
    public static int getDirection(String d){
        if(d.equals("N")) return 0;
        if(d.equals("E")) return 1;
        if(d.equals("S")) return 2;
        return 3;
    }
}

// 0-N 1-E 2-S 3-W
class Cow {
    int x, y, direction;
    int range = -1;
    Cow(int x, int y, int direction){
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
}
class XComparator implements Comparator<Cow> {
    @Override
    public int compare(Cow o1, Cow o2) {
        return o1.x- o2.x;
    }
}
class YComparator implements Comparator<Cow> {
    @Override
    public int compare(Cow o1, Cow o2) {
        return o1.y- o2.y;
    }
}
class RangeComparator implements Comparator<Cow> {
    @Override
    public int compare(Cow o1, Cow o2) {
        return o1.range- o2.range;
    }
}
