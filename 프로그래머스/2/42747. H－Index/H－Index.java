import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] cit){
        
        int answer = 0;
        
        Arrays.sort(cit);
        int size = cit.length;
        for(int i=size-1; i>=0; i--){
            int len = size - i; 
            int h = Math.min(cit[i], len);
            if(answer >= h) break;
            answer = h;
            }
        
        return answer;
    }
}