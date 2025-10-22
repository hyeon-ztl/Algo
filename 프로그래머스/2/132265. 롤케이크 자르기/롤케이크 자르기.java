import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        
        Set<Integer> leftSet = new HashSet<>();
        Set<Integer> rightSet = new HashSet<>();
        
        int size = topping.length;
        
        int [] leftArr = new int [size];
        int [] rightArr = new int [size];
        
        int leftPrev = 0;
        int rightPrev = 0;
        
        for (int l = 0; l < size; l++){
            int r = size - l -1;
            
            // left
            if(!leftSet.contains(topping[l])){ // 미포함
                leftSet.add(topping[l]);
                leftArr [l] = ++leftPrev;
            }
            else {
                leftArr [l] = leftPrev;
            }
            
            // right
            if(!rightSet.contains(topping[r])){
                rightSet.add(topping[r]);
                rightArr [r] = ++ rightPrev;
            }
            else {
                rightArr [r] = rightPrev;
            }
        }
        
        for(int i=0; i<size-1; i++){
            if(leftArr[i] == rightArr[i+1]) answer ++;
        }
        
        
        return answer;
    }
}