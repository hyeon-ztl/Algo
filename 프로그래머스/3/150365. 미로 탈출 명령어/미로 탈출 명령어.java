import java.io.*;
import java.util.*;


class Solution {
    
    int [] dr = {1, 0, 0, -1};
    int [] dc = {0, -1, 1, 0};
    char [] ch = {'d', 'l', 'r', 'u'};
    
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "impossible";
        
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        
        outer : while(k > cnt++){
            
            for(int d=0; d<4; d++){
                int nx = x + dr[d];
                int ny = y + dc[d];
                
                if(nx < 1 || nx > n || ny < 1 || ny > m) continue;
                int dis = Math.abs(r - nx) + Math.abs(c - ny);
                if(dis > k - cnt) continue;
                
                sb.append(ch[d]);
                            // System.out.println(sb.toString());

                x = nx;
                y = ny;
                
                continue outer; // 바깥쪽 루프 돌리기
            }
            
           
            return answer;            
            
        }
        
        answer = sb.toString();
        
        return answer;
    }
}