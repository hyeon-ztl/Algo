
import java.io.*;
import java.util.*;

/*
  * 공주 구하기 -> bfs, 좌표
  * 벽있으면 돌아가기, 벽있는부분은 못가도록, + 방문체크
  * 용검 그람 -> 용검 먹은것 여부를 따로 좌표에 저장하도록 함 -> node class에 필드 하나 더 넣기
  * 용검 먹은 순간부터는 분기처리를 해야하나?, BFS는 방문췤을 하니까 그 순간부터는 경로를 새로 그려야 함
  * 그러면 일반 BFS, 용검까지 BFS 그리고 용검후 BFS 를 나눠서 계산?
  * 용검까지 BFS랑 일반 BFS랑 같이 해버리면 안되나? -> 해도 될듯! 용검부터 ~ 목적지까지 BFS 한번 /
  * 일반 BFS로 2개의 답 하고 맨마지막의 두답 비교? 근데 그러면 BFS 자체를 두번 돌리는게 비효율적이지 않나?
  * 하나의 큐로 아예 돌려버릴 순 없을까? 될것 같은데? -> NODE에 boolean 타입으로 저장해놓고 그거에 따라서 visited 배열을 따로 사용하는거지
  * 그리고 나서 무조건 마지막 목적지에 도착하면 최종반환하도록
  *
 */

public class Main {

    static class Node {
        int x;
        int y;
        boolean isGram;

        public Node(int x, int y, boolean isGram) {
          this.x = x;
          this.y = y;
          this.isGram = isGram;
        }
    }

    static int N;
    static int M;
    static int T;

    static int [][] map;
    static boolean [][] visited;
    static boolean [][] gramVisited;

    static final int GRAM = 2;
    static final int WALL = 1;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());


        // 공주님 위치는 N,M
        map = new int [M+1][N+1]; // 0인곳 조심
        visited = new boolean [M+1][N+1]; // 0인곳 조심
        gramVisited = new boolean [M+1][N+1]; // 0인곳 조심

        for (int y=1; y<N+1; y++){
            st = new StringTokenizer(br.readLine());
            for (int x=1; x<M+1; x++){
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = BFS();
        if(answer > 0){
            System.out.println(answer);
        }
        else{
            System.out.println("Fail");
        }

    }



    static int [] dx = {0, 0, 1, -1};
    static int [] dy = {1, -1, 0, 0};

    static int BFS () {
        int depth = 0;
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(1,1,false));
        visited[1][1] = true;

        while(!q.isEmpty()){
            int size = q.size();
            depth ++; // 출발 전 한뎁스 올려주기

            if(depth > T){
                return -1;
            }

            for(int s=0; s < size; s++){
                Node curr = q.poll();
                for (int d =0; d<4; d++){
                    int nx = curr.x + dx[d];
                    int ny = curr.y + dy[d];
                    if(!check(nx, ny, curr.isGram)) continue; // 갈 수 있는지 확인

                    // 정답인지 아닌지 먼저 확인하기
                    if(nx == M && ny == N) return depth;

                    // 그람 먹었는지 여부에 따라서 다르게 분기처리
                    if(curr.isGram){
                        // 그람을 먹은 상태면 그람쪽에 방문췤
                        gramVisited[nx][ny] = true;
                        q.add(new Node(nx, ny, true));
                    }
                    else{
                        visited[nx][ny] = true; // 방문췤 먼저
                        // 그람을 안먹은 상태면 내가 가는곳이 그람인지 먼저 체크
                        if(map[nx][ny]==GRAM) {
                            gramVisited[nx][ny] = true; // 그람을 먹었으니 그람쪽도 방문체크
                            q.add(new Node(nx, ny, true));
                        }
                        else{
                            q.add(new Node(nx,ny, false));
                        }
                    }
                } // end of dy dx
            } // end of size
        }

        return -1;
    }

    static boolean check(int x, int y, boolean isGram){

        if(x <= 0 || y <= 0 || x > M || y > N ){
            return false;
        }

        if (isGram){
            if(gramVisited[x][y]) return false;
        }
        else {
            if (visited[x][y]) return false;
            if (map[x][y] == WALL) return false; // 그람 없을땐 벽 부딫히면 false
        }

        return true;
    }
}
