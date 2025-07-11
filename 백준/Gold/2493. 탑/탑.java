
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int [] answer = new int [N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        Stack<int[]> stack = new Stack<>();
        stack.push(new int[] {1, Integer.parseInt(st.nextToken())}); // 첫 원소는 넣어놓고 시작
        answer[0] = 0; // 첫 원소는 0


        for(int i=1; i<N; i++){

            int input = Integer.parseInt(st.nextToken());
            int [] fromStack = new int[0];
            int idx = 0;

            do{
                fromStack = stack.pop();
            } while (fromStack[1] < input && !stack.isEmpty());


            if(fromStack[1] >= input) {
                if(fromStack[1] > input) stack.push(fromStack); // 더 크면 다시 넣어주기
                answer[i] = fromStack[0]; // 정답 넣어주기
            }
            else {
                answer[i] = 0;
            }
            stack.push(new int[]{i + 1, input});

        }

//        StringBuilder sb = new StringBuilder();
        for(int element: answer){
            System.out.print(element+" ");
        }
//        System.out.println(sb);

    }
}
