import java.io.*;
import java.util.*;

class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long answer = 0;

        long left = 0;
        long right = 9_000_000_000_000_000L;
        long size = (long) g.length;
        long ss = (long) a + b;
        
        // 바깥쪽으로 보전하도록 해야함 
        while (left <= right){
            long mid = left + (right - left)/2;
            // long mid = right + (left - right)/2;
            
            long gold = 0;
            long silver = 0;
            long sum = 0;
            
            // 편도 왕복 포함해서 각 마을에서 최대로 보내준거
            for(int i=0; i< size; i++){
                long tmp = (long) mid / t[i]; // 편도 이동 최대 횟수 
                long arrive = (tmp + 1) / 2 ; // 실제로 도달하는 횟수
                   
                gold += Math.min (g[i], w[i] * arrive);
                silver += Math.min(s[i] , w[i] * arrive);
                sum += Math.min((long)g[i] + s[i], w[i] * arrive);
            }
            
            // 지점찾아내기
            if(gold >= a && silver >= b && sum >= ss) { // 과분하다 그럼 줄여 + 보전까지
                answer = mid; // 정답 보전하기
                right = mid - 1;
            }
            else { // 부족하다 
                left = mid +1;
            }
            
        }
        
        
        
        return answer;
    }
}