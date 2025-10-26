import java.io.*;
import java.util.*;

class Solution {
    public int solution(String[] board) {
        int answer = -1;
        
        char [][] map = new char [3][3];
        
        int idx = 0;
        for(String b : board){
            map[idx ++] = b.toCharArray();
        }
        
        int cntO = 0;
        int cntX = 0;
        
        for(int r =0; r<3; r++){
            for(int c=0; c<3; c++){
                if(map[r][c]== 'O') cntO ++;
                else if(map[r][c] == 'X') cntX ++;
            }
        }
        
        if(cntO < cntX) {
        // System.out.println("갯수안맞음");
            return 0;
        }
        else if (cntO > cntX +1) return 0;
        
        int bingoO = 0;
        int bingoX = 0;
        
        // col 빙고 체크
        loop: for(int r=0; r<3; r++){
            char first = map[r][0];
            if(first == '.') continue;
            
            for(int c=1; c<3; c++){
                if(first != map[r][c]) continue loop;
            }
            
            // 빙고 완성
            if(first == 'O')bingoO ++;
            else bingoX ++;
        }
        
        // row 빙고 체크
        loop: for(int c=0; c<3; c++){
            char first = map[0][c];
            if(first == '.') continue;
            for(int r=1; r<3; r++){
                if(first != map[r][c]) continue loop;
            }
            
            // 빙고 완성
            if(first == 'O')bingoO ++;
            else bingoX ++;
        }
        
        // 대각선 빙고 체크 
        char first = map[0][0];
        if(first != '.'){
            boolean isOk = true;
            for(int i =1; i<3 ; i++){
                if(first != map[i][i]) {
                    isOk = false;
                    break;
                }
            }
            if(isOk){
                if(first == 'O')bingoO ++;
                else bingoX ++;
            } 
        }
        
        first = map[0][2];
        if(first != '.'){
            boolean isOk = true;
            for(int i =1; i<3 ; i++){
                if(first != map[0+i][2-i]) {
                    isOk = false;
                    break;
                }
            }
            if(isOk){
                if(first == 'O')bingoO ++;
                else bingoX ++;
            } 
        }

        // 동시 빙고 불가
        if (bingoO > 0 && bingoX > 0) return 0;
        
        
        if(bingoO == 1 && cntO <= cntX) {
            // System.out.println("o빙고");
            return 0;
        }
        if(bingoX == 1 && cntO != cntX)  {
            // System.out.println("x빙고");
            return 0;
        }
        
        return 1;
    }
    

}