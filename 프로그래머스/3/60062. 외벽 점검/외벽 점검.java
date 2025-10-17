import java.util.*;

class Solution {
    public int solution(int n, int[] weak, int[] dist) {
        int w = weak.length;
        int d = dist.length;

        // 1) weak 선형화 (원형을 일자로 펴기)
        int[] ext = new int[w * 2];
        for (int i = 0; i < w; i++) {
            ext[i] = weak[i];
            ext[i + w] = weak[i] + n; // 원형 보정
        }

        // 2) dist 내림차순 (큰 거리부터 쓰는 게 유리) + 모든 순열을 탐색
        Arrays.sort(dist);
        reverse(dist); // 내림차순

        int[] best = {Integer.MAX_VALUE}; // 최소 친구 수

        // 모든 시작점 시도
        for (int start = 0; start < w; start++) {
            permuteDist(dist, 0, start, ext, w, best);
            if (best[0] == 1) break; // 더 줄일 수 없음
        }

        return best[0] == Integer.MAX_VALUE ? -1 : best[0];
    }

    // dist를 내림차순으로 한 뒤 모든 순열을 백트래킹하며 검사
    private void permuteDist(int[] dist, int idx, int start, int[] ext, int w, int[] best) {
        if (idx == dist.length) {
            checkWithOrder(dist, start, ext, w, best);
            return;
        }

        // 가지치기: 현재까지 쓴 친구수가 이미 best 이상이면 더 뒤 섞어도 의미 없음 → 하지만
        // 여기서는 실제 검사에서만 cnt로 프루닝하므로 순열 단계에선 단순히 전부 시도.
        for (int i = idx; i < dist.length; i++) {
            swap(dist, idx, i);
            checkWithOrder(dist, start, ext, w, best);
            // 추가 가지치기를 위해 조기 종료 조건을 둔다: 1이면 최적
            if (best[0] == 1) { swap(dist, idx, i); return; }

            permuteDist(dist, idx + 1, start, ext, w, best);
            if (best[0] == 1) { swap(dist, idx, i); return; }
            swap(dist, idx, i);
        }
    }

    // 고정된 시작점(start)과 현재 dist 순열로 커버 가능한지 검사
    private void checkWithOrder(int[] dist, int start, int[] ext, int w, int[] best) {
        int used = 1;                                // 현재 쓰는 친구 수
        int reach = ext[start] + dist[0];            // 첫 친구가 커버 가능한 끝 위치

        // 이미 최소값이 더 작으면 의미 없음
        if (used >= best[0]) return;

        for (int i = start; i < start + w; i++) {
            if (ext[i] > reach) {                    // 다음 취약점이 커버 범위 밖이면
                used++;                              // 다음 친구 투입
                if (used > dist.length || used >= best[0]) return; // 더 못 줄이면 중단
                reach = ext[i] + dist[used - 1];     // 새 친구가 커버할 끝 위치
            }
        }
        best[0] = Math.min(best[0], used);
    }

    private void reverse(int[] a) {
        for (int i = 0, j = a.length - 1; i < j; i++, j--) swap(a, i, j);
    }
    private void swap(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }
}
