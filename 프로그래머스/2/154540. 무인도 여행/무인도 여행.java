import java.io.*;
import java.util.*;

class Solution {
    
    final char SEA = 'X';
    char [][] map ;
    boolean [][] visited ;
    List <Integer> list ;
    
    
    int rowSize;
    int colSize;
    
    // int landCnt;
    
    public int[] solution(String[] maps) {        

        // landCnt = 0;
        
        list = new ArrayList<>();
        
        rowSize = maps.length;
        colSize = maps[0]. length();
        
        map = new char[rowSize][colSize];
        visited = new boolean [rowSize][colSize];
        
        for(int i=0; i< rowSize; i++){
            map[i] = maps[i].toCharArray();
        }
        
        
        
        for(int r=0; r<rowSize; r++){
            for(int c=0; c<colSize; c++){
                if(visited[r][c] || map[r][c] == 'X') continue;
                BFS(r, c);
            }
        }
        
        if(list.size() == 0) {
        int [] answer = {-1};
            return answer;
        
        };
        
        
        int [] answer = new int [list.size()];
        
        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
        
        Arrays.sort(answer);
        return answer;
        
    }
    
    int []dr = {0, 0, 1, -1};
    int []dc = {1, -1, 0, 0};
    
    class Node {
        int row;
        int col;
            
        Node (int row, int col){
            this.row = row;
            this.col = col;
        }
    }
    
    Queue<Node> q ;
    
    void BFS(int sr, int sc){
        
        q = new LinkedList<>();
        
        q.add(new Node(sr, sc));
        visited[sr][sc] = true;
        
        int day = map[sr][sc] - '0';
        
        while(!q.isEmpty()){
            Node curr = q.poll();
            for(int d=0; d<4; d++){
                int nr = curr.row + dr[d];
                int nc = curr.col + dc[d];
                
                if(nr < 0 || nr >= rowSize || nc < 0 || nc >= colSize) continue;
                if(visited[nr][nc] || map[nr][nc] == 'X') continue;
                
                visited[nr][nc] = true;
                day += map[nr][nc] - '0'; // 더해주기
                
                q.add(new Node(nr, nc));
            }
        }
        
        list.add(day);
    }
    
    
}