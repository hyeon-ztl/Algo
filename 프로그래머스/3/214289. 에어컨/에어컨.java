import java.io.*;
import java.util.*;

/*
5 ~ 15 사이
5 or 1 소비
*/

class Solution {
    public int solution(int temp, int t1, int t2, int a, int b, int[] onboard) {
        int size = onboard.length;
        
        temp += 10;
        t1 += 10;
        t2 += 10;
        
        int [][] dp = new int [size][51]; // index 온도 항상 -10 해주기
        int bigNum = Integer.MAX_VALUE;
        int answer = bigNum;

        
        for(int [] d: dp)
        Arrays.fill(d, bigNum);
        
        // 0분 설정해주기
        dp[0][temp] = 0; // 소비전력은 온도가 올라갈때 동시반영해주기
        
        for(int time=0; time<size-1; time++){
            int start = 0;
            int end = 50;
            
            if(onboard[time] == 1) { // 탑승중일땐 탑승중인 부분만 보기
                start = t1;
                end = t2;
            }
            
            for(int on = start; on <= end; on++){
                if(dp[time][on] == bigNum) continue;
                int curr = dp[time][on];
                
                
                // on - a
                if(on+1 <= 50) dp[time+1][on+1] = Math.min(dp[time+1][on+1], curr+a);
                if(on-1 >= 0) dp[time+1][on-1] = Math.min(dp[time+1][on-1], curr+a);
                
                // 유지 - b
                dp[time+1][on] = Math.min(dp[time+1][on], curr + b);
                
                // off - 0
                int offNext = 0;
                if(on > temp) offNext = -1;
                else if (on < temp) offNext = 1;
                
                if(on + offNext >= 0 && on + offNext <= 50)
                dp[time+1][on+offNext] = Math.min(dp[time+1][on+offNext], curr);
                
            }
        }
        
        int start = 0;
        int end = 50;
        if(onboard[size-1] == 1) {
            start = t1;
            end = t2;
        }
        
        for(int i=start; i<=end; i++){
            answer = Math.min(answer, dp[size-1][i]);
        }

        
        return answer; 
    }
}