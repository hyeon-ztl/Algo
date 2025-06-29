import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        Map<String, Integer> wantMap = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            wantMap.put(want[i], number[i]);
        }

        int answer = 0;

        for (int i = 0; i <= discount.length - 10; i++) {
            Map<String, Integer> windowMap = new HashMap<>();

            // 현재 10일 구간에 대해 제품 개수 카운트
            for (int j = i; j < i + 10; j++) {
                windowMap.put(discount[j], windowMap.getOrDefault(discount[j], 0) + 1);
            }

            if (matches(wantMap, windowMap)) {
                answer++;
            }
        }

        return answer;
    }

    private boolean matches(Map<String, Integer> wantMap, Map<String, Integer> windowMap) {
        for (String item : wantMap.keySet()) {
            if (windowMap.getOrDefault(item, 0) < wantMap.get(item)) {
                return false;
            }
        }
        return true;
    }
}
