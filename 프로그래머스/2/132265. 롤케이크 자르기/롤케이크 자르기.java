
import java.util.HashMap;
import java.util.Map;

class Solution {

    public int solution(int[] topping) {

        int answer = 0;

        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();

        for (int i = 0; i < topping.length; i++) {
            map2.put(topping[i], map2.getOrDefault(topping[i], 0) + 1);
        }

        for (int i = 0; i < topping.length - 1; i++) {

            map1.put(topping[i], map1.getOrDefault(topping[i], 0) + 1);

            int cnt = map2.getOrDefault(topping[i], 0);
            if (cnt > 1) {
                map2.put(topping[i], cnt - 1);
            } else {
                map2.remove(topping[i]);
            }

            if (map1.keySet().size() == map2.keySet().size()) {
                answer += 1;
            }
        }

        return answer;
    }
}
