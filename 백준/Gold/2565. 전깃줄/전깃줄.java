import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine()); // 전깃줄 개수
        List<Pair> wires = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            wires.add(new Pair(a, b));
        }
        
        // A전봇대 기준으로 정렬
        Collections.sort(wires, (o1, o2) -> o1.a - o2.a);

        // B전봇대의 연결 위치를 따로 리스트에 담기
        int[] bPositions = new int[n];
        for (int i = 0; i < n; i++) {
            bPositions[i] = wires.get(i).b;
        }

        // LIS 구하기
        int lisLength = findLIS(bPositions);

        // 남겨야 할 전깃줄의 개수
        int toRemove = n - lisLength;
        System.out.println(toRemove);
    }

    // 최장 증가 부분 수열(LIS) 구하는 메서드
    private static int findLIS(int[] arr) {
        List<Integer> lis = new ArrayList<>();
        
        for (int num : arr) {
            int index = Collections.binarySearch(lis, num);
            if (index < 0) {
                index = -(index + 1); // 삽입해야 할 위치
            }
            if (index >= lis.size()) {
                lis.add(num);
            } else {
                lis.set(index, num); // 현재 숫자를 대체
            }
        }
        return lis.size();
    }

    // Pair 클래스 정의
    static class Pair {
        int a, b;
        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
