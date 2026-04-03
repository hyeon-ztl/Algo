
import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int num;
        Node parents;
        List<Node> son ;
        int weight;

        Node (int num, Node parents) {
            this. num = num;
            this. parents = parents;
            weight = 0;
            son = new ArrayList<>();
        }

        void insertSon (Node s) {
            son.add(s);
            update();
        }

        void update() {
            weight ++;
            if(parents != null) parents.update();
        }
    }



public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());

    Map<Integer, Node> map = new HashMap<>(); // 주소넣어놓기
    Node min = new Node(0, null);
    map.put(0, min); // 민식이

    st.nextToken(); // 민식이 빼주기
    for(int i=1; i<N; i++){
        Node parents = map.get(Integer.parseInt(st.nextToken()));
        Node tmp = new Node(i, parents);
        map.put(i, tmp);

        // 부모 연결
        parents.insertSon(tmp);
    }
    // 트리완성
    answer = 0;

    System.out.println(DFS(0, min));


}


static int answer;

static int DFS (int time, Node me){
//        System.out.println("스택");
    // 가지치기
    List<Node> list = me.son;
//    Collections.sort(list, (a,b) -> b.weight - a.weight);

    if(list.size() == 0){
        return time;
    }

    List<Integer> sonTime = new ArrayList<>();

    for(int i=0; i<list.size(); i++){
        sonTime.add(DFS(time+1, list.get(i)));
    }

    int max = 0;
    Collections.sort(sonTime, (a,b)-> b -a);

    for(int i=0; i<list.size(); i++){ // 젤 큰애부터 사용한걸로 호출 해주기
        max = Math.max(sonTime.get(i) +i, max);
    }
    return max;
}
}
