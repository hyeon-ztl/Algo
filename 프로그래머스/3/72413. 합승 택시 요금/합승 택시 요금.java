import java.io.*;
import java.util.*;

class Solution {
    
    final int S = 0;
    final int A = 1;
    final int B = 2;
    
    int answer ;
    
    int [][] arr ;
    List<Node>[] list ;
    
    class Node implements Comparable<Node>{
        int n;
        int dis;
        
        Node(int n, int dis) {
            this. n = n;
            this. dis = dis;
        }
        
        @Override
        public int compareTo (Node o) {
            return this.dis - o.dis;
        }
    }
    
    int N;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        
        N = n;
        arr = new int [3][n+1];
        list = new ArrayList[n+1];
        for(int i=0; i<=n; i++) list[i] = new ArrayList<>();
        
        for(int[] f : fares){
            int one = f[0];
            int two = f[1];
            int dis = f[2];
            
            list[one].add(new Node(two, dis));
            list[two].add(new Node(one, dis));
        }
        
        int bigNum = Integer.MAX_VALUE;
        for(int i=0; i<3; i++){
            Arrays.fill(arr[i], bigNum);
        }
        
        
        djstra(a, A);
        djstra(b, B);
        djstra(s, S);
        
        answer = bigNum;
        
        for(int i=1; i<n+1; i++){
            answer = Math.min(answer, arr[S][i] + arr[A][i] + arr[B][i]);
        }
        
        return answer;
    }
    
    boolean visited [];
    
    void djstra(int start, int k) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        
        arr[k][start] = 0;
        visited = new boolean [N+1];

        q.add(new Node(start, 0));
        
        while(!q.isEmpty()){
            Node curr = q.poll();
            
            if(visited[curr.n]) continue;
            visited[curr.n] = true;
            
            List<Node> tmpList = list[curr.n];
            
            for(Node next : tmpList){
                if(curr.dis + next.dis < arr[k][next.n]){
                    arr[k][next.n] = curr.dis + next.dis;
                    q.add(new Node(next.n, arr[k][next.n]));
                }
            }
        }
        
        
        // new Node
        
        
        
    }
}