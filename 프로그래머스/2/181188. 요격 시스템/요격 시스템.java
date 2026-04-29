import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        Arrays.sort(targets, (a,b) -> 
           a[1]-b[1]);
        
        int point = -1; //  항상 left
        
        for(int[] t : targets){
            int s = t[0];
            int e = t[1];
            
            if(s < point) continue;
            else {
                point = e;
                answer ++;
            }
        }
        
        
        return answer;
    }
}