import java.io.*;
import java.util.*;

class Solution {
    
     Map <Integer, List<Integer>> map;
    
    public int[] solution(int[][] edges) {
        int[] answer = new int [4];
        
        TreeSet<Integer> cntSet = new TreeSet<>();
        Set<Integer> inSet = new HashSet<>();
        Set<Integer> outSet = new HashSet<>();
        Set<Integer> twoSet = new HashSet<>();
        
        map = new HashMap<>();
        
        
        for(int[] e : edges){
            int a = e[0];
            int b = e[1];
     
            cntSet.add(a);
            cntSet.add(b);
            inSet.add(b);
            
            if(outSet.contains(a)) twoSet.add(a);
            else outSet.add(a); 
        }
        
        int size = cntSet.size() +1; // 미리 +1 해주기
        
        for(int in : inSet) twoSet.remove(in);
        int STOP = 0;
        for(int s : twoSet) STOP = s;
        
        answer [0] = STOP; // STOP 찾기 완료
        
        for(int [] e: edges){
            int a = e[0];
            int b = e[1];
            
            if(a==STOP) continue;
            
            List<Integer> tmpList = map.getOrDefault(a, new ArrayList<>());
            tmpList.add(b);
            map.put(a,tmpList);
        } // 간선리스트 만들어주기
        
        int max = cntSet.last();
        visSet = new HashSet<>(); // 연속적이지 않으니 굳이 배열말고 set 쓰기
        visSet.add(STOP);
        
        for(int i : cntSet){ // 번호는 항상 연속적이진 않음
            saveShape = -1; // 다 못만들어진 stick 일부분 거르기
            if(visSet.contains(i)) continue;
            DFS(i, i);
            
            if(saveShape > 0) answer[saveShape] ++;
        }
        
        

        
        return answer;
    }
    
   
    Set visSet;
    int saveShape;
    
    /*
    만약 갯수따지기?
    cnt 와 set 만들어놓고 ++, 무조건 전부다 돌림 -> 이럴려면 한곳 중복방문해야됨 ㄴㄴ
    근데 끝에 도착하게 될경우에만 stick으로 -> -1

    */ 
    
    void DFS(int first, int me) {
        
        List<Integer> list = map.get(me);
        
        if(list == null) { // 끝에 도달
            saveShape = 2;
            visSet.add(me);
            return;
        }
                
        else if (list.size() > 1) saveShape = 3; // 두갈래길 등장시 8자
        else if (first == me && visSet.contains(me)) saveShape = Math.max(saveShape, 1); // 시작정점 재방문시 도넛으로 생각
        
        for(int l : list){
            if(visSet.contains(l)) continue;
            visSet.add(l); // 방문체크는 항상 다음지점 방문 직전에 미리하기
            DFS(first, l);
        }
        
    }

}