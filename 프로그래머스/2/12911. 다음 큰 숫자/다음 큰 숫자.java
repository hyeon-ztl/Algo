class Solution {
    public int solution(int n) {
        int countOne = Integer.bitCount(n); // n의 2진수 1 개수

        while (true) {
            n++; // n보다 큰 수부터 확인
            if (Integer.bitCount(n) == countOne) {
                return n;
            }
        }
    }
}
