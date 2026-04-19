import java.util.*;

class Solution {

    boolean[][] col; // 기둥 [x][y]
    boolean[][] beam; // 보 [x][y]
    int N;

    public int[][] solution(int n, int[][] build_frame) {
        N = n;
        col = new boolean[N + 2][N + 2];
        beam = new boolean[N + 2][N + 2];

        for (int[] cmd : build_frame) {
            int x = cmd[0];
            int y = cmd[1];
            int a = cmd[2];
            int b = cmd[3];

            if (b == 1) { // 설치
                if (a == 0) {
                    col[x][y] = true;
                    if (!isAllValid()) col[x][y] = false;
                } else {
                    beam[x][y] = true;
                    if (!isAllValid()) beam[x][y] = false;
                }
            } else { // 삭제
                if (a == 0) {
                    col[x][y] = false;
                    if (!isAllValid()) col[x][y] = true;
                } else {
                    beam[x][y] = false;
                    if (!isAllValid()) beam[x][y] = true;
                }
            }
        }

        ArrayList<int[]> list = new ArrayList<>();
        for (int x = 0; x <= N; x++) {
            for (int y = 0; y <= N; y++) {
                if (col[x][y]) list.add(new int[]{x, y, 0});
                if (beam[x][y]) list.add(new int[]{x, y, 1});
            }
        }

        list.sort((o1, o2) -> {
            if (o1[0] != o2[0]) return o1[0] - o2[0];
            if (o1[1] != o2[1]) return o1[1] - o2[1];
            return o1[2] - o2[2]; // 0(기둥) 먼저
        });

        int[][] answer = new int[list.size()][3];
        for (int i = 0; i < list.size(); i++) answer[i] = list.get(i);

        return answer;
    }

    boolean isAllValid() {
        for (int x = 0; x <= N; x++) {
            for (int y = 0; y <= N; y++) {
                if (col[x][y] && !canCol(x, y)) return false;
                if (beam[x][y] && !canBeam(x, y)) return false;
            }
        }
        return true;
    }

    boolean canCol(int x, int y) {
        // 바닥
        if (y == 0) return true;

        // 아래 기둥
        if (y > 0 && col[x][y - 1]) return true;

        // 왼쪽 보 끝 위
        if (beam[x][y]) return true;

        // 오른쪽 보 끝 위
        if (x > 0 && beam[x - 1][y]) return true;

        return false;
    }

    boolean canBeam(int x, int y) {
        // 왼쪽 끝이 기둥 위
        if (y > 0 && col[x][y - 1]) return true;

        // 오른쪽 끝이 기둥 위
        if (y > 0 && col[x + 1][y - 1]) return true;

        // 양쪽이 보로 연결
        if (x > 0 && beam[x - 1][y] && beam[x + 1][y]) return true;

        return false;
    }
}
