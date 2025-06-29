import java.util.Arrays;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);  // 몸무게 오름차순 정렬

        int i = 0;                 // 가장 가벼운 사람
        int j = people.length - 1; // 가장 무거운 사람
        int boats = 0;

        while (i <= j) {
            // 가장 가벼운 사람과 가장 무거운 사람을 함께 태울 수 있다면
            if (people[i] + people[j] <= limit) {
                i++;  // 가벼운 사람도 탑승
            }
            // 무거운 사람은 항상 탑승
            j--;
            boats++;
        }

        return boats;
    }
}
