import java.io.*;
import java.util.*;

class Solution {
    
    int N;
    int [] ansArr; 
    int qSize;
    Set<Integer> qArr [];
    
    int answer;
    
    
    public int solution(int n, int[][] q, int[] ans) {
        answer = 0;
        
        N = n;
        ansArr = ans;
        
        qSize = q.length;
        qArr = new HashSet[qSize];
        
        for(int i=0; i<qSize; i++){
            qArr[i] = new HashSet<>();
            
            for(int j=0; j<5; j++){
                qArr[i]. add(q[i][j]);                
            }
        } // 전처리 완료
        
        
        comArr = new int [5];
        
        combine(1,0);
        
        
        return answer;
    }
    
    int [] comArr ; 
    
    void combine (int idx , int sidx){
        // 기저 조건
        if(sidx == 5){
            if(test()) answer ++;
            return;
        }
        if(idx > N) return;
        
        // 재귀부분
            // 미선택
            combine(idx+1, sidx); 
            
            // 선택
            comArr[sidx] = idx;
            combine(idx+1, sidx + 1);
    }
    
    boolean test(){
        
        for(int i=0; i<qSize; i++){
            int tmpCnt = 0;
            
            for(int j=0; j<5; j++){
                if(qArr[i].contains(comArr[j])) tmpCnt ++; // 포함돼 있으면 ++ 해주기
            } // end of j
            
            //이제 갯수 검증
            if(tmpCnt != ansArr[i]) return false;
        }// end of i
        
        return true; // 다 통과 
    }
    
}