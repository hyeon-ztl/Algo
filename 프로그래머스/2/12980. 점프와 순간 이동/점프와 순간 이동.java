import java.util.*;

public class Solution {
    public int solution(int n) {
        
        int answer = 0;

        while (true){
            if(n == 1 || n== 2){
                break;
            }
 
            
            if(n % 2 == 0){
                n /= 2;
            } 
            else {
                n -= 1;
                answer ++;
            }
            
        }

        

        return answer + 1;
    }
}