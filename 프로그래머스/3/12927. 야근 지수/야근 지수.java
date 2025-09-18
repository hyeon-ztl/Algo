import java.io.*;
import java.util.*;

/*
핵심은 works들의 표준편차를 줄이는것
일단 숫자들을 정렬시키고 큰놈부터 그리디하게 깎아주기

1-1. 지금 갖고있는 숫자를 다음숫자와 같은 숫자로 만들어주기 
 -> 0번 idx 부터 N번까지는 항상 다 같은 숫자
1-2. 그러다가 내가 갖고 있는 N이 부족하면, 그 갖고 있는 최소한의 N을 쪼개서 사용
 -> 모든 숫자를 다 건드릴 수 있으면 나눈값을 다 빼주고, 나머지는 남은애들한테 분배

2. 근데 다음숫자를 찾다가, 만약 모든숫자가 다 같은 상황이 주어지면 
index out bound 에러 발생 -> 맨마지막 원소를 한칸 추가해서 0으로 초기화 해놓기

3.

*/

class Solution {
    public long solution(int n, int[] input) {
        long answer = 0;
        int N = n;
        // 정렬
        Arrays.sort(input);
        int [] works = new int [input.length+1];
        int sum = 0;
        
        // 내림차순으로 바꿔주기
        for(int i=0; i<works.length-1; i++){
            works[i] = input[input.length-1-i];
            sum += works[i];
        }
        
        /*
         젤 비슷하게 만들어주는것 
         같은숫자를 처리하려는게 아니라 같은숫자로 만들어주는게 핵심이다!
         
        */
        

        int idx = 0;
        
        // 만약 일할 시간이 많이 남았으면 바로 return 
        if(sum <= N){
            return answer;
        }

        while(N>0){
            int now = works[idx];
            int next = works[idx+1];
            
            // 둘이 같은 숫자라면 뒤로 몇개까지 같은숫자인지 체크
            if(now == next){ 
                while(now == next){
                    idx ++;
                    next = works[idx +1];
                }
            }
            
            // 지금까지 같은숫자의 갯수를 전부 다음숫자로 깎아만큼 
            // N이 여유가있다면 바로 깎아주기
            if((now - next)*(idx+1) <= N){
                
                // 깎아주기
                int fromZero = 0;
                while(fromZero <= idx){
                    works[fromZero++] = next;
                }
                N -= (now - next)*(idx+1);
            } // end of If
            
            
            // 아니면 갖고있는 만큼 분배해서 깎아주고 종료하기
            else {
                // 깎아줄 숫자 정하기, 근데 모든 숫자를 다깎아줄거라곤 장담못함
                int minus = N/(idx+1);
                int mod = N%(idx+1);
                
                // 모든 숫자를 다 깎을 수 있을때
                if(minus > 0){
                    
                    int fromZero = 0;
                    while(fromZero <= idx){
                        works[fromZero++] -= minus;
                    }
                    // 나머지도 빼주기 (짬처리)
                    if(mod!=0) {
                        int modIdx = 0;
                        while(mod > 0){
                            works[modIdx++] -= 1;
                            mod --;
                        }
                    }
                    
                    N = 0;
                }
                // 모든 숫자를 다 깎을 순 없을때
                else {
                    int fromZero = 0;
                    while(N > 0){
                        works[fromZero++] -= 1;
                        N --;
                    }
                }
            
                break;
            }
        }// end of while
        
        for(int work : works){
            answer += work*work;
        }
        
        return answer;
    }
}