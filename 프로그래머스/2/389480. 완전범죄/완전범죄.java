import java.io.*;
import java.util.*;




class Solution {
    int N;
    int M;
    int [][] INFO;
    int size;
    int answer ;
    
    int memo [][];
    
    public int solution(int[][] info, int n, int m) {
        N = n;
        M = m;
        INFO = info;
        size = info.length;
        int INFIT = 987654321;
        answer = INFIT;
        
        memo = new int [size][120];
        
        for(int i=0; i<size; i++)
        Arrays.fill(memo[i], INFIT);
        
        DFS(0, 0, 0);
        
        if(answer == INFIT) return -1;
        
        return answer;
    }
    
    void DFS (int idx, int a, int b){
        // 기저
        if(a >= N || b >= M) return;
        if(a >= answer) return ;
        
        if( idx >= size ) {
            // 잡앗따
            answer = Math.min(a, answer);
            return;
        }
        
        if(memo[idx][b] > a){
            memo[idx][b] = a;
        }
        else return;
        
        // a선택
        DFS(idx +1, a + INFO[idx][0], b);
        
        // b 선택
        DFS(idx +1, a, b+ INFO[idx][1]);
        
    }
}