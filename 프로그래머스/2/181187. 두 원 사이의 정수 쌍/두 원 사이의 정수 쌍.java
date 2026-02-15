import java.io.*;
import java.util.*;

class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        
        long x = 0;
        
        long memoTop = 0;
        long memoBottom = 0;
        
        // System.out.println(Math.ceil(num));
        
        while(x < r2){ // x축 오른쪽으로 하나씩 이동
            
            // 맨 위끝 찾기
            memoTop = (long)Math.sqrt((long)r2*r2 - x*x); // 소숫점 버림처리로 안쪽 찾기

            // 아래끝 찾기
            if(x < r1){
                memoBottom = (long)Math.ceil(Math.sqrt((long)r1*r1 - x*x)); // 작은원의 y좌표 소숫점 올림처리
            }
            else memoBottom = 1; // 작은 원의 범위 벗어나면 1로 고정해주기 

            
            // 정답 계산
            answer += Math.max(0, memoTop - memoBottom +1);
            
            // 다음턴 준비
            x ++; 
        }
        
        return answer * 4;
    }
}