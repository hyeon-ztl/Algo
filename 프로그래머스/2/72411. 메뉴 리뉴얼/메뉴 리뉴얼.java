import java.io.*;
import java.util.*;



class Solution {
    
    String[] ORDERS ;
    int [] COURSE ;
    Map<String, Integer> map;
    int tmpMax ;
    PriorityQueue tmpQ;
    
    public String[] solution(String[] orders, int[] course) {
        
        ORDERS = orders;
        COURSE = course;
        
//         int courseSize = course.length;
//         comArr = new HashMap [courseSize];
//         for(int i=0; i<courseSize; i++){
//             comArr[i] = new HashMap<>();
//         }
        
                
        PriorityQueue<String> q = new PriorityQueue<>();

        

        for(int c : course){
            map = new HashMap<>();
            tmpMax = 0;
            
            for(String o : orders){
                combine (o, c, new int[c], 0, 0);
            }
            
            if(tmpMax < 2) continue;
                
            // 여기서 최대 비교 해줘야함 
            for(String key : map.keySet()){
                if(map.get(key) == tmpMax){
                    q.add(key);
                } 
            }         
        }
        
        String[] answer = new String[q.size()];
        
        int idx = 0;
        while(!q.isEmpty()){
            answer[idx++] = q.poll();
        }
        
        
        return answer;
    }
    
    
    void combine (String o, int c, int[] com, int idx, int sidx) {
        // 기저조건
        if(sidx >= c){
            useMap(o, com);
            
            return;
        } // 완성
        
        if(idx >= o.length()) return;
        
        
        // 재귀부분
        combine(o, c, com, idx+1, sidx); // 미선택
        
        com[sidx] = idx;
        combine(o, c, com, idx+1, sidx+1);// 선택   
    }
    
    void useMap (String o, int[] com) {
        
        tmpQ = new PriorityQueue<>();
        
        for(int c : com){
            tmpQ.add(o.charAt(c));
        }
        
        String tmp = "";
        while(!tmpQ.isEmpty()){
            tmp += tmpQ.poll();
        }
        
        
        map.put(tmp, map.getOrDefault(tmp ,0) +1); // 등장횟수 업데이트 해주기
        tmpMax = Math.max(tmpMax, map.get(tmp) ); // 최댓값 미리 업데이트
    }
}