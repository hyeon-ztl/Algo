import java.io.*;
import java.util.*;

class Solution {
    public String[] solution(String[] orders, int[] course) {
        
        ORDERS = orders;
        COURSE = course;
        sizeOfC = course.length;
        map = new HashMap<>();
        maxValue = new int [11];
        
        for(int i=0; i<orders.length; i++){
            set = new HashSet <> ();
            DFS(i, 0, "");
            
            for(String word : set){
                if(map.containsKey(word)){
                    map.put(word, map.get(word)+1);
                }
                else {
                    map.put(word, 1);
                }
                maxValue[word.length()] = Math.max(maxValue[word.length()], map.get(word));
                }      
            }
        

        
        List<String> list = new ArrayList<>();
    
        for(String k : map.keySet()){
            int curr = map.get(k);
            if(curr < 2) continue;
            if(maxValue[k.length()] == curr){
                list.add(k);
            }
        }
       
        String[] answer = new String [list.size()];
        
        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
        
        Arrays.sort(answer);
        
        // System.out.println(Arrays.toString(maxValue));
        // System.out.println(map.toString());

        
        return answer;
    }
    
    Map <String, Integer> map ;
    String [] ORDERS ;
    int [] COURSE ;
    int sizeOfC;
    int [] maxValue; 

    Set<String> set ;
    
    // 조합 만드는 DFS
    void DFS(int cust, int idx, String word) {
        
        // 기저조건
        int wordSize = word.length();
        
        for(int i=0; i<sizeOfC ; i++){
            if(wordSize == COURSE[i]){
                
                
                char[] tmp = word.toCharArray();
                Arrays.sort(tmp);
                String finish = new String(tmp);
                
                set.add(finish);
                
//                 // 맵전용메서드 외우기
//                 if(map.containsKey(word)){
//                     map.put(word, map.get(word)+1);
//                 }
//                 else {
//                     map.put(word, 1);
//                 }
                
//                 maxValue[COURSE[i]] = Math.max(maxValue[COURSE[i]], map.get(word));
//                 break;    
            }        
        }
        
        if(idx >= ORDERS[cust].length()) return;
            
            
        
        // 재귀부분
            //선택하거나 
            DFS(cust, idx+1, word + ORDERS[cust].charAt(idx));
        
            // 안하거나
            DFS(cust, idx+1, word);


        
    }
    
    
    
}