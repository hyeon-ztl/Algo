import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);
        int idx = 1;
        int size = citations.length;
        
        for(int c : citations){
            
            if(size <= c) return size;
            size--;
        }
        
        return answer;
    }
}