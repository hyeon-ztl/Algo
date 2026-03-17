import java.io.*;
import java.util.*;


class Solution {
    
    public int solution(int n, int[] tops) {
        long answer = 0;
   
        long dp[] = new long [2*n+1];
        
        dp[0] = 1;
        dp[1] = 2;
        if(tops[0] == 1) dp[1] ++;
        
        for(int i=2; i<2*n+1; i++){
            dp[i] = dp[i-1] + dp[i-2];
            if(i%2 != 0 && tops[(i-1)/2] == 1) dp[i] += dp[i-1];
            dp[i]%= 10007;
        }
        
        answer = dp[2*n] % 10007;
        
        return (int)(answer);
    }
}