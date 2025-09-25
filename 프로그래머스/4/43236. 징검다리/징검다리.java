import java.io.*;
import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        
        Arrays.sort(rocks);
        
        int left = 0;
        int right = distance;
        int mid ;
        
        while(left <= right){
            
            mid = (left + right)/ 2;
        
            // 주운 횟수를 통해 검증
            int prev = 0;
            int gap = 0;
            int cnt = 0;
            for(int rock : rocks){
                gap = rock - prev;

                // 최소보다 작으면 치워야지
                if(gap < mid){
                    //prev는 유지
                    cnt ++;
                }
                // 안치우면
                else {
                    prev = rock; // 이전 돌을 업데이트
                }
            }// end of for 
            
            if(distance-prev < mid){
                cnt++;
            }
            else prev = distance;
            
            if(cnt > n){
                right = mid-1;
            }
            else if(cnt <= n){
                answer = mid;
                left = mid+1;
            }
            
        }// end of while
        
        return answer;
    }
}