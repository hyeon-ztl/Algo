import java.util.*;
import java.io.*;

class Solution {
    

    
    class Node implements Comparable<Node> {
        int v, dis;
        
        public Node(int v, int dis){
            this.v = v;
            this.dis = dis;
        }
        
        @Override
        public int compareTo(Node n){
            return this.dis - n.dis;
        }
    }
    
    final int INF = 987654321;
    boolean[] visited ;
    PriorityQueue<Node> q;
    int [] dist ;
    List<Node> [] list ;
    int VILLAGE;
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        list = new ArrayList [N+1];
        dist = new int [N+1];
        visited = new boolean [N+1];
        VILLAGE = N;
        
        Arrays.fill(dist, INF);

        
        for(int i=0; i<N+1; i++){
            list[i] = new ArrayList<>();
        }

        
        for(int [] way : road){
            int a = way[0];
            int b = way[1];
            int tmpDis = way[2];
            list[a].add(new Node(b, tmpDis));
            list[b].add(new Node(a, tmpDis));
        }
        
        dijkstra(1);
        
        for(int num : dist){
            if(num <= K){
                answer++;
            }
        }
        

        return answer;
    }
    
    void dijkstra (int start){
        q = new PriorityQueue<>();

        dist[1] = 0;
        q.add(new Node(start, 0));
        
        while(!q.isEmpty()){
            Node curr = q.poll();
            if(visited[curr.v]) continue;
            visited[curr.v] = true;
            
            for(Node node : list[curr.v]){
                if(!visited[node.v] && dist[node.v] > dist[curr.v] + node.dis){
                    dist[node.v] = dist[curr.v] + node.dis;
                    q.add(new Node(node.v, dist[node.v]));
                }
                
            }
            
        }
        

    }
    
}