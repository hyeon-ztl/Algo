
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        /*
        1. k개의 초밥을 연속해서 섭취 -> 가장 가짓수가 많도록
            -> 큐 혹은 list 등 돌면서 가장 많은 가짓수인 연속된 것 찾기
            -> set 사용하면 될듯

        2. 쿠폰 사용하면 그 초밥의 가짓수 추가
            -> max 갱신할때마다 항상 최대 초밥 확인?

         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int dish = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int cupon = Integer.parseInt(st.nextToken());

        Map<Integer, Integer> map = new HashMap<>();

        // 삭제해줄때 중복 조심해야됨, 중복해서 들어가 있는 상태였는데 삭제해버리면 곤란
        // 그러면 array 형태로 보관하면서 계속 모든 원소를 set에 넣는걸 순회 .. ? 너무 비효율적
        // 큐를 써? 중복 원소 관리를 어떻게 하느냐 -> map을 사용하자.

        int [] list = new int[k];
        Queue <Integer> prevQ = new LinkedList<>(); // set에서 다음 걸 넘어갈때 삭제해주기 위해 이전거를 저장해놓음


        // 일단 k개를 넣어 놓고 시작
        for(int i=0; i<k; i++){
            int prev = Integer.parseInt(br.readLine());
            prevQ.add(prev);
            list[i] = prev;
            if(map.containsKey(prev)) {
                map.put(prev, map.get(prev)+1);
            }
            else {
                map.put(prev, 1);
            }
        }
//        System.out.println(map.toString());


        int answer = map.size();
        if(!map.containsKey(cupon)){
            answer ++;
        }

        for(int i=k; i<N; i++){
            int prev = prevQ.poll();
            // 이전원소 먼저 빼주기기
            int checkDouble = map.get(prev);
            if(checkDouble >= 2){
                map.put(prev, checkDouble-1);
            }
            else {
                map.remove(prev);
            }

            // 다음원소 넣어주기
            int next = Integer.parseInt(br.readLine());
            if(map.containsKey(next)){
                map.put(next, map.get(next)+1);
            }
            else{
                map.put(next, 1);
            }

            // prev 업데이트
            prevQ.add(next);

            // answer와 비교해주기
            int tmpSize = map.size();
            if(!map.containsKey(cupon)) tmpSize++;

            answer = Math.max(answer, tmpSize);
//            System.out.println(map.toString());

        }

        // k-1번 추가 수행 필요
        for(int i=0; i<k-1; i++){
            int prev = prevQ.poll();
            // 이전원소 빼주기
            int checkDouble = map.get(prev);
            if(checkDouble>=2){
                map.put(prev, checkDouble-1);
            }
            else {
                map.remove(prev);
            }

            // 다음원소 넣어주기
            int next = list[i];
            if(map.containsKey(next)){
                map.put(next, map.get(next)+1);
            }
            else{
                map.put(next, 1);
            }

            // prev 업데이트
            prevQ.add(next);

            int tmpSize = map.size();
            // 크기 비교
            if(!map.containsKey(cupon)){
                tmpSize ++;
            }

            answer = Math.max(answer, tmpSize);
//            System.out.println(map.toString());
        }

        System.out.println(answer);







    }
}
