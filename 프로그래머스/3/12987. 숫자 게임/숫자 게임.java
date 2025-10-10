import java.io.*;
import java.util.*;

class Solution {
    
    public int solution(int[] A, int[] B) {
        int answer = 0;
        int size = B.length;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        int idx = 0;
        for(int a : A){
            
            if(a < B[idx++]) {
                answer ++;
                // System.out.println("A: "+a+", B:"+B[idx-1]);
            }
            else {
                if(idx >= size) return answer;
                
                while(a >= B[idx]){
                    idx ++;
                    if(idx >= size) return answer;
                }
                idx++;
                answer ++;
                // System.out.println("A: "+a+", B:"+B[idx]);
                
                
            }// end of else
            
            if(idx >= size) return answer;
        }
        
        
        
        return answer;
    }
}