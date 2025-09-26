import java.io.*;
import java.util.*;

class Solution {
    int banSize;
    int userSize;
    int answer;
    int [] arr;
    boolean [] visited;
    String [] user;
    String [] banned;
    
    
    public int solution(String[] user_id, String[] banned_id) {
        answer = 0;
        
        userSize = user_id.length;
        banSize = banned_id.length;
        
        user = user_id;
        banned = banned_id;
        
        arr = new int [banSize];
        visited = new boolean [userSize];
        
        set = new HashSet<>();
       
        dfs(0);
        
        return set.size();
    }// end of main
    
    Set<String> set ;
    StringBuilder sb;

    
    void dfs (int idx){
        // 기저 조건
        if(idx >= banSize){
             sb = new StringBuilder();
            
            for(boolean visit : visited){
                if(visit){
                    sb.append("t");
                }
                else{
                    sb.append("f");
                }
            }
            
            set.add(sb.toString());
            
            return;
        }
        
        // 재귀부분
             loop: for(int u=0; u<userSize; u++){
                 // 이미 이전에 선택한 애면 넘어가
                 if(visited[u]) continue loop;
                 
                 // 길이가 다르면 넘어가
                    int len = banned[idx].length();
                    if(len != user[u].length()) continue loop;
                
                 // 자 그러면 이제 그 user 문자열로 비교를 해보자 
                    for(int i=0; i<len; i++){
                        // 마스킹은 넘어가
                        if(banned[idx].charAt(i) == '*') continue;
                        // 다르면 바로 다음 단어로 점프
                        if(banned[idx].charAt(i) != user[u].charAt(i)) continue loop;
                    }
                 
                    // 통과한자만이 도달하는 공간
                    visited[u] = true;
                    dfs(idx+1);
                    visited[u] = false;
                }
        
        
        } // end of dfs
        

}