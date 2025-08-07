import java.util.*;
import java.io.*;
class Solution {
    int[] info;
    List<Integer>[] child;
    int ans = 0;

    public int solution(int[] info, int[][] edges) {
        this.info = info;
        child = new ArrayList[info.length];
        for (int i = 0; i < info.length; i++) child[i] = new ArrayList<>();
        for (int[] e : edges) child[e[0]].add(e[1]);

        dfs(1, 1, 0);   // mask=1(루트), sheep=1, wolf=0
        return ans;
    }

    void dfs(int mask, int sheep, int wolf) {
        ans = Math.max(ans, sheep);

        // 방문한 노드들의 미방문 자식 모으기
        for (int i = 0; i < info.length; i++) {
            if ((mask & (1 << i)) == 0) continue;
            for (int nxt : child[i]) {
                if ((mask & (1 << nxt)) != 0) continue;

                int s = sheep + (info[nxt] == 0 ? 1 : 0);
                int w = wolf  + (info[nxt] == 1 ? 1 : 0);
                if (w >= s) continue;

                dfs(mask | (1 << nxt), s, w);
            }
        }
    }
}
