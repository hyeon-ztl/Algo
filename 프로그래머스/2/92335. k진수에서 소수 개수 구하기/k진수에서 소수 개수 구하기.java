import java.io.*;
import java.util.*;

class Solution {
    
    int answer;
    
    public int solution(int n, int k) {
        answer = 0;
        
        long zinsu = makeZinsu(n, k);

        
        long seg = 0;
        long mul = 1;

        
        while(zinsu > 0){
            
            long num = zinsu % 10L;
            
            if(num == 0){
                
                if(seg > 1 && isPrime(seg)) {                    
                    answer ++;
                }
                
                seg = 0;
                mul = 1;
            }
            
            else {
                // 저장해주기
                seg += mul * num;
                mul *= 10;
            }
            
            zinsu /= 10;

        }
        
        // seg가 맨마지막에 남을테니 한번 더 처리
        if(seg != 0 && isPrime(seg)){
            answer ++;
        }
        

        
        return answer;
    }
    
    long makeZinsu(int n, int k){
        
        long zinsu = 0;
        int idx = 0;
        
        while (n >= k){
            zinsu += (n % k) * Math.pow(10, idx++); 
            n /= k;
        }
        
        return zinsu + ((long)n * (long)Math.pow(10, idx++));        
    }
    
    
    
    boolean isPrime(long num){
        if(num < 2) return false;
        if(num % 2 == 0) return num == 2;
        if(num % 3 == 0) return num == 3;
        
        for(int i= 5; i <= num / i; i+= 6){
            if( num % i == 0 || num % (i +2) == 0) return false;
        }
        
        return true;
        
    }
}