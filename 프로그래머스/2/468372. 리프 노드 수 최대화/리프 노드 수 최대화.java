import java.util.*;

class Solution {
    public int solution(int dist_limit, int split_limit) {
        long answer = 1;

        List<Long> pow2 = new ArrayList<>();
        List<Long> pow3 = new ArrayList<>();

        long v = 1;
        while (v <= split_limit) {
            pow2.add(v);
            if (v > split_limit / 2) break;
            v *= 2;
        }

        v = 1;
        while (v <= split_limit) {
            pow3.add(v);
            if (v > split_limit / 3) break;
            v *= 3;
        }

        for (int a = 0; a < pow2.size(); a++) {
            long p2 = pow2.get(a);

            for (int b = 0; b < pow3.size(); b++) {
                long p3 = pow3.get(b);

                if (p2 > split_limit / p3) break; // 2^a * 3^b > split_limit

                answer = Math.max(answer, calc(dist_limit, a, b));
            }
        }

        return (int) answer;
    }

    private long calc(int distLimit, int twoCnt, int threeCnt) {
        long used = 0;      // 사용한 분배 노드 수
        long leaves = 1;    // 현재 리프 개수
        long frontier = 1;  // 현재 depth에서 확장 가능한 리프 수

        // 2를 twoCnt번 쓰는 구간
        for (int i = 0; i < twoCnt; i++) {
            long remain = distLimit - used;
            if (remain <= 0) break;

            long x = Math.min(frontier, remain); // 이번 레벨에서 실제 확장할 개수
            used += x;
            leaves += x;       // 1개가 2개가 되므로 리프 +1 * x
            frontier = 2 * x;  // 다음 레벨 후보
        }

        // 3을 threeCnt번 쓰는 구간
        for (int i = 0; i < threeCnt; i++) {
            long remain = distLimit - used;
            if (remain <= 0) break;

            long x = Math.min(frontier, remain);
            used += x;
            leaves += 2 * x;   // 1개가 3개가 되므로 리프 +2 * x
            frontier = 3 * x;
        }

        return leaves;
    }
}