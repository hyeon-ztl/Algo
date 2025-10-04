import java.util.*;

class Solution {
    public int solution(int k, int n, int[][] reqs) {
        // 1) 타입별로 요청 묶기: (start, dur)
        List<int[]>[] byType = new ArrayList[k];
        for (int t = 0; t < k; t++) byType[t] = new ArrayList<>();
        for (int[] r : reqs) byType[r[2] - 1].add(new int[]{r[0], r[1]});
        for (int t = 0; t < k; t++) {
            byType[t].sort(Comparator.comparingInt(a -> a[0]));
        }

        // 2) wait[t][m] 미리 계산해 캐시 (m: 1..n)
        long[][] wait = new long[k][n + 1];
        for (int t = 0; t < k; t++) {
            for (int m = 1; m <= n; m++) {
                wait[t][m] = simulate(byType[t], m);
            }
        }

        // 3) 초기 배정: 각 타입 1명씩
        int[] assigned = new int[k];
        Arrays.fill(assigned, 1);
        int remain = n - k;

        // 4) (delta, type, nextM) 최대힙: 한 명 더 줄 때 줄어드는 대기합이 큰 순
        class Node {
            long delta; int t; int nextM;
            Node(long d, int t, int m) { this.delta = d; this.t = t; this.nextM = m; }
        }
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Long.compare(b.delta, a.delta));

        for (int t = 0; t < k; t++) {
            // m: 1 -> 2 의 마진
            if (n >= 2) {
                long d = wait[t][1] - wait[t][2];
                pq.offer(new Node(d, t, 2));
            } else {
                pq.offer(new Node(0L, t, 2)); // 사실상 사용 안 됨
            }
        }

        // 5) 남은 멘토를 한 명씩 배정
        while (remain-- > 0 && !pq.isEmpty()) {
            Node cur = pq.poll();
            assigned[cur.t] = cur.nextM;

            // 다음 마진도 계산해서 다시 넣기 (m: nextM -> nextM+1)
            if (cur.nextM + 1 <= n) {
                long d = wait[cur.t][cur.nextM] - wait[cur.t][cur.nextM + 1];
                pq.offer(new Node(d, cur.t, cur.nextM + 1));
            }
        }

        // 6) 최종 합산
        long ans = 0;
        for (int t = 0; t < k; t++) ans += wait[t][assigned[t]];
        return (int) ans;
    }

    // 유형별 시뮬: 멘토 m명일 때 총 대기합
    private long simulate(List<int[]> reqsOfType, int m) {
        if (reqsOfType.isEmpty()) return 0L;
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 멘토의 "다음 가용 시각"
        for (int i = 0; i < m; i++) pq.offer(0);

        long waitSum = 0;
        for (int[] r : reqsOfType) {
            int start = r[0], dur = r[1];
            int earliest = pq.poll();
            if (earliest <= start) {
                pq.offer(start + dur);
            } else {
                waitSum += (earliest - start);
                pq.offer(earliest + dur);
            }
        }
        return waitSum;
    }
}
