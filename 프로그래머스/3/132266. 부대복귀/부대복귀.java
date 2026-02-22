import java.io.*;
import java.util.*;

class Solution {
    
    List<Integer> [] list;
    boolean [] visited ;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int [sources.length];
        
        list = new ArrayList[n+1];
        for(int i=1; i<=n; i++){
            list[i] = new ArrayList<>();
        }
        
        for(int [] road : roads){
            int a = road[0];
            int b = road[1];
            
            list[a].add(b);
            list[b].add(a);
        }
        // 인접리스트 완성
        
        Queue<Integer> q;
        outer: for(int i=0; i<sources.length; i++){
            int source = sources[i];
            if(source == destination){
                answer[i] = 0;
                continue outer;
            }
            
            
            visited = new boolean [n+1];
            
            q = new LinkedList<>();
            q.add(source); 
            // 거리를 알아야함
            int depth = 1;
            
            while(!q.isEmpty()){
                int tmpSize = q.size();
                
                for(int s=0; s< tmpSize; s++){ // depth 조절
                    int curr = q.poll();
                    visited[curr] = true;

                    for(int node: list[curr]){ // 현재지점에서 갈수 있는곳들 뽑기
                        if(visited[node]) continue;
                        
                        if(node == destination) { // 강철부대 도착
                            answer[i] = depth;
                            continue outer;
                        }
                        q.add(node);
                    }// end of list
                }// end of size
                
               depth ++;       
            }// end of q
            
            answer[i] = -1; // 맨마지막 까지 못도착

        }
        
        
        
        return answer;
    }
}