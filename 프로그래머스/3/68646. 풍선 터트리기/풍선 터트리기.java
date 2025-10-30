import java.io.*;
import java.util.*;

class Solution {
    

    public int solution(int[] a) {
        int answer = 2;
        int size = a .length;
        
        TreeSet<Integer> leftSet = new TreeSet<>();        
        TreeSet<Integer> rightSet = new TreeSet<>();
        
        leftSet.add(a[0]);
        
        for(int i=2; i<size; i++){
            rightSet.add(a[i]);
        }
        
        for(int i=1; i<size-1; i++){
            int curr = a[i];

            int leftMin = leftSet.first();
            int rightMin = rightSet.first();
            
            // 이후를 위한 작업
            int next = a[i+1];
            rightSet.remove(next);
            leftSet.add(curr);       
            
            // 정답 산정
            if(leftMin < curr && rightMin < curr) continue;
            answer ++;
            
        }
        
        
        return answer;
    }
}