
import java.io.*;
import java.util.*;

class Solution {
    
    class Result {
        boolean aWin;
        int stamp;
        
        Result (boolean aWin, int stamp){
            this. aWin = aWin;
            this. stamp = stamp;
        } 
        
        // 이겼을땐 더 작은값으로
        void changeWin (int newStamp) {
            stamp = Math.min(stamp, newStamp);
        }
        
        // 졌을땐 더 큰 값으로
        void changeLose (int newStamp) {
            stamp = Math.max(stamp, newStamp);
        }
        
        // 진거에서 이긴걸로 바뀔때
        void makeWin (boolean winner,int newStamp){
            aWin = winner;
            stamp = newStamp;
        }
        
        
    }
    
    int BOARD [][] ;
    int rowSize ;
    int colSize ;
     
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        
        BOARD = board;
        rowSize = board.length;
        colSize = board[0].length;
        

        Result answer ; 
        answer = DFS(true, aloc, bloc, 0);
        
        
        return answer.stamp;
    }
    
    
    int[] dr = {0, 0, -1, 1};
    int[] dc = {-1, 1, 0, 0};
    
    // Result bestChoice;
    
    Result DFS (boolean aTurn, int[] aLoc, int[] bLoc, int depth) {
     
        // 현재위치 미리 세팅
        int [] curr = new int [2];
        if(aTurn) curr = aLoc;
        else curr = bLoc;
        
        // 기저조건
        /*
        1. 이번턴에 내 발판이 사라졌어
        2. 더이상 갈곳이 없어
        */
        
        // 발판 실종
        if(BOARD[curr[0]][curr[1]] == 0) {
            return new Result(!aTurn, depth);
        }
        
        // 움직임 불가
        boolean canGo = false;
        for(int d=0; d<4; d++){
            
            int nr = curr[0] + dr[d];
            int nc = curr[1] + dc[d];
            
            if(nr >= rowSize || nr < 0 || nc >= colSize || nc < 0) continue;
            if(BOARD [nr][nc] == 0) continue;
            
            canGo = true;
        }
        if(!canGo) {
            return new Result(!aTurn, depth);
        }
        
        
        // 재귀부분
        /*
        a인지 b인지 보고 
        그거에 따라 a나 b 위치 이동시켜서 
        for 문 돌려주기
        */
        

        
        //결과 
        Result bestChoice = new Result(!aTurn, 0); // 미리 최악의 수로 저장
        
        // 이동하면서 현재 발판 미리 사라짐
        BOARD[curr[0]][curr[1]] = 0;
        
        //범위랑 이동가능여부 체크 
        for(int d=0; d<4; d++){
            
            int nr = curr[0] + dr[d];
            int nc = curr[1] + dc[d];
            
            // 못가 !
            if(nr >= rowSize || nr < 0 || nc >= colSize || nc < 0) continue;
            if(BOARD [nr][nc] == 0) continue;
            
            // 다음 분기로 넘어가며 돌아오는 결과 임시저장 + 
            Result tmpResult ;
            if(aTurn) tmpResult = DFS(!aTurn, new int[]{nr, nc}, bLoc, depth +1);
            else      tmpResult = DFS(!aTurn, aLoc, new int[]{nr, nc}, depth +1);
            
            // 최선의 선택 저장
            if(aTurn == tmpResult.aWin) { // 이겼어
                if(aTurn == bestChoice.aWin) // 이미 승리 처리
                    bestChoice.changeWin(tmpResult.stamp);
                else // 승리처리 x
                    bestChoice. makeWin(aTurn, tmpResult.stamp);
                
            }
            else { // 졌어
                if(aTurn != bestChoice.aWin) // bestChoice도 패배일 경우에만 업데이트
                    bestChoice.changeLose(tmpResult.stamp);
            }
            
        } // end of for - d
        
        // 발판 다시 원상복구 해주기
        BOARD[curr[0]][curr[1]] = 1;

        
        return bestChoice;
    }
    


    
    
}