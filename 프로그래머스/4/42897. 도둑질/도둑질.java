import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] money) {
        int answer = 0;
        int size = money.length;
        
        // 첫집을 털었음
        int O1 [] = new int [size] ;
        int X1 [] = new int [size] ;
        O1[2] = money[0] + money[2]; 
        X1[2] = money[0];
        
        
        // 첫집을 안털었음
        int O2 [] = new int [size] ;
        int X2 [] = new int [size] ;
        O2[2] = money[2];
        X2[2] = money[1];
        
        for(int i=3; i<size; i++){
            // 1부터
            O1[i] = X1[i-1] + money[i]; // 털려면 직전에 안털어야함
            X1[i] = Math.max(X1[i-1], O1[i-1]); // 직전에 털었건 안털었건 둘다 가능
            
            // 2
            O2[i] = X2[i-1] + money[i];
            X2[i] = Math.max(X2[i-1], O2[i-1]);
        }
        
        // 1 첫집을 털었으면, 맨마지막에 안턴걸로 끝나야함
        // 2 첫집을 안털었으면, 맨마직에 털어도 안털어도 상관없음
        
        answer = Math.max(X1[size-1], Math.max(O2[size-1], X2[size-1]));
                         
//         System.out.println("O1: "+Arrays.toString(O1));
//         System.out.println("X1: "+Arrays.toString(X1));
//         System.out.println();
        
//         System.out.println("O2: "+Arrays.toString(O2));
//         System.out.println("X2: "+Arrays.toString(X2));
        
        
        
        return answer;
    }
}