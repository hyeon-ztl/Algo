import java.util.*;

class Solution {

    // Fenwick Tree (BIT) for counting points by y-index
    static class Fenwick {
        int n;
        int[] tree;

        Fenwick(int n) {
            this.n = n;
            this.tree = new int[n + 1];
        }

        void clear() {
            Arrays.fill(tree, 0);
        }

        void add(int idx, int delta) {
            for (int i = idx; i <= n; i += (i & -i)) {
                tree[i] += delta;
            }
        }

        int sum(int idx) {
            int res = 0;
            for (int i = idx; i > 0; i -= (i & -i)) {
                res += tree[i];
            }
            return res;
        }

        int rangeSum(int l, int r) { // inclusive
            if (l > r) return 0;
            return sum(r) - sum(l - 1);
        }
    }

    static class Pt {
        int x;
        int y;
        int yIdx; // compressed y index (1..m)

        Pt(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int solution(int n, int[][] data) {
        Pt[] pts = new Pt[n];
        int[] ys = new int[n];

        for (int i = 0; i < n; i++) {
            int x = data[i][0];
            int y = data[i][1];
            pts[i] = new Pt(x, y);
            ys[i] = y;
        }

        // 1) y 좌표 압축
        Arrays.sort(ys);
        int m = 0;
        int[] uniq = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0 || ys[i] != ys[i - 1]) uniq[m++] = ys[i];
        }

        // 각 점의 yIdx 매핑 (binarySearch)
        for (Pt p : pts) {
            int idx = Arrays.binarySearch(uniq, 0, m, p.y);
            p.yIdx = idx + 1; // 1-based
        }

        // 2) x 오름차순, x 같으면 y 오름차순 정렬
        Arrays.sort(pts, (a, b) -> {
            if (a.x != b.x) return Integer.compare(a.x, b.x);
            return Integer.compare(a.y, b.y);
        });

        Fenwick bit = new Fenwick(m);
        long count = 0;

        // 3) 각 i를 "왼쪽 대각점"으로 고정하고 오른쪽으로 스윕
        for (int i = 0; i < n; i++) {
            bit.clear();

            int baseX = pts[i].x;
            int baseYIdx = pts[i].yIdx;

            int pos = i + 1;

            while (pos < n) {
                int groupStart = pos;
                int curX = pts[pos].x;

                // 같은 x 그룹 끝 찾기
                int groupEnd = groupStart;
                while (groupEnd < n && pts[groupEnd].x == curX) groupEnd++;

                // 3-1) 쿼리 먼저: 내부 x 범위는 (baseX, curX) 이므로
                //       curX와 같은 점들은 아직 BIT에 넣지 않아야 함.
                if (curX != baseX) { // 같으면 width=0이라 어차피 불가
                    for (int j = groupStart; j < groupEnd; j++) {
                        int yjIdx = pts[j].yIdx;

                        // height=0이면 불가
                        if (yjIdx == baseYIdx) continue;

                        int low = Math.min(baseYIdx, yjIdx) + 1;
                        int high = Math.max(baseYIdx, yjIdx) - 1;

                        // 내부 y 구간에 점이 하나도 없으면 OK
                        int inside = bit.rangeSum(low, high);
                        if (inside == 0) count++;
                    }
                }

                // 3-2) 업데이트: 이제 이 x 그룹을 BIT에 추가 (다음 더 큰 x에 대해 "중간 점"이 되므로)
                //       단, baseX와 같은 x는 절대 내부 x가 될 수 없으니 추가하지 않음.
                if (curX != baseX) {
                    for (int j = groupStart; j < groupEnd; j++) {
                        bit.add(pts[j].yIdx, 1);
                    }
                }

                pos = groupEnd;
            }
        }

        // 문제 리턴 타입이 int라면 캐스팅 (최대 12,497,500 정도라 int 안전)
        return (int) count;
    }
}
