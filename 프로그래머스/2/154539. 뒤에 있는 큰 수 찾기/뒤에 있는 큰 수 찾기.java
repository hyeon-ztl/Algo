import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int size = numbers.length;
        int[] answer = new int [size];
        
        Stack<Integer> stack = new Stack<>();
        
        for(int i= size-1; i>=0; i--){
            int tmp = numbers[i];
            
            while(!stack.isEmpty() && stack.peek() <= tmp) stack.pop(); // 나보다 큰놈 나올때까지 pop, 큰놈 나오면 stop
            if(stack.isEmpty()) answer[i] = -1;
            else answer[i] = stack.peek();
            
            stack.push(tmp);
            
        }

        return answer;
    }
}