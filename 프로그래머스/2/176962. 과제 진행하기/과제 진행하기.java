import java.io.*;
import java.util.*;

class Solution {
    
        class Plan implements Comparable<Plan>{
            int start;
            int playTime;
            String name;
            
            Plan (int start, int playTime, String name){
                this.start = start;
                this.playTime = playTime;
                this.name = name;
            }
            
            @Override
            public int compareTo (Plan o){
                return this.start - o.start;
            }
            
            int minus (int time) {
                this.playTime -= time;
                
                if(playTime <= 0) makeAnswer(this.name);
                
                return playTime; // 남은 시간 반환
            }
        }

    
    String [] answer ; 
    int planSize;
    
    public String[] solution(String[][] plans) {
        planSize = plans.length;
        answer = new String [planSize];
        idx = 0;
        
        PriorityQueue<Plan> planQ = new PriorityQueue<>();
        PriorityQueue<Plan> waitQ = new PriorityQueue<>(Collections.reverseOrder());
        
        
        for(String[] plan: plans){
            int hour = Integer.parseInt(plan[1].substring(0,2));
            int min = Integer.parseInt(plan[1].substring(3,5));
            
            planQ.add(new Plan(hour*60 + min, Integer.parseInt(plan[2]), plan[0]));
        }
        
        while(!planQ.isEmpty()){
            Plan now = planQ.poll();
            Plan next = planQ.peek();
            
            if(next == null){ // 큐의 끝에 도달
                makeAnswer(now.name);
                while(!waitQ.isEmpty()){
                    Plan curr = waitQ.poll();
                    makeAnswer(curr.name);
                }
                return answer;
            }
            
            int restTime = next.start - now.start;
            int check = now.minus(restTime);
            
            // check에 시간이 남아있다면
            if(check <= 0){
                while(check < 0 && !waitQ.isEmpty()){
                    now = waitQ.poll(); 
                    check = now.minus(check*-1); // 남은 시간을 넣어줘서 또 남은 시간 계산
                }
                
                if(check >= 1) waitQ.add(now); // 만약 이제 시간을 다 못채운애가 남으면 대기큐에 넣어주기

            }
            else {
                waitQ.add(now); // 대기 큐에 넣어주기
            }
        }
        
    
        return answer;
    }
    
    int idx ;
    
    void makeAnswer (String name){
        answer[idx] = name;
        idx++;
        
        return;
    }
}