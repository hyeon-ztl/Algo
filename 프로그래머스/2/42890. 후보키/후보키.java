import java.io.*;
import java.util.*;

class Solution {
    
    Set<Set<Integer>> huboKey ;
    String [][] RELATION ;
    int colSize;
    int tupSize;
    int answer ;
    
    public int solution(String[][] relation) {
        answer = 0;
        
        RELATION = relation;
        colSize = relation[0].length;
        tupSize = relation.length;
        
        
        huboKey = new HashSet<>();
        
        for(int i=1; i<= colSize; i++){
            combine(0,0,i, new HashSet<>() ) ;
        }
        
        
        return huboKey.size();
    }
    
    void combine (int idx, int sidx, int selSize, Set<Integer> cols){
        // 최소성 검증
        // if(huboKey.contains(cols)) return; // 
        
        // 기저조건
        if(selSize == sidx) { // 선택완료
            check(cols);
            
            return;
        }
        
        else if(idx == colSize) return; // 끝까지 도달
        
        
        // 재귀부분
        // 선택
        cols.add(idx);
        combine(idx+1, sidx+1, selSize, cols);
        cols.remove(idx);
        
        //미선택
        combine(idx+1, sidx, selSize, cols);
        
    }
    
    void check(Set<Integer> cols){
        
        Set<String> checkSet = new HashSet<>();
        
            /*
                여기서 유일성 확인을 위해, column 묶음을 set으로 사용하면 안됐음!
                왜냐면 속성이 달라도 만약 안의 값이 똑같은 애들이 들어온다면, 유일한데도 유일하지 않는다고 오판 가능
                이름 | 동생 이름
                ben     andrew
                anderw  ben
                
                같은 경우!! 
            */
            
        for(int i=0; i<tupSize; i++){ // 튜플 갯수만큼
            

            String tmp = "";
            for(int col: cols){ // 칼럼들을 넣어서 set 만들어주고
                tmp += RELATION[i][col];
            }
            checkSet.add(tmp); // 유일성 검증을 위해 중복체크
        }
        
        if(checkSet.size() != tupSize) return; // 유일성 실패
        
        // 최소성 검증
        for(Set<Integer> huboSet : huboKey){
            if(cols.containsAll(huboSet)) return; // 만약 확정된 후보키 집합을 cols 가 포함한되면 최소성 실패
        }
        
        // 전부다 성공
        huboKey.add(new HashSet<>(cols));
        
        return;
        
    }
}