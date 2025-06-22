import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long left = 1;
        long right = (long) Arrays.stream(times).max().getAsInt() * n;
        long answer = right;

        while (left <= right) {
            long mid = (left + right) / 2;

            long people = 0;
            for (int time : times) {
                people += mid / time;
            }

            if (people >= n) {
                answer = mid;
                right = mid - 1; // 더 적은 시간으로도 가능할 수 있음
            } else {
                left = mid + 1; // 더 많은 시간이 필요
            }
        }

        return answer;
    }
}
