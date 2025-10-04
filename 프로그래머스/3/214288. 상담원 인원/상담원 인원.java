import java.io.*;
import java.util.*;

class Solution {
    
    int answer;
    int lastPerson;
    List<Integer>[] mento; 
    int k;
    int [][] reqs;
    
    public int solution(int K, int n, int[][] REQS) {
        answer = 987654321;
        k = K;
        lastPerson = n - k;
        mento = new ArrayList [k];
        reqs = REQS;
        
        // 일단 한명씩은 다 추가해 놓기
        for(int i=0; i<k; i++){
            mento[i] = new ArrayList<>();
            mento[i].add(0);
        }
        
        make(0,0);
        
        return answer;
    }
    
    
    void make (int idx, int sidx){
        
        // 기저조건
        if(sidx >= lastPerson){
            // 검증함수 호출
            answer = Math.min(answer, check());
            return;         
        }
        else if(idx >= k){ // 선택 실패
            return;
        }
        
        // 재귀부분
        // 선택 - 중복 또 선택 가능하도록
        mento[idx].add(0);
        make(idx, sidx +1);
        mento[idx].remove(0);
            
        // 미선택
        make(idx+1, sidx);
        
        
    }
    
    int check () {
        
        int sum = 0;
        List<Integer>[] newList = new ArrayList[k];
        
        for(int i=0; i<k; i++){
            newList[i] = new ArrayList<>(mento[i]);
        }
        
        
       loop: for(int[] req : reqs){
            int type = req[2];
            int start = req[0];
            int time = req[1];
            
            List<Integer> currs = newList[type-1];
           
            int minIdx = 0;
            int minEtime = 987654321;
            
            for(int s=0; s<currs.size(); s++){
                
                int etime = currs.get(s);
                
                // 들어갈 수 있으면, 바로 넣어주기
                if(etime <= start){
                    currs.set(s, start + time);
                    continue loop;
                }
                
                // 젤 들어갈만한 곳 업데이트 해놓기
                if(etime < minEtime){
                    minIdx = s;
                    minEtime = etime;
                }                   
            }// end of curr
           
           // 바로 넣어주기 실패해서 여기까지 왔으면
            currs.set(minIdx, minEtime + time);
            sum += minEtime - start;
          
        }// end of req
          
        System.out.println(sum);
        return sum;
    }
    
    
}