
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int [] dura = new int [2*N]; // 내구도 저장 배열

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<2*N; i++){
            dura[i] = Integer.parseInt(st.nextToken());
        }

//        System.out.println(Arrays.toString(dura));


        int answer = 0;

        boolean [] robot = new boolean[2*N]; // 로봇 존재 여부 배열 , 벨트 번호 = 인덱스
        int startIdx = 0;
        int endIdx = N-1;


        int zero = 0;

        while(zero < K){

            // 앞으로 가는거 먼저, idx 업데이트해주기, 로봇도 한칸씩 전진시켜주기? 그러면 포문 두번 도는데 .. , 포문 한번으로 끝내보자
            if(endIdx <= 0){
                startIdx --;
                endIdx = 2*N-1;
            }
            else if(startIdx <= 0){
                startIdx = 2*N-1;
                endIdx --;
            }
            else{
                startIdx --;
                endIdx --;
            }

            // 로봇 맨 뒤에 애부터 순회하면서 앞으로 보내주기, boolean 배열 하나씩 전진
            /*
            1. endIdx인 애 있으면 먼저 false로 내려주고 // 컨베이어 벨트가 돌아서 끝에 도달한경우
            2. endIdx-1 부터 뒤로 찬찬히 순회하며 한칸씩 앞으로 땡겨주기 + 내구도 계산
            3. 마지막으로 startIdx에 올려주고, 내구도 차감 후 answer ++
             */

            robot[endIdx] = false; // 1번

            int tmpN = N;
            int backIdx = endIdx;
            int frontIdx;

            while(tmpN-- > 0){ //2번

                frontIdx = backIdx;
                if(--backIdx < 0){ // 아래로 내려가다가 0보다 떨어지면 끝으로 돌아가기
                    backIdx = 2*N-1;
                }

                if(robot[backIdx] && dura[frontIdx] > 0 && !robot[frontIdx]){ // 로봇이 있으면서, 앞칸에 비어있고, 내구도가 0이 아니면
                    robot[backIdx] = false;
                    robot[frontIdx] = true;

                    if(--dura[frontIdx] == 0){ // 내구도 다 닳았으면 zero ++
                            zero++;
                    }

                }
            } // end of while

            if(dura[startIdx] > 0){ // 3번
                robot[startIdx] = true;
                if(--dura[startIdx] == 0){ // 내구도 다 닳았으면 zero ++
                    zero++;
                }
            }

            robot[endIdx] = false; // 끝에 있는애 한번 더 내려주기 (전진해서 갔을 경우)


//            System.out.println(Arrays.toString(dura));
//            System.out.println(Arrays.toString(robot));
//            System.out.println(startIdx+ " idx " + endIdx);

            answer ++;

        }

        System.out.println(answer);

    }
}


