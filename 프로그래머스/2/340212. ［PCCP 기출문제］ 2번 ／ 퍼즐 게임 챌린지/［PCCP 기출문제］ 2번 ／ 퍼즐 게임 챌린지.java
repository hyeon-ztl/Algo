import java.io.*;
import java.util.*;
// 범위조심 제한범위상에선 괜찮아 보여도 합산과정에서 초과날 수 있음 , 이분탐색할때 조건식축과 left right 축을 분리해서 생각할 것
class Solution {
    
    class Score {
        long success;
        long fail;
        
        Score (
            int success, int fail
              ) {
            this. success = success;
            this. fail = fail;
        }
        
        void update (int curr, int prev) {
            success += curr;
            fail += prev;
        }      
        
    }
    
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        int size = diffs.length;
        
        TreeMap <Integer, Score> map = new TreeMap<>();
        
        for(int i=0; i<size; i++){
            int lev = diffs[i];
            int time = times[i];
            int prevTime = 0;
            if(i > 0) prevTime = times[i-1];
            
            if(map.containsKey(lev)) map.get(lev).update(time, prevTime);
            else map.put(lev, new Score(time, prevTime));
        }
        
        Arrays.sort(diffs);
        int left = diffs[0];
        int right = diffs[size -1];
        
        while(left <= right){
            int mid = left + (right - left) / 2;
            long totalTime = 0;
            
            // 총시간 계산하기 
            for(int key : map.keySet()){
                Score curr = map.get(key);
                int k = key - mid;
                
                //난이도가 쉬울때
                if(k <= 0) totalTime += curr.success;
                
                //난이도가 어려울때
                else totalTime += curr.success * (k+1) + curr.fail*k;
            }
            
            //System.out.println("t: "+totalTime);
            //System.out.println("mid: "+mid+"\n");
                             
           
            //if(totalTime == limit) return mid; // 정확히 시간 안에 들어옴
            if (totalTime <= limit){
                right = mid -1;
                answer = mid;
            }
            else {
                left = mid+1;
            }
            
        }
        
        
        
        
        return answer;
    }
}