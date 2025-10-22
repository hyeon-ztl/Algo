import java.io.*;
import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        int answer = 1;
        int n = cards.length;
        
        Set<Integer> inPocket = new HashSet<>(); // 취득한 수
        Set<Integer> notInPocket = new HashSet<>(); // 일단 넘어간 애들
        
        int useOneCoin = 0; 
        int useTwoCoin = 0;
        
        for(int i=0; i<n/3; i++){
            int num = cards[i];
            
            if(inPocket.contains(n + 1 - num)){
                inPocket.remove(n + 1 - num);
                useOneCoin ++;
                coin ++;
            }
            else {
                inPocket.add(num);
            }
            
        }

        for(int i=n/3; i< n; i+=2){

            // ** 먼저 주머니에 넣기, 코인을 사용할때 깎아줄거니, 코인제한은 깎아줄때 걸기
            for(int k =i; k<=i+1; k++){
                int num = cards[k];
                
                // 이미 갖고있는 수중에 짝지가 있음
                if(inPocket.contains(n + 1 - num)){
                    inPocket.remove(n+1-num);
                    useOneCoin ++;
                }

                // 지나간 수 중에 짝지가 있으면 twoCoin에 저장해놓기
                else if(notInPocket.contains(n + 1 - num)){
                    notInPocket.remove(n +1 - num);
                    useTwoCoin ++;
                }

                // 둘다 아니면 임시 set에 넣기
                else {
                    notInPocket.add(num);
                }
            
            }
            
            // ** 다음 턴 갈수 있는지 판별, 사용시점에 코인 깎아주기
            if(coin >= 1 && useOneCoin > 0){
                useOneCoin --;
                coin --;
            }
            else if (coin >= 2 && useTwoCoin > 0){
                useTwoCoin --;
                coin -= 2;
            }
            else {
                break;
            }
            
            answer ++;
        }
        
        
        
        
        return answer;
    }
}