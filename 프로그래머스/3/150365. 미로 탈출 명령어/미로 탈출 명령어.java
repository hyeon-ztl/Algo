import java.io.*;
import java.util.*;

class Solution {
    String answer ;
    boolean isAnswer ;
    int K ;
    
    int R ;
    int C ;
    
    int N;
    int M;
    
    /*
    실수1. return 위치, if문에 들어온것만 return 해줘서 무한으로 확장해서 계속 메모리 초과남. 그래놓고 내 알고리즘 선택실수로 착각
    -> 일단 바로 sysout 찍어보기
    실수2. 1부터 시작하는지 0부터 시작하는지 체크 제대로 안함
    실수3. up은 -1 이고 down 은 +1임 이거 항상 헷갈려하는듯 함 ..
    
    놓침1. 최적화가 필요함
    놓침2. 얼마에서 스택오버플로우나는지 확인해주기
    
    
    */
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        answer = "impossible";
        isAnswer = false;
        K = k;
        
        N = n;
        M = m;
        
        R = r;
        C = c;
        
        // int a  = -1;
            
            // System.out.println(Math.abs(a));
        // int [] [] [] map = new int [50][50][2500];
        
        int dis = Math.abs(R-x) + Math.abs(C-y);
        
        
        if(dis > k) return answer;
        if(dis % 2 != k % 2) return answer;
        
        // if(Math.abs(R-x) + Math.abs(C-y) > K - k) return;
        
        
        DFS(x, y, 0, "");
        
        return answer;
    }
    
    int [] dr = {1, 0, 0, -1};
    int [] dc = {0, -1, 1, 0};
    
    char [] ch = {'d', 'l', 'r', 'u'};
    
    void DFS (int r, int c, int k, String route){
            // System.out.println(route);
            // System.out.println("r: "+r+", c: "+c);
        
        
        if(k >= K) {
            if(r == R && c == C) {
                answer = route;
                isAnswer = true;
            }
            return;
        } // END OF K
        
        if(Math.abs(R-r) + Math.abs(C-c) > K - k) return;
        
        // 홀짝 최적화하기? 
        
        
        
        if(!isAnswer) {
            
            for(int d=0; d<4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                if(nr < 1 || nr > N || nc < 1 || nc > M) continue;
                
                DFS(nr, nc, k+1, route + ch[d]);
            }
            
            
        }
        
    }// END OF MATHOD
    
}