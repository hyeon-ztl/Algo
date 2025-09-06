class Solution {
    public long solution(int n) {
        long answer = 0;
        
        final int MOD = 1234567;
        
        int dp[] = new int [n+1];
        dp[0] = 1;
        dp[1] = 1;
        
        int [] jumps = new int [] {1,2};
        
        for(int i=2; i<n+1; i++){
            for(int jump : jumps){
                dp[i] += dp[i-jump];
            }
            dp[i] %= MOD;
        }
        
        
        return dp[n] % MOD;
    }
}