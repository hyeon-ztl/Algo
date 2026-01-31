import java.io.*;
import java.util.*;

class Solution {

	class Node implements Comparable <Node> {
		int num;
		int time;
		int start;

		Node (int num, int time, int start) {
			this. num = num;
			this. time = time;
			this. start = start;
		}

		@Override
		public int compareTo (Node o) {
			if(this. time == o.time){
				if(this. start == o.start){
					return this. num - o.num; 
				}
				return this. start - o.start;
			}	
			return this.time - o.time;
		}// end of mathod
	} // end of class

	class Job implements Comparable <Job> {
			int num;
			int time;
			int start;
	
			Job (int num, int time, int start) {
				this. num = num;
				this. time = time;
				this. start = start;
			}

			@Override
			public int compareTo(Job o) {
				return this. start - o.start;
			}
	}


	
	public int solution(int[][] jobs) {
    int answer = 0;

		PriorityQueue<Node> waitQ = new PriorityQueue<>();
		PriorityQueue<Job> jobQ = new PriorityQueue<>();

		int idx = 1;
		for(int[] job : jobs){
			jobQ.add(new Job(idx, job[1], job[0]));
			idx ++;
		}

		
		// Job firstJob = jobQ.poll();
		// waitQ.add(new Node(firstJob.num, firstJob.time, firstJob.start));

		int nowTime = 0; //  현재 시각
		while(!(waitQ.isEmpty() && jobQ.isEmpty())){
            // System.out.println("시작한다: "+nowTime);

            
			// waitQ가 비어있으면 현재시각을 업데이트해주기
			if(waitQ.isEmpty() && jobQ.peek().start > nowTime) nowTime = jobQ.peek().start;

			while(!jobQ.isEmpty() && jobQ.peek().start <= nowTime) {
				Job tmpNode = jobQ.poll();
				waitQ.add(new Node (tmpNode.num, tmpNode.time, tmpNode.start));
			}

			// waitQ에서 하나씩 뽑아서 작업시작 -> 근데 waitQ까지 비어있따면?
                Node currNode = waitQ.poll();
                nowTime += currNode.time;
                answer += nowTime - currNode.start;
            // System.out.println("num: "+currNode.num+ ", start"+currNode.start +", endTime"+ nowTime +", 소요시간: "+currNode.time);

		}

		/*
			현재시각 전꺼까지 전부 뽑아버려 v
			그리고 waitQ에 넣어버려 v

			그리고 waitQ에서 하나 뽑아
			그리고 작업 시작
			작업 종료후 현재 시각 업데이트
			소요시간 answer에 누적해주기
		*/

		return answer / jobs.length; // 평균의 정수부분 
    }
}

/*
작업 번호, 요청 시각, 소요 시간
1. 대기큐 비어 x  -> 우선순위 높은 작업 
 소요시간 -> 요청시각 -> 작업 번호 

 2.마치는 시점 = 요청시점 
 요청 들어온 작업을 대기큐 저장 -> 우선순위 높은 작업을 

 3. 끝나는 시점까지의 애들을 대기큐에 저장


-------------
jobs는 무슨순?

대기큐에서 하나씩 무조건 뽑아서 그 작업 처리하고, 
그리고 jobs에 있는애들 내 작업 끝나는 시간 까지 시작시간 도달한 애들 쫙 대기큐에 넣어주기
작업 끝날때마다 작업 시간 answer에 적재해주기


*/