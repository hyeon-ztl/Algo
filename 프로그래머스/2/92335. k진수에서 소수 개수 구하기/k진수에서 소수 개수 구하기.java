import java.io.*;
import java.util.*;

class Solution {
    
    int answer;
    
    public int solution(int n, int k) {
        answer = 0;
        
        long zinsu = makeZinsu(n, k);

        // System.out.println(zinsu);

        long doub = 1;
        long tmp = 0;
        
        while(zinsu > 0){
            doub *= 10;
            long pow = zinsu % doub;
            
            // 이전 연산한것과 같다 -> 0 발견
            if(tmp == pow){
            // System.out.println(zinsu);
            // System.out.println(tmp);
                
                // 진수인지 확인하기
                if(isPrime(tmp)){
                    answer ++;
                    // System.out.println("prime: "+ tmp);
                }
                
                // 그부분 잘라내주기
                zinsu -= tmp;
                while(zinsu % 10 == 0  && zinsu != 0 ){
                    zinsu /= 10;                
                }
                
                // tmp랑 doub 초기화
                tmp = 0;
                doub = 1;
            }
            
            // 아니라면 업데이트해주기
            else {
                tmp = pow;
            }             
        }
        
        
        

        // System.out.println(isPrime(12));
        
        return answer;
    }
    
    long makeZinsu(int n, int k){
        
//         long zinsu = 0;
//         // n보다 작으면서 가장 큰 k의 배수 찾기
//         int doub = 0;
        
//         while(Math.pow(k, doub) <= n){
//             doub ++;
//         }
//         doub --; // 선넘은거 되돌려주고
        
//         // 이제 진수 만들어주기
//         while(doub >= 0){
//             // 우선 진수에 나눈 값 더해주고
//             zinsu *= 10;
//             zinsu += n / Math.pow(k, doub);
            
//             // n에 나머지 넣어주기
//             n %= Math.pow(k, doub);
//             doub --;
//         }
        
        long zinsu = 0;
        int idx = 0;
        
        while (n >= k){
            zinsu += (n % k) * Math.pow(10, idx++); 
            n /= k;
        }
        

        return zinsu + ((long)n * (long)Math.pow(10, idx++));        
    }
    
    int makeTen (long zinsu, int k){
        long ten = 0;
        int doub = 0;
        
        while(zinsu > 0){
            long tmp = zinsu % 10; // 자릿수 추출
            ten += tmp * Math.pow(k, doub); // 그 자릿수 
            zinsu -= tmp;
            zinsu /= 10;
            doub ++;
        }
        
        
        return (int)ten;
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