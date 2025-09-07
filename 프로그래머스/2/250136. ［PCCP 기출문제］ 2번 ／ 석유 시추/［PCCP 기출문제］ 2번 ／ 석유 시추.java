import java.io.*;
import java.util.*;

class Solution {
    
    class Oil {
        int id;
        int size; 
        
        Oil (int id, int size) {
            this.id = id;
            this.size = size;
        }
        
        void setOil(int size){
            this.size = size;
        }
        
        void increaseOil(){
            size++;
        }
    }
    
    class Node {
        int row;
        int col;
        Node (int row, int col){
            this.row = row;
            this.col = col;
        }

    }
    
    int [] dr = {0, 0, -1, 1};
    int [] dc = {-1, 1, 0, 0};
    
    Oil BFS (Node start) {
        Queue<Node> q = new LinkedList<>();
        q.add(start);
        
        Oil oil = new Oil(idxOfOil++, 0); // 오일 객체 먼저 선언
        visited[start.row][start.col] = oil; // 오일 넣어줌과 동시에 방문체크
        oil.increaseOil(); // 찾은 오일 수 늘려주기

        
        while(!q.isEmpty()){
            Node curr = q.poll();
            
            for(int d=0; d<4; d++){
                int nr = curr.row + dr[d];
                int nc = curr.col + dc[d];
                
                if(nr < 0 || nr >= depth || nc < 0 || nc >= width) continue;
                if(land[nr][nc] == 0) continue; // 땅이면 가지마
                if(visited[nr][nc]!= null)  continue; // 이미 측량된 숫자 있으면 방문한곳  
                
                visited[nr][nc] = oil; // 오일 넣어줌과 동시에 방문체크
                oil.increaseOil(); // 찾은 오일 수 늘려주기
                q.add(new Node(nr, nc)); 
            } // end of For     
        }
        return oil;
    }
    
        int [][] land;
        Oil [][] visited;
    
        int depth;
        int width;
        int idxOfOil; // 각 기름덩어리를 식별하기 위해 넣음
    
        public int solution(int[][] input) {
        
        int answer = 0;
            
        land = input;
        depth = land.length;
        width = land[0].length;
        idxOfOil = 0;
            
        visited = new Oil [depth][width];
        List<Integer> idList;
        
        for(int w =0; w < width ; w++){
            int sum = 0;
            idList = new ArrayList<>();
            
            outer: for(int d=0; d<depth ; d++){
                //1. 땅인가
                if(land[d][w] == 0) continue;
                // 2. 이미 측량됐던곳인가? 아닌가? 
                if(visited[d][w] == null){ // 측량 x bfs
                    Oil tmp = BFS(new Node(d, w));
                    sum += tmp.size;
                    idList.add(tmp.id); // 중복 합산 방지를 위해 id 모아놓기
                }
                else { // 이미 측량됐던곳이면
                    // 3. 이번 시추때 방문한 곳인지 id 체크
                    Oil currOil = visited[d][w];
                    for(int i=0; i<idList.size(); i++){
                        // 3-1. 이미 방문했던곳이니 다음칸 탐색하자
                        if(idList.get(i) == currOil.id) continue outer; 
                    }
                    // 3-2. 방문했던곳이 아니면 sum에 더해주기
                    sum += currOil.size;
                    idList.add(currOil.id);
                }
            }// end of d
            // 마지막으로 sum을 해당 칸의 최대 시추로 업데이트
            answer = Math.max(answer, sum);
        }            
            
//         for(int d=0; d< depth; d++){
//             for(int w = 0; w < width; w++){
//                 System.out.printf("%2d, ", land[d][w]);                
//             }
//             System.out.println();
//         }    
//                         System.out.println("-----------");

//         for(int d=0; d< depth; d++){
//             for(int w = 0; w < width; w++){
//                 if(visited[d][w]!= null){
//                     System.out.printf("%2d, ", visited[d][w].size);                
//                 }
//                 else{
//                     System.out.printf("%2d, ", 0);             
//                 }
//             }
//             System.out.println();
//         }
        
        return answer;
    }
}