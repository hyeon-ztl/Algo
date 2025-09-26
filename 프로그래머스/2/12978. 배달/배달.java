import java.io.*;
import java. util. *;

class Solution {
    
    class Node implements Comparable<Node> {
        int v;
        int w;
        
        Node (int v, int w){
            this. v= v;
            this. w = w;
        }
        
        @Override
        public int compareTo(Node o){
            return this.w-o.w;
        }
    }
    
    int [] dist;
    List<Node> [] adjList;
    int n;
    
    public int solution(int N, int[][] road, int K) {
        
        int answer = 0;
        n = N;
        
        int INF = 987654321;

        dist = new int [n+1];
        adjList = new ArrayList [n+1] ;
        
        Arrays.fill(dist, INF);
        for(int i=1; i<=n; i++){
            adjList [i] = new ArrayList<>();
        }
        
        for(int[] r : road){
            int start = r[0];
            int end = r[1];
            int d = r[2];
            
            adjList[start].add(new Node(end, d));
            adjList[end].add(new Node(start, d));
        }
        
        dikstra(1);
        
        for(int dis : dist){
            if(dis <= K) answer ++;
        }

        return answer;
    }
    
    void dikstra(int start){
        PriorityQueue<Node> q = new PriorityQueue<>();
        boolean visited [] = new boolean [n+1];
        
        q.add(new Node (start, 0));
        dist [start] = 0;
        
        while(!q.isEmpty()){
            Node curr = q.poll();
            
            if(visited[curr.v]) continue;
            visited[curr.v] = true;

            for(Node node : adjList[curr.v]){
                if(visited[node.v]) continue;
                if(dist[node.v] > dist[curr.v] + node.w){
                    dist[node.v] = dist[curr.v] + node.w;
                    q.add(new Node(node.v, dist[node.v]));
                }
            }
            
        }
        
    }
}