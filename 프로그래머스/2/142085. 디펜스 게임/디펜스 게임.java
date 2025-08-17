import java.io.*;
import java.util.*;

/*

그리디하게 풀수는 없음
옥수수밭처럼 내가 가지고 있는 k개의 무적권을 무조건 다 소진해야하는데,
그중에서 가장 효율적이게 하려면 내가 도달한 라운드에서 가장 큰 숫자들에만 다 무적권을 쓰는 것
크기가 k인 Priority Queue에다가 계속해서 크기가 가장 큰 숫자들 3개를 넣어놓은 상태로 저장을 하고,
그 queue 전체 합의 크기도 저장해놓기
그러면 배열을 전체 순회할때마다 계속 if문을 돌려야할 것 같은데 괜찮으려나? 
*/

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        
        PriorityQueue<Integer> q = new PriorityQueue<>();
        
        int restN = n; 
        int round = 0;
            
        while(round < enemy.length) {
            
            int thisRound = enemy[round];
            
            // 초반 라운드는 냅다 큐 채우기
            if(round < k){
                q.add(thisRound); 
            }
            // 이번 라운드 애가 큐의 마지막 원소보다 크다면 얘를 면제권 쓰기
            else if(q.peek() < thisRound){
                int fromQueue = q.poll();
                q.add(thisRound);
                
                restN -= fromQueue; // 면제권 안쓴거 처리
            }
            // 면제권 안쓸때
            else {
                restN -= thisRound;
            }
            
            // 최종적으로 이번라운드 성공여부 확인
            if(restN < 0) break; // 0보다 더 적어졌으면 실패 
            
            round++;
        }
        
        
        return round;
    }
}