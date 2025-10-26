
import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node>{
        int v;
        int dis;

        Node (int v, int dis){
            this. v = v;
            this. dis = dis;
        }

        @Override
        public int compareTo (Node o){
            return this.dis - o.dis;
        }

    }

    static int range;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //---------
        StringTokenizer st = new StringTokenizer(br.readLine());

        // n m r
        int num = Integer.parseInt(st.nextToken()); // 지역개수
        range = Integer.parseInt(st.nextToken()); // 수색범위
        int road = Integer.parseInt(st.nextToken()); // 길의갯수

        distance = new int [num][num];
        visited = new boolean [num][num];
        canGo = new boolean [num][num];

        final int INF = 987654321;

        item = new int [num];

        //----------
        st = new StringTokenizer(br.readLine());
        int idx = 0;

        for(int i=0; i<num; i++){
            item[i] = Integer.parseInt(st.nextToken());
        }

        // 연결리스트
        list = new ArrayList [num];
        for(int i=0; i<num; i++){
            list[i] = new ArrayList<>();
            Arrays.fill(distance[i], INF);

        }

        for(int i=0 ; i<road; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list[a-1].add(new Node(b-1, w));
            list[b-1].add(new Node(a-1, w));
        }

        answer = 0;
        // ----------- input 완료
        for(int i=0; i<num; i++){
            djstra(i);
        }

        System.out.println(answer);


    }
    static int answer ;
    static int [] item;
    static List<Node> [] list;
    static int [][] distance;
    static boolean [][] visited;
    static boolean [][] canGo;
    static PriorityQueue <Node> q ;

    static void djstra (int start) {
        q = new PriorityQueue<>();

        int cnt = 0;

        q.add(new Node(start, 0));
        distance[start][start] = 0;

        while(!q.isEmpty()){
            Node curr = q.poll();
            
            if (curr.dis != distance[start][curr.v]) continue;
            
            if(visited[start][curr.v]) continue; // 방문햇으면 넘어가
            visited[start][curr.v] = true;


            cnt += item[curr.v];

            for(Node o : list[curr.v]){
                if(visited[start][o.v]) continue; // 방문햇으면 넘어가
                if(distance[start][o.v] < o.dis + distance[start][curr.v]) continue; // 저장해놓은 값이 더 작으면 이 거리도 넘어가
                int tmp = o.dis + distance[start][curr.v];
                if(tmp > range) continue;

                distance[start][o.v] = tmp;
                q.add(new Node(o.v, distance[start][o.v]));
            }


        } // end of while

        answer = Math.max(answer, cnt);
    }// end of mathod

}
