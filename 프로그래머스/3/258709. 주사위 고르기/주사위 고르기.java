import java.util.*;

class Solution {

    public int[] solution(int[][] dice) {
        final int n   = dice.length;
        final int h   = n / 2;
        final int ALL = 1 << n;

        long bestWin  = -1;
        int  bestMask = 0;

        for (int mask = 0; mask < ALL; mask++) {
            if (Integer.bitCount(mask) != h) continue;     // A가 가져갈 주사위 h개

            Map<Integer, Long> distA = getDist(mask, dice);
            Map<Integer, Long> distB = getDist(((1 << n) - 1) ^ mask, dice);

            long wins = countWins(distA, distB);
            if (wins > bestWin) {
                bestWin  = wins;
                bestMask = mask;
            }
        }

        int[] answer = new int[h];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if ((bestMask & (1 << i)) != 0) answer[idx++] = i + 1; // 1-base
        }
        return answer;
    }

    /** 선택된 주사위들의 합 분포(합 → 경우의 수) */
    private Map<Integer, Long> getDist(int mask, int[][] dice) {
        Map<Integer, Long> dist = new HashMap<>();
        dist.put(0, 1L);

        for (int i = 0; i < dice.length; i++) {
            if ((mask & (1 << i)) == 0) continue;

            Map<Integer, Long> next = new HashMap<>();
            for (var e : dist.entrySet()) {
                int  sum = e.getKey();
                long cnt = e.getValue();
                for (int face : dice[i]) {
                    next.merge(sum + face, cnt, Long::sum);
                }
            }
            dist = next;
        }
        return dist;
    }

    /** A가 이기는 모든 경우의 수 */
    private long countWins(Map<Integer, Long> distA, Map<Integer, Long> distB) {
        int maxSumB = distB.keySet().stream().max(Integer::compare).orElse(0);
        long[] pref = new long[maxSumB + 1];

        for (var e : distB.entrySet()) pref[e.getKey()] = e.getValue();
        for (int i = 1; i < pref.length; i++) pref[i] += pref[i - 1];
        long totalB = pref[pref.length - 1];               // B의 총 경우의 수

        long wins = 0;
        for (var e : distA.entrySet()) {
            int  sumA = e.getKey();
            long cntA = e.getValue();

            long bLess;                                    // B 합 < A 합
            if (sumA <= 0) {
                bLess = 0;
            } else if (sumA - 1 >= pref.length) {          // A 합이 B의 최댓값보다 큼
                bLess = totalB;
            } else {
                bLess = pref[sumA - 1];
            }
            wins += cntA * bLess;
        }
        return wins;
    }
}
