import java.io.*;
import java.util.*;

class Solution {
    
    int distSize;
    int weakSize;
    
    int[] DIST;
    int[] WEAK;
    
    int N;
    
    public int solution(int n, int[] weak, int[] dist) {
        int answer = 1;
        Arrays.sort(dist);
        
        distSize = dist.length;
        weakSize = weak.length;
        N = n;
        
        DIST = dist;
        WEAK = weak;
        isFinish = false;
        
        
        // 바보같이 여기서 dist
        while (answer <= distSize) {
            combine(0, 0, answer, new int [answer]);
        
            if (isFinish) return answer;
            answer ++;
        }
        
        return -1;
    }
    
    boolean isFinish;
    
    void combine (int idx, int sidx, int num, int [] arr){
        if(isFinish) return;
        
        // 기저조건
        if(sidx == num){
            // 체크하기
            if(check(num, arr)) isFinish = true;
            
            return;
        } 
        else if (idx >= weakSize){
            return;
        }
        
        
        // 재귀부분
        
        // 선택
        arr[sidx] = idx;
        combine (idx+1, sidx+1, num, arr);
        
        // 미선택
        combine (idx+1, sidx, num, arr);
        
    }
     boolean check (int num, int [] arr){
        // 끊어주기 규칙 
        
    /*
    1. 무조건 바로 앞숫자와의 관계를 끊어준다고 생각
    2. 거리측정 (1,9)
        - 끊어준 숫자부터 시계방향으로 탐색
        - 다음 끊긴 숫자 이전 숫자까지 탐색해서 거리저장
        - 그 거리를 정렬해주고 할수 있는지 확인하기 
    */
        int [] tmpArr = new int [num]; 
        int idx = 0;
        
        for(int k=0; k<num; k++){
            
            // 거리를 탐색할 첫 ~ 끝 인덱스 정해주기 arr[k] , arr[k+1] -1
            int first = arr[k]; 
            int next = ((k >= num-1) ? arr[0] : arr[k+1]) -1;
            
            // 만약 다음문이 0번 인덱스의 취약지점이라면 -1 인덱스가 되지 않도록 끝번인덱스로 바꿔주기
            if(next < 0) next = weakSize-1; 
            
            int tmpDist ;
            if(first > next) {
                 tmpDist = N - WEAK[first] + WEAK[next];
            }
            else {
                tmpDist = WEAK[next] - WEAK[first];
            }
            
            tmpArr [idx++] = tmpDist;
        } 
            
         Arrays.sort(tmpArr); // 정렬해주고
         
         for(int i=0; i<num; i++){
             if(tmpArr[num-1-i] > DIST[distSize-1-i]) return false;
         }   
        
         return true;
    }

}