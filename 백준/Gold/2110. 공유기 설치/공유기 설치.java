import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]); // 집의 개수
        int C = Integer.parseInt(input[1]); // 공유기의 개수
        
        int[] houses = new int[N];
        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        // 좌표 정렬
        Arrays.sort(houses);

        // 이분 탐색을 위한 변수 설정
        int left = 1; // 최소 거리
        int right = houses[N - 1] - houses[0]; // 최대 거리
        int result = 0;

        // 이분 탐색
        while (left <= right) {
            int mid = (left + right) / 2; // 중간 거리
            
            if (canPlaceRouters(houses, C, mid)) {
                result = mid; // 설치 가능할 경우 최대 거리 업데이트
                left = mid + 1; // 더 큰 거리 탐색
            } else {
                right = mid - 1; // 작은 거리 탐색
            }
        }

        System.out.println(result); // 결과 출력
    }

    // 주어진 거리로 공유기를 설치할 수 있는지 확인하는 메서드
    private static boolean canPlaceRouters(int[] houses, int C, int distance) {
        int count = 1; // 첫 번째 공유기 설치
        int lastPosition = houses[0]; // 첫 번째 집에 공유기 설치

        for (int i = 1; i < houses.length; i++) {
            if (houses[i] - lastPosition >= distance) {
                count++; // 공유기 설치 가능
                lastPosition = houses[i]; // 마지막으로 설치한 위치 업데이트
            }
            if (count >= C) { // 설치한 공유기 수가 C 이상이면 true
                return true;
            }
        }
        return false; // 설치한 공유기 수가 C 미만이면 false
    }
}
