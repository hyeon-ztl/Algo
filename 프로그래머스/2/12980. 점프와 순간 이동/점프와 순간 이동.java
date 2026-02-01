import java.util.*;
import java.io.*;

public class Solution {
    public int solution(int n) {
        int ans = 0;
        
        
        while(true){
            if(n==1 || n==2) {
                break;
            }
            
            if(n % 2 == 0){
                n /= 2;
            }
            else {
                ans ++;
                n --;
            }
            
            
        }
        
        return ans +1;
    }
}

/*

1칸 앞 점프 or 거리 x2
순간이동 ok , 점프 -k

n만큼 
점프 최소화 -> 건전지 사용량 최솟값

최솟값 기준으로 설계를 해야됨


n번칸 

dp로하는게 맞네
숫자 N이 10억이하 -> long으로 써야됨? 아니지 최대가 10억일테니 

1차원 dp 면 
1 2 3 4 5 6 7 8 9 10 .. n/2까지로? 
순간이동으로 오는 경우의수는 1가지지만, 점프는 너무 많음 (순행이건 후행이건)

2차원 dp? -> 10억 * 10억 말도 안됨

*/