import java.io.*;
import java.util.*;

class Solution {
    public int solution(int N, int number) {
        
                
        if(N == number){ // 둘이 같으면 바로 끝내기
            return 1;
        }
        
        int [] numArr = new int [9];
        numArr [1] = N;
        for(int i=2; i<= 8; i++){
            numArr[i] = numArr[i-1]*10 + N;
        }
        
        
        Set<Integer> [] set = new HashSet[9];
        set [1] = new HashSet<Integer>();
        set[1].add(N);
        
        for(int setIdx = 2; setIdx <= 8; setIdx++){
            set[setIdx] = new HashSet<Integer>();
            
            for(int numsIdx = 1; numsIdx < setIdx; numsIdx ++){ 
                for(int Bitem : set[numsIdx]){
                    for(int Aitem : set[setIdx - numsIdx]){ // set에 있는 숫자들을 돌려야함 여기선 
                        set[setIdx].add(numArr[setIdx]); // 나 자신도 넣어주기
                        set[setIdx].add(Aitem + Bitem);
                        set[setIdx].add(Aitem * Bitem);
                        if(Aitem % Bitem == 0){
                            set[setIdx].add(Aitem / Bitem); 
                        }
                        if(Aitem > Bitem) { // 0이 생기지 않도록 하기 
                            set[setIdx].add(Aitem - Bitem);
                        }
                }
                    
                } // end of item
            } // end of num
            
            if(set[setIdx].contains(number)) { //다 끝나고 성공했는지 확인하기
                return setIdx;
            }
        } // end of s 횟수 
        
        return -1;
        
        
        
    }
}