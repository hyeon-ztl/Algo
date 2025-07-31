import java.util.*;
import java.io.*;
        /*
        최단경로인데 장애물이 없기에 bfs 아님
        그냥 바로 최단경로 찾기 가능
        순차적으로 한대씩 움직이도록? 
        그러면 map에 index를 숫자로 남겨놓고, 해당칸에 index가 남겨지는걸 확인? 
        
        항상 최단경로로 이동, r 좌표를 먼저 그리고 c를 이동 -> 항상 ㄴ자 모양으로 이동함
        1. 모든 로봇을 동시에 하나씩 출발시킬것이냐, 그렇게해서 충돌확인
        2. 로봇을 따로따로 map에 기억시켜놓고 같은 t일때를 확인해서 충돌을 ++ 해줄것이냐
        3. 아니면 각 초에 무언가를 남겨놓을까? 초마다 해당 로봇들이 어떤 index에 존재하는지?
        -> 몇초까지 있을줄 알고? 
        2번방법으로 가자 
        2번 3번 방법을 섞을까? 
        */

class Solution {
    
    static List<Integer> [][] map;
    static int answer;
    static int [][] pointStatic;
    
    public int solution(int[][] points, int[][] routes) {
        int pointSize = points.length; // 포인트의 index는 0부터 ! 항상 +1 해주기
        int robotSize = routes.length;
        int routeSize = routes[0].length;
        
        map = new ArrayList [101][101];
        answer = 0;

        
        for(int robot = 0; robot < robotSize; robot ++){
            int time = 0; // 각 로봇의 시간
            
            // 0번 index 충돌도 처리 해주기
            int [] zeroIndex = points[routes[robot][0]-1];
            if(map[zeroIndex[0]][zeroIndex[1]]==null){
                map[zeroIndex[0]][zeroIndex[1]] = new ArrayList<>();
            }
            else{
                if( map[zeroIndex[0]][zeroIndex[1]].contains(0)){
                    if(map[zeroIndex[0]][zeroIndex[1]].indexOf(0) ==  map[zeroIndex[0]][zeroIndex[1]].lastIndexOf(0)){
                        answer ++;
                    }
                }
            }
             map[zeroIndex[0]][zeroIndex[1]].add(0);
            
            for(int route=0; route< routeSize-1; route++){
                int [] start = points[routes[robot][route]-1]; // 출발점
                int [] end = points[routes[robot][route+1]-1]; // 최종 도착지 
                time = go(start, end, time); // 다음 시간을 업데이트
            }
        }
        
        
        return answer;
    }
    
    /*
    0초의 위험도 잡아줘야함 -> 메서드 하나로 처리? 
    불가 시작지점과 끝지점을 동시처리해주면 메서드 반복호출시마다 중복 계산하게 됨 -> 중복충돌 처리 위험 
    */
   
    static int go (int [] start, int [] end, int time){
        int timeStamp = time;
        
        int indexRow = -1; // 아래로 
        int indexCol = -1; // 왼쪽으로
        
        if(start[0] < end[0]) indexRow = 1; // 위로
        if(start[1] < end[1]) indexCol = 1; // 오른쪽으로 
        
        int currRow = start[0];
        int currCol = start[1];
        
        // Row 이동 시작 
        while(currRow != end[0]) {
            currRow += indexRow; // 좌표옮겨주고
            timeStamp ++; // 시간 올려주고 
            
            if(map[currRow][currCol] == null){ // 해당 인덱스 map에 초기화 해주고
                map[currRow][currCol] = new ArrayList<>();
            }
            else{ // 이미 다른요소가 들어가 있는 상태라면, 충돌 체크
                if(map[currRow][currCol].contains(timeStamp)){
                    if(map[currRow][currCol].indexOf(timeStamp) == map[currRow][currCol].lastIndexOf(timeStamp)){ 
                        // 하나만 있다면
                        answer ++; // 충돌 발생 
                    }   
                }
            }   
            map[currRow][currCol].add(timeStamp); // map에 timeStamp 넣어주기
        }// end of while
        
        // Col 이동 시작
        while(currCol != end[1]){
            currCol += indexCol;
            timeStamp ++;
            
            if(map[currRow][currCol]==null){ 
                map[currRow][currCol] = new ArrayList<>(); // 초기화
            }
            else{
                if(map[currRow][currCol].contains(timeStamp)){
                    if(map[currRow][currCol].indexOf(timeStamp)==map[currRow][currCol].lastIndexOf(timeStamp)){
                        answer ++; // 충돌발생
                    }
                }
            }
            map[currRow][currCol].add(timeStamp);
        }// end of while
        
        return timeStamp;
    }// end of go mathod
}