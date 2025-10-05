import java.io.*;
import java.util.*;

class Solution {

    class Letter implements Comparable<Letter> {
        int idx;
        char word;
        
        Letter (int idx, char word){
            this.idx = idx;
            this.word = word;
        }
        
        @Override
        public int compareTo (Letter o) {
            if (this.idx == o.idx){
                return this.word -o.word;
            }
            
            return this.idx-o.idx;
        }
    }
    
    class Node {
        int row;
        int col;
        
        Node (int row, int col){
            this. row = row;
            this. col = col;
        }
    }
    
    char [][] map ;
    
    public String solution(int m, int n, String[] board) {
        String answer = "";
        
        // char A = 'A'; //65
        // char Z = 'Z'; //90
        // System.out.println((int)A +" "+(int)Z);
        int cntLetter = 0;
        
        map = new char [m][n];
        
        List<Node> list [] = new ArrayList[26];
        
        for(int i=0; i<26; i++){
            list[i] = new ArrayList<>();
        }
    
        
        // map 만들기
        for(int r=0; r<m; r++){
            for(int c=0; c<n; c++){
                
                char tmp = board[r].charAt(c);
                map[r][c] = tmp;
                
                // 숫자라면 여기 넣어주기
                int num = (int) tmp;
                if(num >= 65 && num <=90){
                    cntLetter ++;
                    list[num-65].add(new Node(r,c));
                }
            }
        } // 입력 완료

        
        
        // 턴 시작 
        PriorityQueue<Letter> q = new PriorityQueue<>();
        
        int idx = 0;
    
       loop: while(true){
            
            for(List<Node> alpha : list){ // 알파벳 전부 돌자
                int size = alpha.size();
                if(size == 0 || size == 1) continue;
                char let = map[alpha.get(0).row][alpha.get(0).col];
                
                
                for(int i=0; i<size-1; i++){
                    for(int j=i+1; j<size; j++ ){
                        if (check(alpha.get(i), alpha.get(j), let) ){
                            q.add(new Letter(idx, let));
                            // System.out.println(let);
                            
                            map[alpha.get(i).row][alpha.get(i).col] = '.';
                            map[alpha.get(j).row][alpha.get(j).col] = '.';   
                            
                            // list remove는 실시간으로 index가 바뀌게 되니 언제나 조심, idx 혼란 안오게 뒤에서 부터 삭제
                            alpha.remove(j);
                            alpha.remove(i);
                            
                            cntLetter -= 2;
                            idx++;
                            continue loop;
                        } 
                    } // j
                }// i
                
            }// end of each
            
            // if(isBreak) 
            break; // 이번턴에 아무것도 못했으면 끝내기
        }

        
        
        if(cntLetter !=0){
            return "IMPOSSIBLE";
        }
        
        else {
            StringBuilder sb = new StringBuilder ();
            while(!q.isEmpty()){
                sb.append(q.poll().word);
            }
            return sb.toString();
        }
        
        
    }
    
    
    boolean check (Node a, Node b, char let) {

        if(a.row == b.row && clearCol(a.col, b.col, b.row, let)) return true; 
        else if(a.col == b.col && clearRow(a.row, b.row, b.col, let)) return true; 
 
        else {
            if(clearRow(a.row, b.row, b.col, let) && clearCol(a.col, b.col, a.row, let)) return true;
            if(clearRow(a.row, b.row, a.col, let) && clearCol(a.col, b.col, b.row, let)) return true;     
        }
       
        
        return false;
        
    } // end of check
    
    boolean passable(int r, int c, char let){
        return map[r][c] == '.' || map[r][c] == let;
    }
    
    boolean clearRow(int r1, int r2, int c, char let) {
        int start = Math.min(r1, r2);
        int end = Math.max(r1,r2);
        
        for(int i=start; i<=end; i++){
            if(!passable(i, c, let)) return false; 
        }
        
        return true;
    }
    
    boolean clearCol(int c1, int c2, int r, char let){
        int start = Math.min(c1, c2);
        int end = Math.max(c1, c2);
        
        for(int i=start; i<=end; i++){
            if(!passable(r, i, let)) return false;
        }
        return true;
    }
    
    
    
    
    
}