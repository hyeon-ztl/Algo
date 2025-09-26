import java.io.*;
import java.util.*;

class Solution {
    
    class Node implements Comparable<Node> {
        int v;
        int w;
        String name;
        
        Node (int V, int W){
            this.v = V;
            this.w = W;
        }
        
        @Override
        public int compareTo (Node o) {
            return this.w - o.w;
        }
        
    }
    
    boolean visited [];
    int dis [][];
    List<Node>[] adjList;
    
    int a;
    int b;
    int n;
              
    public int solution(int nh, int s, int ah, int bh, int[][] fares) {
        int answer = 0;
        
        a = ah;
        b = bh;
        n = nh; 
        
        adjList = new ArrayList[n+1];
        
        for(int i=0; i<=n; i++){
            adjList[i] = new ArrayList<>();
        }
        
        for(int[] fare : fares){
            int step1 = fare[0];
            int step2 = fare[1];
            int dist = fare[2];
            
            adjList[step1].add(new Node(step2, dist));
            adjList[step2].add(new Node(step1, dist));
        }
        
        dis = new int [n+1][n+1];
        int MAX = 987654321;
        
        for(int i=0; i<=n; i++){
            Arrays.fill(dis[i], MAX);
        }
        
        for(int i=1; i<=n; i++){
            djstra(i, i);
        }
        
        answer = dis[s][a] + dis[s][b];
        
        for(int i=1; i<=n; i++){            
            if(dis[s][i]<0 || dis[i][a] <0 || dis[i][b] <0) continue;
            if(dis[s][i]>=MAX || dis[i][a] >=MAX || dis[i][b]>=MAX) continue;
            answer = Math.min(answer, dis[s][i] + dis[i][a] + dis[i][b]);
        }
            
        return answer;
    }

    void djstra (int ver, int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        visited = new boolean [n+1];
        
        dis[ver][start] = 0;
        
        pq.add(new Node(start, 0));
        
        while(!pq.isEmpty()){
            Node curr = pq.poll();
            if(visited[curr.v]) continue;
            visited[curr.v] = true;
            
            for(Node o : adjList[curr.v]){
                if(visited[o.v]) continue;
                if(dis[ver][o.v] < (dis[ver][curr.v]+o.w)) continue;
                
                dis[ver][o.v] = dis[ver][curr.v]+o.w;
                pq.add(new Node(o.v, dis[ver][o.v]));
            } 
        }
        
        
    }
}