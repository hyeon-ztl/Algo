class Solution {
    public int solution(int n, int a, int b) {
        int round = 0;

        while (a != b) {
            a = (a + 1) / 2;  // 다음 라운드 번호 갱신
            b = (b + 1) / 2;
            round++;
        }

        return round;
    }
}
