class Solution {
    int n, m;
    int[][] map;
    boolean[][] rVisited, bVisited;
    int rx, ry, bx, by, rtx, rty, btx, bty;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int answer = Integer.MAX_VALUE;

    public int solution(int[][] maze) {
        n = maze.length;
        m = maze[0].length;
        map = maze;
        rVisited = new boolean[n][m];
        bVisited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) { rx = i; ry = j; }
                else if (map[i][j] == 2) { bx = i; by = j; }
                else if (map[i][j] == 3) { rtx = i; rty = j; }
                else if (map[i][j] == 4) { btx = i; bty = j; }
            }
        }

        rVisited[rx][ry] = true;
        bVisited[bx][by] = true;
        backtrack(rx, ry, bx, by, 0);

        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    private void backtrack(int curRx, int curRy, int curBx, int curBy, int turns) {
        // 두 수레 모두 도착 완료
        boolean rDone = (curRx == rtx && curRy == rty);
        boolean bDone = (curBx == btx && curBy == bty);

        if (rDone && bDone) {
            answer = Math.min(answer, turns);
            return;
        }

        // 현재 턴이 이미 구한 최솟값보다 크면 가차없이 종료 (가지치기)
        if (turns >= answer) return;

        // 빨간 수레 이동 후보 생성
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int nRx = rDone ? curRx : curRx + dx[i];
                int nRy = rDone ? curRy : curRy + dy[i];
                int nBx = bDone ? curBx : curBx + dx[j];
                int nBy = bDone ? curBy : curBy + dy[j];

                if (isValid(curRx, curRy, nRx, nRy, curBx, curBy, nBx, nBy, rDone, bDone)) {
                    rVisited[nRx][nRy] = true;
                    bVisited[nBx][nBy] = true;
                    backtrack(nRx, nRy, nBx, nBy, turns + 1);
                    rVisited[nRx][nRy] = false;
                    bVisited[nBx][nBy] = false;
                }
            }
        }
    }

    private boolean isValid(int crx, int cry, int nrx, int nry, int cbx, int cby, int nbx, int nby, boolean rd, boolean bd) {
        // 1. 범위를 벗어나거나 벽(5)인 경우
        if (nrx < 0 || nrx >= n || nry < 0 || nry >= m || nbx < 0 || nbx >= n || nby < 0 || nby >= m) return false;
        if (map[nrx][nry] == 5 || map[nbx][nby] == 5) return false;

        // 2. 방문했던 칸 재방문 금지 (이미 도착한 경우는 예외)
        if (!rd && rVisited[nrx][nry]) return false;
        if (!bd && bVisited[nbx][nby]) return false;

        // 3. 동시에 같은 칸으로 이동 금지
        if (nrx == nbx && nry == nby) return false;

        // 4. 수레끼리 자리 바꾸기 금지
        if (nrx == cbx && nry == cby && nbx == crx && nby == cry) return false;

        return true;
    }
}