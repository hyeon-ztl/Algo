
import java.io.*;
import java.util.*;

public class Main {
//    static final boolean [] ZERO = {true, true, true, false, true, true, true};
//    static final boolean [] ONE = {false, false, true, false, false, true, false};
//    static final boolean [] TWO = {true, false, true, true, true, false, true};
//    static final boolean [] THREE = {true, false, true, true, false, true, true};
//    static final boolean [] FOUR = {false, true, true, true, false, true, false};
//    static final boolean [] FIVE = {true, true, false, true, false, true, true};
//    static final boolean [] SIX = {true, true, false, true, true, true, true};
//    static final boolean [] SEVEN = {true, false, true, false, false, true, false};
//    static final boolean [] EIGHT = {true, true, true, true, true, true, true};
//    static final boolean [] NINE = {true, true, true, true, false, true, true};

    static final boolean [][] NUM = {{true, true, true, false, true, true, true},
                                    {false, false, true, false, false, true, false},
                                    {true, false, true, true, true, false, true},
                                    {true, false, true, true, false, true, true},
                                    {false, true, true, true, false, true, false},
                                    {true, true, false, true, false, true, true},
                                    {true, true, false, true, true, true, true},
                                    {true, false, true, false, false, true, false},
                                    {true, true, true, true, true, true, true},
                                    {true, true, true, true, false, true, true}} ;

    static int [][] DIFF ;
    static int N;
    static int K;
    static int P;
    static String X;

    static int [] floor ;

    static int answer;

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader( System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 층수 제한
        K = Integer.parseInt(st.nextToken()); // 디스플레이 자릿수
        P = Integer.parseInt(st.nextToken()); // 반전시킬 LED
        X = st.nextToken(); // 현재 층

        floor = new int [K];
        for(int i=0; i<X.length(); i++){
            floor [K-1 -i] = X.charAt(X.length()-1 -i) - '0';
        }


        answer = -1; // 아무것도 안바꾸는 경우의 수 포함

        DIFF = new int [10][10];

        //각 숫자별로 바꿔야하는 갯수 미리 저장해놓기
        for(int i1=0; i1 < 10; i1++){
            for(int i2=0; i2<10; i2++){
                if(i1 >= i2) continue;

                int count = 0;
                for(int d=0; d<7; d++){
                    if(NUM[i1][d] != NUM[i2][d])count ++; // 둘이 다른 갯수 저장
                }
                DIFF[i1][i2] = count;
                DIFF[i2][i1] = count;
            }
        }

        DFS (0, 0, K);
        System.out.println(answer);

    }

    static void DFS (int p, int n, int k) { // 변화시킨 갯수, 지금숫자, 자릿수
        if(n > N) return; // 층수가 높아짐
        if (p > P) return; // 바꿀수 있는 갯수 초과
        if (k == 0) { // 마지막 자릿수 까지 무사히 안착
            if (n != 0) answer++;
            return;
        }

        int change = floor[K-k];

        // 재귀부분 모든 숫자를 다해줌
        for(int i=0; i<10; i++){
            DFS( p + DIFF[change][i], n + i*(int)Math.pow(10, k-1) , k-1);
        }

    }
}
