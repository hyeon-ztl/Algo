import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(String[] info, String[] querys) {
        init ();
        int[] answer = new int[querys.length];

        
        for(String in : info){
            backTrack(0, in.split(" "), "");
            
        } 
        
        for(String key: map.keySet()){
            Collections.sort(map.get(key));
        }
        
        // 전처리 끗
        
        int idx = 0;
        for(String query : querys){
            String [] qs = query.split("and");
            
            String question = "";
            for(int i=0; i<3; i++){
                question+= change.get(qs[i].trim());
            }
            
            String lastQs [] = qs[3].trim().split(" ");
            question += change.get(lastQs[0]);
            
            if(!map.containsKey(question)) answer[idx++] = 0;
            else answer[idx++] = lowerBound(map.get(question), Integer.parseInt(lastQs[1]));
        }
        
        return answer;
    }
    
    int lowerBound (List<Integer> list, int num) {
        // num 이상인 사람은 모두 몇명
        int size = list.size();
        
        int top = size -1;
        int bottom = 0;
        int ans = 0;
        
        if(num > list.get(size-1)) return 0; 
        else if(num < list.get(0)) return size;
        
        while(top >= bottom) {
            int mid = top + (bottom-top) /2 ;         

            if(list.get(mid) >= num){
                top = mid-1;
                ans = mid;
            }
            else {
                bottom = mid+1;
            }
        }
        
        return size - ans ;
    }
    
    
    void backTrack(int depth, String[] info, String key) {
        //기저조건
        if(depth == 4){
            List<Integer> tmpList = map.getOrDefault(key, new ArrayList<>());
            tmpList.add(Integer.parseInt(info[4]));
            map.put(key, tmpList);       
            return;
        }
        
        //선택
        backTrack(depth+1, info, key + change.get(info[depth]));
        
        //미선택
        backTrack(depth+1, info, key+"-");
    }

    Map <String, List<Integer>> map;
    Map <String, String> change ;
    void init () {
        change = new HashMap<>();
        map = new HashMap<>();
        
        change.put("cpp", "0");
        change.put("java", "1");
        change.put("python", "2");
        
        change.put("backend", "0");
        change.put("frontend", "1");
        
        change.put("junior", "0");
        change.put("senior", "1");
        
        change.put("chicken", "0");
        change.put("pizza", "1");
        
        change.put("-", "-");   
    }

}