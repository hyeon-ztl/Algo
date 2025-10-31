import java.io.*;
import java.util.*;

class Solution {
    
    class Node implements Comparable<Node>{
        int idx; // 작업번호 
        int when; // 요청 시각
        int time; // 소요 시간
        
        Node (int idx, int when, int time){
            this. idx = idx;
            this. when = when;
            this. time = time;
        }
        
        @Override
        public int compareTo (Node o){
            return this.when - o.when;
        }
    }
    
    class WaitNode implements Comparable<WaitNode>{
        int idx; // 작업번호 
        int when; // 요청 시각
        int time; // 소요 시간
        
        WaitNode(int idx, int when, int time){
            this. idx = idx;
            this. when = when;
            this. time = time;
        }
        
        @Override
        public int compareTo (WaitNode o){
            if(o.time == this.time){
                if(o.when == this.when) return this.idx - o.idx;
                return this.when - o.when;
            }
            
            return this.time - o.time;
        }
    }
    
    
    public int solution(int[][] jobs) {
        int answer = 0;// 평균 반환 , 요청부터 종료까지 
        int now = 0;
        int size = jobs.length;
        
        PriorityQueue<Node> firstQ = new PriorityQueue<>();
        PriorityQueue<WaitNode> waitQ = new PriorityQueue<>();
        
        int idx = 0;
        for(int[] job : jobs){
            firstQ. add (new Node (idx, job[0], job[1]));
            idx ++;
        }
        
        // 시작
        Node tmp = new Node (0, 0, 0);
        WaitNode curr = new WaitNode (0, 0, 0);
        
        while (!firstQ.isEmpty() || !waitQ.isEmpty()){

            
            // 시작하기부분도 실수 
            if(waitQ.isEmpty()) { // wait 큐가 비어있을때 
                tmp = firstQ.peek();
                now = tmp.when;                
                while(!firstQ.isEmpty()){
                    tmp = firstQ.peek();
                    if(tmp.when != now) break;
                    tmp = firstQ.poll();
                    waitQ.add(new WaitNode(tmp.idx, tmp.when, tmp.time));
                }
                
            }
            curr = waitQ.poll(); // 아니면 wait큐에서 우선순위 높은애 먼저 뽑기
            
            
            // 현재시각 처리
                if(now > curr.when) now += curr.time; // 이미 이전에 시작했어야하는 애임
                else now = curr.when + curr.time; // now 이후 좀 여유있게 시작? 

                // 정답처리
                answer += now - curr.when;

                
                
            // 현재시각까지 작업큐에 있던애들 다 대기큐로 넣어주기 
            
            while(!firstQ.isEmpty() && firstQ.peek().when <= now) {      // 와일문 null point 실수                 
                Node next = firstQ.poll(); 
                waitQ.add(new WaitNode(next.idx, next.when, next.time));
            }
        }    
        
        
        return answer/size;
    }
}