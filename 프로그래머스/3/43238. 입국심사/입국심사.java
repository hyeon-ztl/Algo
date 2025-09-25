import java.io.*;
import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long mid = 0;
        long answer = 0;
        
        Arrays.sort(times);
        
        long start = 0;
        long end = (long)times[times.length-1] * n;

        
        while(start <= end){
            mid = (start + end)/2;
            
            long person = 0;
            for(int time : times){
                person += mid/time;
            }
            
            // 많이 처리 혹은 딱맞게 처리 -> 줄여주기
            if(person >= n) {
                end = mid -1;
                answer = mid;
            }
            else start = mid + 1;   
        }
        
        return answer;
    }
}