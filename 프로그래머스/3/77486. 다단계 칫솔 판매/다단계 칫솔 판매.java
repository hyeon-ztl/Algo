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
            this. sell += tmp * 100; // 칫솔 개당 백원
        }
        
        void addFee (int fee) {
            this. sell += fee; // 자식에게 받은 수수료 누적해주기 
        }
    }
    Map<String, Node> map = new HashMap<>();
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int memSize = enroll.length;
        int[] answer = new int [memSize];
        
        // 그래프 만들어주기
        for(int e=0; e<memSize; e++){
            map.put(enroll[e], new Node (e, referral[e]));
        }
        
        // 판매금액 넣어주기
        for(int s=0; s<seller.length; s++){
            map.get(seller[s]).makeSell(amount[s]);
        }
        
        for(int e=memSize-1; e>=0; e--){
            Node curr = map.get(enroll[e]);
            
            //내가 번돈 fix
            int extra = curr.sell / 10;
            answer[e] = curr.sell -extra;;
            
             // 부모 있으면 상납해주기
            if(!curr.parents.equals("-")){
                map.get(curr.parents).addFee(extra);
            }
        }        
        
        return answer;
    }
    
    /*
        가입한 순서대로 돼 있는거면 굳이 dfs 할필요 없지 않나? 
        그냥 맨끝부터 순회해도 역전은 안되는거잖아
        그냥 맨끝부터 순회하면 될듯? 
    */
    
   
}