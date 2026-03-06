import java.io.*;
import java.util.*;

class Solution {
    /*
        DFS는 완탐 -> 모든경로를 찾을 때 씀
        즉 노드를 방문하는 최적의 순서를 찾을때 쓰는거임
        트리고 그래프고 그런게 중요한게 아니라 오로지 '순서'에 집중할 것
        
        지금까지 항상 해온 DFS가 일방향 그래프여서 
        한번 갔다가 그 경로를 돌아오면 다시 탐색하지 않는 방식이었음
        -> 왜냐면 항상 가면 끝까지 가게 되고 가다가 멈추고 돌아오는일이 없음
        그러므로 visited 배열을 사용하고 한번 갔떤 경로는 다시 갈일이 없음
        
        그러나 여기서는 내가 가고 있는중일때 
        한 분기내에서 갔던 경로를 다시 재방문하는 경우의 수도 생각해야함.
        쭉탐색하는게 아니라 내 상태에 따라 탐색이 달라지기 때문
        
        원래 dfs에서는 분기가 달라질때만 재방문, 왜냐면 최적경로이기 때문에
        굳이 갔던길을 또 방문하는건 비효율적이기 때문
        
        근데 여기서는 한 분기 내에서도 재방문하는 걸 따져야 하는 문제임
        왜냐면 어떻게 가느냐에 따라 갈수 있는 최대 도달 길이 달라지기 때문
    */
    
    int[] info;
    List<Integer> [] list ;
    int answer;
    
    public int solution(int[] info, int[][] edges) {
        this. info = info;
        int size = info.length;
        list = new ArrayList[size];
        
        for(int i=0; i<size; i++){
            list[i] = new ArrayList<>();
        }
        
        for(int[] edge : edges){
            list[edge[0]].add(edge[1]);
        }
        // 입력처리 끗
        
        answer = 0;
        
        // Set <Integer> set = new HashSet<>();
        // set.add(1);
        // set.remove(2);
        
        DFS(0, 1, 0, new HashSet<>(), new boolean[size]);
        
        return answer;
    }
    
    void DFS(int now, int sheep, int wolf, Set<Integer> ready, boolean [] visited) {
        // 기저조건
        if(wolf >= sheep) return;
        
        // 재귀부분
        
        //현재 위치에서 갈수 있는부분 넣어주기
        List<Integer> sonList = list[now];
        for(int o : sonList){
            if(visited[o]) continue; // 이미 방문해서 먹은곳이면 건너뛰어
            ready.add(o); // 중복 안되게 쑤셔넣기 -> 하지만 경로 미리 저장해놓기 때문에 순간이동 가능
        }
        
        // 순환하는중에 추가 삭제하면 좀 안되나?
        
        for(int o : ready){
            boolean isSheep = info[o] == 0;
            Set<Integer> tmpSet = new HashSet<>(ready);
            tmpSet.remove(o);
            visited[o] = true;
            
            if(isSheep) DFS(o, sheep+1, wolf, tmpSet, visited);
            else        DFS(o, sheep, wolf+1, tmpSet, visited);
            
            visited[o] = false;
        }
        
        answer = Math.max(answer, sheep);
        
        
    }
}