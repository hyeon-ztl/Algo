import java.io.*;
import java.util.*;

class Solution {
    
    class Node implements Comparable<Node> {
        int v, dis;
        
        Node (int v, int dis){
            this.v = v;
            this.dis = dis;
        }
        
        @Override
        public int compareTo (Node o){
            return this.dis- o.dis;
        }
        
    }
    
    int N;
    int K;
    int [][] ROAD;
    
    List<Node> [] list;
    PriorityQueue<Node> q ;

    public int solution(int n, int[][] road, int k) {
        int answer = 0;

         N = n;
         K = k;
         ROAD = road;
        
        list = new ArrayList[n];
        
        for(int i=0; i<n; i++){
            list[i] = new ArrayList<>();
        }
        
        for(int [] way : road){
            int a = way[0]-1;
            int b = way[1]-1;
            int dis = way[2];
            
            list[a]. add(new Node(b, dis));
            list[b]. add(new Node(a, dis));        
        }
        
        djstra(0);
        
        for(int d : dist){
            if(d <= k){
                answer ++;
            }
        }

        return answer;
    }
    
    boolean [] visited ;
    int dist [];

    
    void djstra (int start) {
        q = new PriorityQueue<>();
        
        visited = new boolean [N];
        dist = new int [N];
        int INF = 987654321;
        Arrays.fill(dist, INF);
       
        dist [start] = 0;
        
        
        q.add(new Node(start, 0));
        
        while(!q.isEmpty()){
            
            Node curr = q.poll();
            if(visited[curr.v]) continue;
            visited [curr.v] = true; // 방문체크
            
            for(Node n : list[curr.v]){
                if(!visited[n.v] && dist[n.v] > dist[curr.v] + n.dis ){
                    dist[n.v] = dist[curr.v] + n.dis;
                    q.add(new Node(n.v, dist[n.v]));
                }
            }
            
        }
    }
}