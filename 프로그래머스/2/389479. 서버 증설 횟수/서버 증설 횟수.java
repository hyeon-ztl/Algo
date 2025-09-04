import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        
        int [] server = new int [24];
        int [] check = new int [24];
        
        for(int i=0; i<24; i++){
            
            int lastPlayer = players[i] - server[i]*m;
            int addServer = 0;
            
            if(lastPlayer > 0){
                    addServer = lastPlayer/m;
                answer += addServer;
                check[i] = addServer;
            }// end of if
            
            int time = k;
            int idx = i;
            while(time-- > 0 && idx < 24){
                server[idx++] += addServer;
            }
        }
        
        // System.out.println(Arrays.toString(server));
        // System.out.println(Arrays.toString(check));

        
        return answer;
    }
}