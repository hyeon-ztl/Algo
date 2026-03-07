import java.io.*;
import java.util.*;

class Solution {
    
    class Node implements Comparable<Node> {
        int idx;
        int dis;
        
        Node (int idx, int dis){
            this.idx = idx;
            this.dis = dis;
        }
        
        public int compareTo(Node o){
            return this.dis - o.dis;
            }
    }
    
    PriorityQueue<Node> pq = new PriorityQueue<>(); 
    List<Node> [] list;
    int [] distance; 
    int N;
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        this. N = N;
        
        list = new ArrayList [N+1];
        distance = new int [N+1];
        
        for(int i=1; i<N+1; i++){
            list[i] = new ArrayList<>();
        }
        
        for(int[] r : road){
            list[r[0]] .add(new Node(r[1], r[2]));
            list[r[1]].add(new Node(r[0], r[2]));
        }
        
        Arrays.fill(distance, bigNum);
        djstra(new Node(1, 0));
        
        for(int d: distance){
            if(d <= K) answer ++;
        }

        return answer;
    }
    
        int bigNum = Integer.MAX_VALUE;
    
    
    void djstra (Node start) {
        boolean [] visited = new boolean[N+1];
        distance[start.idx] = 0;
        
        pq.add(start);
        
        while(!pq.isEmpty()){
            Node curr = pq.poll();
            List<Node> tmpList = list[curr.idx];
            // curr 방문체크? 
            
            for(Node o : tmpList){ // 갈 수 있는 경로들
                if(visited[o.idx]) continue; // 이미 갔던곳이면 건너뛰어
                if (distance[o.idx] > distance[curr.idx] + o.dis){
                    distance[o.idx] = distance[curr.idx] + o.dis;
                    pq.add(new Node(o.idx, distance[o.idx]));
                }
            } // end
            
        }
        
    }
}