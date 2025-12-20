
import java.io.*;
import java.util.*;

public class Main {

    class Node {
        int row;
        int col;
//        int val;
//
//        public int compareTo(Node o) {
//            return this.val -o.val;
//        }
    }

    static int N;
    static int M;

    static int [][] map ;
    static int [][][] DP;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader (System.in));
        StringTokenizer st = new StringTokenizer (br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int [N][M];
        DP = new int [N][M][3];
        int [][] ans = new int [N][M];

        final int DOWN = 0;
        final int RIGHT = 1;
        final int LEFT = 2;

        int bigNum = Integer.MIN_VALUE;


        for(int r=0; r<N; r++){
            st = new StringTokenizer (br.readLine());
            for(int c=0; c<M; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
//                DP[r][c] = bigNum;
            }
        } // input 받기 끝

        // 첫행부터 세팅해놓기
        ans[0][0] = map[0][0];
        for(int c=1; c<M; c++){
            ans [0][c] = ans[0][c-1] + map[0][c];
        }

        // 둘째줄부터 돌리기
        for(int r=1; r<N; r++){

            // DOWN
            for(int c=0; c<M; c++){
                DP[r][c][DOWN] = ans[r-1][c] + map[r][c];
            }

            //RIGHT
            DP[r][0][RIGHT] = DP[r][0][DOWN];
            for(int c=1; c<M; c++){
                DP[r][c][RIGHT] = Math.max(DP[r][c-1][DOWN], DP[r][c-1][RIGHT]) + map[r][c];
            }

            //LEFT
            DP[r][M-1][LEFT] = DP[r][M-1][DOWN];
            for(int c=M-2; c>=0; c--){
                DP[r][c][LEFT] = Math.max(DP[r][c+1][DOWN], DP[r][c+1][LEFT]) + map[r][c];
            }

            // 최종 최적안 선택
            for(int c=0; c<M; c++){
                ans [r][c] = Math.max (DP[r][c][DOWN] , Math.max(DP[r][c][RIGHT], DP[r][c][LEFT]));
            }

        }




        System.out.println(ans[N-1][M-1]);




    }// end of main






}
