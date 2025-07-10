
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0){
            char [] function = br.readLine().toCharArray();

            int n = Integer.parseInt(br.readLine());
            int [] arr = new int [n];
            char [] tmpArr = br.readLine().toCharArray();
            int num = 0;
            int idx = 0;

            for (int i=1; i<tmpArr.length; i++) {
                if(tmpArr[i] == ']' || tmpArr[i] == ','){
                    if(num != 0){
                        arr[idx++] = num;
                        num = 0;
                    }
                    else {
                        break;
                    }

                }
                else if (tmpArr[i] >= '0' && tmpArr[i] <= '9'){
                    num *= 10;
                    num += tmpArr[i] - '0';
                }
            }

//            StringTokenizer st = new StringTokenizer( br.readLine(), ",");
//
//            if(n>0){
//                String tmp = st.nextToken();
//                arr[0] = Integer.parseInt(tmp.substring(1, n==1? tmp.length()-1: tmp.length()));
//            }
//            if(n>1){
//                for (int i=1; i<n-1; i++){
//                    arr[i] = Integer.parseInt(st.nextToken());
//                }
//                String tmp = st.nextToken();
//                arr[n-1] = Integer.parseInt(tmp.substring(0,tmp.length()-1));
//            }
//            System.out.println(Arrays.toString(arr));


            // 배열은 수정삭제가 불편하니까, 그냥 시작 index랑 끝 index 그리고 뒤집힘여부를 관리했다가 맨마지막에 반영해주기
            int startIdx = 0;
            int endIdx = n-1;
            boolean isHead = true; // 현재 앞쪽을 삭제해야하는 상태인지 아닌지를 확인하는 함수

            for (int i=0; i< function.length; i++) {
                char action = function[i];
                if(action == 'R') isHead = !isHead; // 뒤집어주기
                else if(action == 'D'){ // 하나 삭제하기
                    if(startIdx > endIdx) { // 만약 이제 더이상 삭제할게 없다면
                        startIdx = -1;
                        break;
                    }
                    if(isHead) startIdx += 1;
                    else endIdx -= 1;
//                    System.out.println("st:"+startIdx+" en:"+endIdx);
                }
            }

            if(startIdx == -1) {
                System.out.println("error");
            }
            else {
                StringBuilder sb = new StringBuilder();
                sb.append('[');
                if(startIdx <= endIdx){ // 하나도 안남은 상태가 아닐때만
                    if(isHead) { // 거꾸로인 상태라면 출력할 반복문 뒤집어주기
                        for(int i=startIdx; i<endIdx; i++){
                            sb.append(arr[i]+","); // int + char 하면 언제나 이상해짐 !
                        }
                        sb.append(arr[endIdx]);
                    }
                    else{
                        for(int i=endIdx; i>startIdx; i--){
                            sb.append(arr[i]+",");
                        }
                        sb.append(arr[startIdx]);
                    }
                }
                sb.append(']');
                System.out.println(sb.toString());

            }

        } // end of while

    }
}
