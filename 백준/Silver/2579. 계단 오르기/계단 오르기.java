
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 조건 1. 한칸 or 두칸 오르기 가능 -> 오르는 방법의 수
 * 조건 2. 연속된 3개 불가 -> 조건 
 * 조건 3. 마지막 도착계단은 밟아야함 -
 * 
 * 최종. 점수합이 젤 높은거!
 */

/*
 * 실수 한거!
 * 1. 연속된 3개의 계단을 오르면 안된다 -> 1칸오르기를 3번하면안된다 (연속된 계단 4개오르기) 로 착각함
 * 2. 시작지점은 계단으로 치지 않는다 -> 첫번째 계단은 한칸오르기를 한거니까 앞으로 한칸오르기를 더하면 안된다고 착각함
 */

public class Main{

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int H = Integer.parseInt(br.readLine());
		int[] stair = new int[H+1];
		int[] saveJump = new int[H+1];

		for (int h = 1; h <= H; h++) {
			stair[h] = Integer.parseInt(br.readLine());
		}

		int[] dp = new int[H + 1]; // 최적해를 저장할 dp
		dp[1] = stair[1];

		for (int h = 2; h <= H; h++) { // 높이까지
			for (int jump = 1; jump <= 2; jump++) { // 점프 종류
				
				// 최적해 조건
				int tmp = dp[h - jump] + stair[h]; // 지금 방법의 해
				
				if (dp[h] <= tmp) { // 만약 지금 숫자가 더 최적해면
					
					// 그러면 이번에 1칸 3번 점프한건 아닌지 체크하자
					if (jump == 1) {// 내가 이번에도 1칸 점프 뛰려고 할때
						// 이미 큐에 1이 2칸 차 있으면 안돼!
						if (saveJump[h-1] >= 1) {
							// 만약 한칸점프가 아니라 2칸점프해서 직전칸에 도달한 점수라면?
							tmp = dp[h-1 -2] + stair[h-1] + stair[h];
							saveJump[h] = 1;
						}
						else {
							saveJump[h] = 1; 	
						}
						//아니라면
					}

					else if (jump == 2) { // 두칸 점프뛰려고 한다면
						saveJump[h] = 0;
					}

					dp[h] = tmp;
				}

			} // end of j
		} // end of h

//		System.out.println(Arrays.toString(dp));
//		System.out.println(Arrays.toString(saveJump));
		System.out.println(dp[H]);

	}
}
