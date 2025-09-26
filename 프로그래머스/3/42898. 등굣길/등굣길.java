class Solution {
    public int solution(int m, int n, int[][] puddles) {
        
        boolean [][] map = new boolean [n][m];
        long dp [][] = new long [n][m];

        // 웅덩이 표시
        for(int[] p : puddles){
            map[p[1]-1][p[0]-1]= true;
        }
        
        // 초기화
        int rowDis = 1;
        for(int r=0; r<n; r++){
            if(map[r][0]) rowDis = 0;
            dp[r][0] = rowDis;
        }
        
        // 초기화
        int colDis = 1;
        for(int c=0; c<m; c++){
            if(map[0][c]) colDis = 0;
            dp[0][c] = colDis;            
        }
        
        int mod = 1_000_000_007;
        
        for(int r=1; r<n; r++){
            for(int c=1; c<m; c++){
                if(map[r][c]) continue;
                dp[r][c] = (dp[r-1][c] + dp[r][c-1])% mod;   
            }
        }
        
        return (int)dp[n-1][m-1]%mod;
    }
}