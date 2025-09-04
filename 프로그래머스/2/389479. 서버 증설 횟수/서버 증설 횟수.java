import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int [] install = new int[24];
        int active = 0;
        int idx = 0;
    System.out.print("[");

        for(int player : players){
            
            if(idx>= k){
                active -= install[idx-k]; // 시간이 지나면 사라진다
            }
            
            int last = player / m - active;
            if(last > 0){
                active += last;
                install[idx] += last;
                answer += last;
            }
            
            idx ++;
        System.out.print(active+", ");
            
        }
        
        System.out.println("\n"+Arrays.toString(install));
        
        
        
        return answer;
    }
}