import java.io.*;
import java.util.*;

class Solution {
    public int solution(int temperature, int T1, int T2, int a, int b, int[] onboard) {

        // dp 돌리기 수월하게 0부터로 올림해주기
        int tem = temperature + 10;
        int t1 = T1 + 10;
        int t2 = T2 + 10; 
        
        
        int size = onboard.length;
        int on [][] = new int [size][51];
        
        int INF = 987_654_321;
        
        
        for(int i=0; i<size; i++){
            Arrays.fill(on[i], INF);
        }
        
        on[0][tem] = 0;

        for(int i=0; i<size-1; i++){
            
            // 미탑승
            int start = 0;
            int end = 50;

            // 탑승                    
            if(onboard[i] == 1){
                start = t1;
                end = t2;            
            }
                for(int j=start; j<=end; j++){
                    
                /*
                j 는 현재 온도
                dp니까 어떤방향으로 옮긴다는 생각 x
                현재온도
                tem = j : 그대로 유지는 0, 위 아래 갈때는 a 
                tem > j : 그대로 유지는 b, 위로 0, 아래로 a 
                tem < j : 그대로 유지는 b, 위로 a, 아래로 b 
                */
                    if(on[i][j] == INF) continue;
                    
                    // 현재 온도에 따라 어떻게 작업을 쳐줘야할지 정하는 부분
                    int just = 0;
                    int up = a;
                    int down = a;
                    
                    if(tem > j){
                        just = b;
                        up = 0;
                        down = a;
                    }
                    else if(tem < j){
                        just = b;
                        up = a;
                        down = 0;
                    }
                    
                    // up                                        
                    if(j+1 <= 50){
                        on[i+1][j+1] = Math.min(on[i+1][j+1], on[i][j] + up);
                    }    
                    // down
                    if( j-1 >= 0 ){
                        on[i+1][j-1] = Math.min(on[i+1][j-1], on[i][j] + down);
                    } 

                    // just
                    on[i+1][j] = Math.min(on[i+1][j], on[i][j] + just);
                    
                } // end of j
            
            } // end of i

        
        int answer = INF;
        int start = 0;
        int end = 50;

        // 탑승                    
        if(onboard[size-1] == 1){
            start = t1;
            end = t2;            
        }
        
        for(int i=start; i<=end; i++){
           System.out.println(on[size-1][i]);
            answer = Math.min(answer, on[size-1][i]);
        }
        
        
        
        return answer;
    }
}