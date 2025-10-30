import java.io.*;
import java.util.*;

class Solution {
    

    public int solution(int[] a) {
        int answer = 2;
        int size = a .length;
        
        int [] L = new int [size];
        int [] R = new int [size];
        
        int leftMin = Integer.MAX_VALUE;
        int rightMin = Integer.MAX_VALUE;
        
        
        
        
        for(int i=0; i<size; i++){
            L[i] = leftMin = Math.min(leftMin, a[i]);
            R[size-1-i] = rightMin = Math.min(rightMin, a[size-1-i]);
        }
        
        for(int i=1; i<size-1; i++){
            int curr = a[i];
            
            if(curr == L[i] || curr == R[i]) answer++;
        }
        
        
        return answer;
    }
}