
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        int t=0;
        while(TC-- > 0){
            int bigNum = Integer.parseInt(br.readLine());
            int [] nums = new int [] {1, 2, 3};

            int [] dp = new int [bigNum+1];
            dp[0] = 1;


            // 순서가 있으려면 안쪽에서 코인을 돌려야함
            for(int i=1; i<= bigNum ; i++){
                for(int num : nums){
                    if(num > i) continue;
                    dp[i] += dp[i-num];
                }
            }
            System.out.println(dp[bigNum]);

        }

    }
}
