import java.io.*;
import java.util.*;

class Solution {
    
    class Node {
        int idx;
        String parents;
        int sell;
        
        Node (int idx, String parents) {
            this. idx = idx;
            this. parents = parents;
            this. sell = 0;
        }
        
        void makeSell (int tmp) {
            int extra = (tmp * 10); // 칫솔 개당 백원
            answer[idx] += tmp * 90;
            
            if(!parents.equals("-") && extra != 0) map.get(parents).addFee(extra);
        }
        
        void addFee (int fee) {
            int extra = fee / 10;
            answer [idx] += fee - extra;
            
            if(!parents.equals("-") && extra != 0) map.get(parents).addFee(extra);
            
        }
    }
    Map<String, Node> map = new HashMap<>();
    int [] answer ;
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int memSize = enroll.length;
        answer = new int [memSize];
        
        // 그래프 만들어주기
        for(int e=0; e<memSize; e++){
            map.put(enroll[e], new Node (e, referral[e]));
        }
        
        // 판매금액 넣어주기
        for(int s=0; s<seller.length; s++){
            map.get(seller[s]).makeSell(amount[s]);
        }
        

        
        return answer;
    }
    
    /*
        가입한 순서대로 돼 있는거면 굳이 dfs 할필요 없지 않나? 
        그냥 맨끝부터 순회해도 역전은 안되는거잖아
        그냥 맨끝부터 순회하면 될듯? 
    */
    
   
}