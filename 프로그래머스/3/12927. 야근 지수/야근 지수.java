import java.io.*;
import java.util.*;

class Solution {

		// class Node implements Comparable<Node> {
		// 	int time;

		// 	Node (int time) {
		// 		this. time = time;
		// 	}

		// 	@Override
		// 	public int compareTo (Node o) {
		// 		return - this.time + o.time;
		// 	}
		// }

	
	
    public long solution(int n, int[] works) {
        long answer = 0;

				PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); 

				for(int work : works){
					pq.add(work);
				}

			


			/*
				예외사항
				q에 1개만 있거나
				이미 전부 0이된 상황
			*/

			// q에 1개만 남았을때 예외 처리
			if(pq.size() == 1){

				int tmp = pq.poll() - n;
				if(tmp < 0) tmp = 0;
				
				return tmp * tmp;
			}

			
			while(n > 0){
				int tmp = pq.poll();
				int top = pq.peek();
                
                // System.out.println(tmp + " " + top);

				if(tmp == 0) return 0; // 전부 0이 돼버리면 n이 남았어도 바로 터뜨리기

				int minus = tmp - top + 1;
				if(n >= minus) {
                    if(tmp - minus > 0){
                        n -= minus;
					    pq.add(tmp - minus);
                    }
                    else {
                        n -= tmp;
                        pq.add(0);
                    }

				}
				else { // 남은 시간 종료
					pq.add(tmp - n);
                    n = 0;

			// for(int time : pq){
			// 	System.out.print(time);
			// }
					break;
				}
				
                
			// for(int time : pq){
			// 	System.out.print(time);
			// }
			// System.out.println();

			}

			// 피로도 계산
			for(int time : pq){
				answer += time * time;

			}
				
				
        return answer;

			
			
    }
}
/*
자나깨나 범위조심 !!


야근 시작시점의 남은작업량의 제곱

1시간 1의 양
n시간동안 일할거고
남은 작업량의 제곱 -> 젤 큰 숫자를 계속 줄여주면되네
pq를 쓰는데 큰것부터 있도록 
1씩 줄여주지말고 peek한거의 -1 만큼까지 줄여주면될듯

젤큰거 뽑음
peek한애 -1 만큼 줄임
그리고 다시 넣음
비슷하네 구름공원이랑

*/