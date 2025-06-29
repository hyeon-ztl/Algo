class Solution {
    public int solution(int n) {
        final int MOD = 1_234_567;

        long prev2 = 0;   // F(0)
        long prev1 = 1;   // F(1)
        long cur    = 0;  // F(i)

        for (int i = 2; i <= n; i++) {
            cur   = (prev1 + prev2) % MOD;
            prev2 = prev1;
            prev1 = cur;
        }
        return (int) (n == 0 ? 0 : (n == 1 ? 1 : cur));
    }
}
