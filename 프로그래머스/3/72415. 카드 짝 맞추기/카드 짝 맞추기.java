import java.io.*;
import java.util.*;

class Solution {
    
    // Map<Integer, Integer> friend;
    Map<Integer, List<Integer>> map;

    boolean [][] isCard;
    boolean [] isExist;
    
    
    public int solution(int[][] board, int cr, int cc) {
        answer = Integer.MAX_VALUE;
        
        map = new HashMap<>();
        // friend = new HashMap<>();
        
        isCard = new boolean [4][4];
        isExist = new boolean[7];

        
        int already = 0;
        
        for(int r = 0; r< 4; r++){
            for(int c=0; c<4; c++){
                
                int tmp = board[r][c];
                if(board[r][c] == 0) {
                    already = already | (1 << change(r, c));
                    continue;
                }
                
                isExist[tmp] = true;
                isCard[r][c] = true;

                List<Integer> s = map.getOrDefault(tmp, new ArrayList());
                s.add(change(r, c));
                map.put(tmp, s);
            }
        }
        
//         for(int key : map.keySet()){
//             List<Integer> s = map.get(key);
            
//             friend.put(s.get(1), s.get(0));
//             friend.put(s.get(0), s.get(1));
//         }
        
        fullMask = (int)Math.pow(2,16) - 1;
        dp = new int [fullMask+1][16];
        
        for(int i=0; i<fullMask+1; i++){
            for(int j=0; j<16; j++){
                dp[i][j] = answer; // 초기화
            }
        }
        
        // System.out.println("f:"+fullMask);
        // System.out.println("a:"+already);
        
        for(int i=1; i<=6; i++){
            if(!isExist[i]) continue;
            DFS(already, i, change(cr, cc), 0);
        }
        
//         for(int i=0; i< 16; i++){
//             System.out.println(dp[fullMask][i]);
//         }


        return answer;
    }
    
    int fullMask;
    int dp [][] ;
    int answer;
    
    
    
    // 모든 노드단위 x 번호단위로 
    void DFS (int mask, int node, int prev, int dis) {
        
        // 기본세팅
        // 이전노드에서 여기까지 오는 거리를 내가 계산해야함.
        List<Integer> list = map.get(node);
        int first = list.get(0);
        int second = list.get(1);
        
        int fDis =  dis + simul(prev, second, first) + 2;
        int sDis =  dis + simul(prev, first, second) + 2;
        
        mask = mask | (1 << first);
        mask = mask | (1 << second);
        
        
        // 기저조건
        if(mask == fullMask){ // 이때도 최종작업해줘야함
            answer = Math.min(answer, Math.min(fDis, sDis));   
            return;
        }
        
        
        // 재귀부분 
        // 추가적으로 isCard 랑 isExist 제어 필요


        makeVisit(node, first, second); // 방문췌크
        
        for(int i=0; i<=6; i++){
            if(!isExist[i]) continue;
            
                DFS(mask, i, first, fDis);
                DFS(mask, i, second, sDis); 
        }
        
        cancleVisit(node, first, second); // 퇴실체크
        
    }
    

    void makeVisit(int n, int first, int second){
        isExist[n] = false;
        int [] fArr = change(first);
        int [] sArr = change(second);    
        
        isCard [fArr[0]][fArr[1]] = false;
        isCard [sArr[0]][sArr[1]] = false;
        
    }
    
    void cancleVisit(int n, int first, int second){
        isExist[n] = true;
        int [] fArr = change(first);
        int [] sArr = change(second);    
        
        isCard [fArr[0]][fArr[1]] = true;
        isCard [sArr[0]][sArr[1]] = true;
    }
    

    
    
    int simul(int prev, int first, int second){
        // prev -> first -> second 순으로 가는 거리 제야함
        int [] pArr = change(prev);
        int [] fArr = change(first);
        int [] sArr = change(second);
        
        return BFS(pArr, fArr) + BFS(fArr, sArr);
        
    }
    
    int [] dr = {0, 0, 1, -1}; 
    int [] dc = {1, -1, 0, 0};
    
    int BFS (int[] start, int[] end){
        Queue <int[] > q = new LinkedList<>(); 
        
        boolean visited[][] = new boolean [4][4];
        
        visited[start[0]][start[1]] = true;
        q.add(start);
        int cnt = 0;
        
        while(!q.isEmpty()){
            int size = q.size();
            
            cnt ++; // 한칸 간다
            
            for(int i=0; i<size; i++){
                int [] curr = q.poll();

                // 그냥이동
                for(int d=0; d<4; d++){
                    int nr = curr[0] + dr[d];
                    int nc = curr[1] + dc[d];
                    
                    if(nr < 0 || nr >= 4 || nc < 0 || nc >= 4) continue;
                    if(visited[nr][nc]) continue;
                    
                    if(nr == end[0] && nc == end[1]) return cnt; // 도착
                    
                    visited[nr][nc] = true;
                    q.add(new int[]{nr, nc});
                    
                }
                
                // 컨트롤 이동
                for(int d=0; d<4; d++){
                    
                    int nr = curr[0];
                    int nc = curr[1];
                    
                    while(true) {
                        int nextRow = nr + dr[d];
                        int nextCol = nc + dc[d];

                        if(nextRow < 0 || nextRow >= 4 || nextCol < 0 || nextCol >= 4) break;
                        
                        nr = nextRow;
                        nc = nextCol;
                        
                        if(isCard[nr][nc]) break;
                        
                        } 
                    
                    if(nr < 0 || nr >= 4 || nc < 0 || nc >= 4) continue; // 혹시라도 범위벗어나면 out
                    if(visited[nr][nc]) continue;
                    
                    if(nr == end[0] && nc == end[1]) return cnt; // 도착
                    
                    visited[nr][nc] = true;
                    q.add(new int[]{nr, nc});                    
                }

            } // end of size    
        } // end of while
        
        return 0;
    }
    

    
    
    int change (int row, int col){
        return row * 4 + col;
    }
    
    int[] change (int num){
        return new int[] {num/4, num % 4};
        
    }
    
    
    
}