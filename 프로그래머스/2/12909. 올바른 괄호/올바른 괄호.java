class Solution {
    boolean solution(String s) {
        int count = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                count++;
            } else {
                count--;
            }

            // 닫는 괄호가 더 많아지면 잘못된 괄호
            if (count < 0) {
                return false;
            }
        }

        // 모든 괄호가 짝지어졌는지 확인
        return count == 0;
    }
}
