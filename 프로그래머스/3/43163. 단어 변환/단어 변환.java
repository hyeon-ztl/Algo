
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    static int wordsLength;
    static int wordLength;
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        wordLength = begin.length();
        wordsLength = words.length;

        for (int i = 0; i < wordsLength; i++) {
            if (words[i].equals(target)) {
                break;
            }
            if (i == wordsLength - 1) {
                return answer;
            }
            
        }
        boolean[] visited = new boolean[wordsLength];

        // 만약 begin 과 같은 단어가 words에 존재한다면?? visited 배열에 예외 처리 필요

        Queue<String> q = new LinkedList<>();
        int depth = 0;
        q.add(begin);

        while(!q.isEmpty()){ // BFS 시작
            int size = q.size(); // depth 체크
            for(int dep=0; dep<size; dep++){ // depth 만큼 돌리고 depth ++
                String curr =  q.poll(); // 뽑아 내기

                for(int wrd=0; wrd<wordsLength; wrd++){ // 단어 수만큼 회전
                    if(visited[wrd]) continue; // 이미 방문했으면 건너뛰어
                    if(checkIsSimilar(curr, words[wrd])){ // 유사하다고 판단되면
                        if(words[wrd].equals(target)) return depth+1; // 타겟에 도달했는지 확인
                        // 아니라면
                        q.add(words[wrd]); // 큐에 넣고
                        visited[wrd] = true; // 방문췤
                    }// end of if
                }// end of wrd
            } // end of dep
            depth ++;

        }

 
        
        return answer; // 끝까지 돌았는데 실패하면 0 반환 ~ 
    }


        static boolean checkIsSimilar (String first, String next ){
            int count = 0; // 틀린 갯수
            for(int i = 0; i<wordLength; i++){
                if(first.charAt(i) != next.charAt(i)){
                    count++; // 틀릴때마다 하나씩 추가
                    if(count>=2) return false; // 두개이상 틀리면 false 반환
                }
            }
            return true;
        }
}