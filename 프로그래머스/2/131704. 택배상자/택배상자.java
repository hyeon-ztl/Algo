import java.io.*;
import java.util.*;


class Solution {
    public int solution(int[] order) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        int idx = 0; // 스택에 들어간 값인 동시에 idx가 됨 // 안도 ㅐ 스택은 peek할수있으니까
        
        for(int o : order){
                        
            if(o <= idx){ // 이미 스택에 들어간 값임 
                int tmp = stack.peek();
                if(o == tmp) {
                    stack. pop();
                    answer ++;
                }
                else {
                    return answer;
                }
                
            }
            else if(o > idx){ // 스택에 안들어간 값
                // 스택에 넣고
                while (o -1 > idx){
                    stack.push(++idx);
                }
                idx ++; // 택배 트럭에 상차
                answer ++;
            }
            
            
            
        }
        
        
        return answer;
    }
}