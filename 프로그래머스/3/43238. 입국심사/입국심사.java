import java.io.*;
import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        long left = 1;
        long right = 0;
        
        for(int time : times){
           right = Math.max(right, (long)time*(long)n);
        }
        long mid = 0;
        
        while (left <= right){
             mid = (left + right)/2;
            
            long people = 0; 
            for(int time : times){
                people += mid /time;
            }
            // System.out.println("mid: "+mid +"\n people: "+people);
            
            if(people >= n){ // 처리량이 더많다, 시간이 남음
                answer = mid;
                right = mid -1;
            }
            else if(people < n){ // 처리량이 더 적다 , 시간 부족
                left = mid +1;
            }    
        }
        
        
        return answer;
    }
}