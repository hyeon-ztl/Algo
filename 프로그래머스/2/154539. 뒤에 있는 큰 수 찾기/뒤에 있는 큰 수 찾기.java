import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        
        
        int size = numbers.length;
        int[] answer = new int [size];
        answer[size-1] = -1;
            
        Stack<Integer> stack = new Stack<>();
        stack.push(numbers[size-1]);
        
        for(int i=size-2; i>=0; i--){
            
            int tmp = numbers[i];
            int fromStack = stack.pop(); // 항상 무조건 하나는 들어있다는 전제 조건 필요, 근데 성립함
            boolean isEmpty = false;
            while(tmp >= fromStack ){
                if(stack.isEmpty()) { // 만약 끝까지 돌렸는데 내가 젤 큰놈이야
                    isEmpty = true;
                    break;
                }
                fromStack = stack.pop();
            }
            
            if(isEmpty) { // empty여서 루프를 빠져나온 경우에는 -1로
                answer[i] = -1;
                stack.push(tmp); // 그리고 나 자신 넣어주기 (내가 젤 큰놈)
            }
            else{ // 만약 더 큰걸 발견한 경우에는 이렇게 
                answer[i] = fromStack;
                stack.push(fromStack); // 나보다 큰애 다시 넣어주고
                stack.push(tmp); // 나 자신도 넣어주기
            }   
            
        }
        

        
        
        
        return answer;
    }
}