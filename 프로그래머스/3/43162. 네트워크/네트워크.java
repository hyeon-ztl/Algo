import java.util.*;

class Solution {
    static boolean [] visited;

    public int solution(int n, int[][] computers) {
        int answer = 0;
        List<Integer> [] list = new ArrayList [n];
        visited = new boolean [n];
        
        // 입력값 리스트 형식으로 바꿔주기
        for (int i=0; i<n; i++) {
            list[i] = new ArrayList<>(); // 1번원소 초기화
            for(int j=0; j<n; j++){
                if(i == j) continue; // 같은원소는 건너뛰기
                if(computers[i][j]==1) { // 연결 돼 있으면 
                    list[i].add(j); //연결돼있다고 써주기
                } 
            }
        } 
        
        // BFS와 DFS 뭐가 더 효율적일까?, 어쨌든 모두 끝까지 가서 방문체크를 하는게 목적이니 그냥 DFS쓸까 
        // 루프 발생시 대처 방법은 ? 1. BFS 일땐 이미 방문했던곳이니 방문 췤 돼있어서 괜찮을듯, DFS 도 맟나가지 
        // 일단 BFS 로 풀어보자 
        
        Queue<Integer> q = new LinkedList<>();
        
        for(int i=0; i<n; i++){
            if(visited[i]) continue;
            q.add(i); 
            while(!q.isEmpty()){
                int curr = q.poll();
                int sizeOfList = list[curr].size();
                for(int j=0; j < sizeOfList; j++){
                    int listCurr = list[curr].get(j); 
                    if(visited[listCurr]) continue; // 이미 방문한 애면 넣지말고 
                    visited[listCurr] = true; // 방문체크
                    q.add(listCurr);
                }
            } // end of while
            answer ++; // 네트워크 연결 하나 찾았다
        } // end of For
        
        
        
        return answer;
    }
    
//     static void DFS (int computer, int depth) {
//         // 기저조건
//         if(list[computer].size() <= depth) return; 

        
//         // 재귀부분
//         int next = list[computer].get(depth); // 다음으로 갈 곳 
        
//         // 이미 방문한 곳이면 건너뛰는거? 
//         if(!visited[next]){
//           visited [next] = true; // 방문체크
//           DFS (next, 0); // 다음 노드는 0부터 시작              
//         }
//         // 윗놈 다 갔다 왔다고 생각하면, 이제 다음 노드 탐색
//         int nenext = list[computer]. 

        
//     }
    

}