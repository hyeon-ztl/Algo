import java.util.*;
    
// 최대한 limit에 가깝게 태우는것이 포인트
// 아 2명까지만 태울 수 있구나 .. 잘못풀었다 ..

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        int size = people.length;
        Arrays.sort(people); // 정렬
        
        int start = 0; // 첫 index
        int end = size -1; // 맨 마지막 index 
        
        int boat = people[start]; // 첫번째 애 보트에 태워놓음

        
        while(start <= end){
            boat = people[start];
            
            // 만약 limit 보다 더 안에 들어와서 두명 탑승 성공
            if(people[start] + people[end] <= limit){ 
                start ++;
                end --;
                boat = 0; // 보트 비워주기 (맨마지막에 보트 비어있는지 확인하기 위해)
            }
            // 만약 초과한다 
            else {
                end --; 
            }
  
                            answer ++; // 무거운애 보트 태워서 보내기 

        }
        // if(boat != 0) answer ++;
        
        return answer; 
    }
}