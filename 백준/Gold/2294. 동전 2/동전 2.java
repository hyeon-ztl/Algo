
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        // 가짓수가 아니라 최소가 되도록 하는것
        // 현재 - 동전의 애가 최소라면 지금애도 최소 +1

        BufferedReader br = new BufferedReader(new InputStreamReader (System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int value = Integer.parseInt(st.nextToken());
        int [] coins = new int [N];
        final int INF = 100000000;

        for(int i=0; i<N; i++){
            coins[i] = Integer.parseInt(br.readLine());
        }

        /*
         지금 문제상황
         첫 코인을 꺼내서 확인할때는 얘가 0으로 비어있음

         근데 문제는 한번도 닿지 않아서 0이 돼야하는 애도 있다는 것
         지금 상태는 맨마지막에 +1을 해주고 있기 때문에 0이 돼야할 애들이 1로 보임

         근데 그렇다고 맨마지막에 +1 에 if 문으로 얘가 0이 아닐때만 +1 하도록 해버리면,
         coin과 같은 상수인 애들이 망가져 버림, 왜냐면 걔네들을 맨밑으로 내려올때 0이 되는게 정상이니까?
         */

        int [] dp = new int [value+1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

//        for (int i= 1; i <= value; i++){
//            for(int coin : coins){
//                if(i-coin < 0 || dp[i-coin] == INF) continue;
//                dp[i] = Math.min(dp[i-coin]+1, dp[i]);
//            }
//        }
//
//        for(int j =0; j< coins.length; j++) {
//            if(j==0){
//                for (int i=coins[1]; i<= value; i++){
//                    if(i-coins[j]<0 || dp[i-coins[j]==-1) dp[i] = -1;
//                    dp[i] = dp[i-coins[j]]+1;
//                }
//            }
//            else{
//                for (int i=coins[1]; i<= value; i++){
//                    dp[i] = Math.min(dp[i-coins[j]]+1, dp[i]);
//                }
//            }
//
//        }

        for (int coin : coins){
            for(int i=coin; i<=value; i++){
                if(dp[i-coin] == INF) continue;
                dp[i] = Math.min(dp[i], dp[i-coin]+1); // 항상 coin이 몇번째건 그 몇번째 줄은 코인 가짓수가 늘어날 수록 최소로 사용할수 있는 동전 수는 줄어듬 ! 반대로 조합은 늘어나고 그래서 가능
            }
        }

        System.out.println(dp[value]==INF?-1:dp[value]);

    }
}
