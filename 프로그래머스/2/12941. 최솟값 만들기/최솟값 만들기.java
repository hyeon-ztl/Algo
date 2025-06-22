import java.util.Arrays;

class Solution
{
    public int solution(int []A, int []B)
    {
        Arrays.sort(A); // 오름차순
        Arrays.sort(B); // 오름차순 후, 뒤에서부터 사용 (내림차순처럼)

        int answer = 0;
        int len = A.length;
        for (int i = 0; i < len; i++) {
            answer += A[i] * B[len - 1 - i];
        }

        return answer;
    }
}
