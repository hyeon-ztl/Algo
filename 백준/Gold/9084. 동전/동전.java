import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
   
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
        
        while (T-- > 0) {
            // 동전의 가지 수
            int N = Integer.parseInt(br.readLine()); 
            int[] coins = new int[N];
            
            // 동전 금액 입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }
            
            // 목표 금액 입력
            int M = Integer.parseInt(br.readLine());
            
            // dp[i]는 금액 i를 만들 수 있는 방법의 수 저장
            int[] dp = new int[M + 1];
            dp[0] = 1; // ★ 0원을 만드는 방법은 1가지 !!!!
            
            // 각 동전에 대해 DP 테이블 갱신
            for (int coin : coins) { // 코인 가짓수만큼 포문 돌리기!, 밖에서 코인 반복을 돌려야 순서가 안생김
                for (int i = coin; i <= M; i++) {
                    dp[i] += dp[i - coin];
                }
            }
            
            System.out.println(dp[M]);
        } // end of test
        
    }
}
