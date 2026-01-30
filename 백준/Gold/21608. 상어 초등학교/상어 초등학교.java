import java.io.*;
import java.util.*;


class Main {

	static class Seat {
		boolean isBlank; 
		int student;
		Set<Integer> set;
		int nearBlank;

		Seat (int blank) {
			student = -1;
			isBlank = true;
			set = new HashSet<>();
			nearBlank = blank;
		}

		// 내가 착석을 하면, 이제 그 곳의 blank 수나 set 같은거 세줘야됨 그래도
		void updateSeat (int student){
			this.student = student;
			isBlank = false;
		}
		
		//blank 인 애들만 업데이트해주기
		void nearSeat (int num){
			set.add(num);
			nearBlank --;
		}
		
	}

	static Seat [][] classSeat ;
	static int N;

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int []arr = new int [N*N];

		int [][] studentArr = new int [N*N+1][4];
		
		for(int i=0; i<N*N; i++){
			st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken());
			arr[i] = first;
			for(int j=1; j<5; j++){
				int tmp = Integer.parseInt(st.nextToken());
				studentArr[first][j-1] = tmp;
			}
		} // 입력 처리 끝

		classSeat = new Seat [N][N];
		for(int r=1; r<N-1; r++){
			for(int c=1; c<N-1; c++){
				classSeat[r][c] = new Seat(4);
			}
		}

		for(int r=1; r<N-1; r++){
			classSeat[r][0] = new Seat(3);
			classSeat[r][N-1] = new Seat(3);			
		}

		for(int c=1; c<N-1; c++){
			classSeat[0][c] = new Seat(3);
			classSeat[N-1][c] = new Seat(3);			
		}

		classSeat[0][0] = new Seat(2);
		classSeat[0][N-1] = new Seat(2);
		classSeat[N-1][0] = new Seat(2);
		classSeat[N-1][N-1] = new Seat(2);
		
		

		// 첫번째 애 부터 자리에 앉히기 (N은 항상 3이상)
		updateNearSeat(arr[0], 1, 1);

		for(int i=1; i<N*N ; i++){
				findSeat(arr[i], studentArr[arr[i]]);
		}		

		int answer = 0;
		
		for(int r=0; r<N; r++){
			for(int c=0; c<N; c++){

				Seat now = classSeat[r][c];
				
				int tmpLove = 0;
				for(int i=0; i<4; i++){
					if(now.set.contains(studentArr[now.student][i])) tmpLove++;
				}

				if(tmpLove == 1) answer += 1;
				else if(tmpLove ==2) answer += 10;
				else if(tmpLove == 3) answer += 100;
				else if(tmpLove == 4) answer += 1000;
		}
		}

		System.out.println(answer);
		// System.out.println("answer");

		// for(int r=0; r<N; r++){
		// 	for(int c=0; c<N; c++){
		// 		System.out.print(classSeat[r][c].student + " ");
		// 	}
		// 	System.out.println();

		// }
		
	}

	static int [] dr = {0, 0, -1, 1};
	static int [] dc = {-1, 1, 0, 0};


	static void findSeat (int who, int [] student) {

		// if(who == 7){
		// 	System.out.println("7차례다");
		// 	System.out.println(Arrays.toString(student));
		// }
		
		int row =0;
		int col =0;

		int love = -1;
		int blank = -1;

		for(int r=0; r<N; r++){
			for(int c=0; c<N; c++){
				if(!classSeat[r][c].isBlank) continue; // 이미 누가 앉았으면 건너뛰어

				// 애정도 확인
				Seat tmp = classSeat[r][c];
				int tmpLove = 0;
				for(int i=0; i<4; i++){
					if(tmp.set.contains(student[i])) tmpLove ++;
				}


				// 	if(who == 7){
				// 	System.out.print("--- 현재위치 ---");
				// 	System.out.println(r + ", " + c);
				// 	System.out.println("저장된 것: "+ love+" "+blank);
				// 	System.out.println("탐색중인 것: "+ tmpLove+" "+tmp.nearBlank);
						
				// }

				if(tmpLove > love){ // 만약 애정도가 더 높아
					row = r;
					col = c;
					love = tmpLove;
					blank = tmp.nearBlank;

				// if(who == 7){
				// 	System.out.print("더커서 바뀜 love:");
				// 	System.out.println(tmpLove);
				// 	System.out.println(row + " " + col);
				// }
					
				}
				else if(tmpLove == love && tmp.nearBlank > blank){ // 만약 애정도가 같으면서 인접 blank가 더 많아
					row = r;
					col = c;
					blank = tmp.nearBlank;
				// 	if(who == 7){
				// 	System.out.print("빈칸 많아서 바뀜 blank:");
				// 	System.out.println(blank);
				// 	System.out.println(row + " " + col);
				// }
				}
	
			}
		} // end of seat 순회

		// 확정된 애를 이제 자리에 배치해주기
		updateNearSeat(who,row, col);
		
	}

	static void updateNearSeat(int student, int row, int col) {
		
	 classSeat[row][col].updateSeat(student); // 자리 배치
		
		for(int d=0; d<4; d++){ // 주변업데이트
			int nr = row + dr[d];
			int nc = col + dc[d];

			if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			// if(!classSeat[row][col].isBlank) continue;

			classSeat[nr][nc].nearSeat(student);
		}
		
	}// end of mathod
	
}

/*
n^2 명
순서 , 좋아하는 학생 4명

상하좌우 한칸씩 떨어질때만 인접하다고 판단
1) 좋아하는 학생이 가장 많은 칸
2) 인접 칸중 비어있는 칸이 젤 많은 칸
3) 행번호가 작은칸 -> 열번호가 작은 칸

-> 첫번째 애는 항상 위치가 정해져 있네 -> 빈칸이 가장 많으면서 행번호와 열번호가 가장 작은곳으로 배치
-> 그러면 나머지 두번째 부터는 모든 빈칸 순회.. ? 
-> 빈칸을 조회할때마다 상하좌우 빈칸 갯수 이런거 조회하는 것보다 애초에 그 칸의 인접한 빈칸 갯수와 인접 학생번호를 넣어놓는게 효율적 
-> 그리고 조회할때 한번에 빵

-> 빈칸만 조회하기 어떻게? 



*/