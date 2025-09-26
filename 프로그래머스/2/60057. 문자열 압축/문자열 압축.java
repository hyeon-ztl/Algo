class Solution {
    public int solution(String s) {
        int len = s.length();
        int bestSaved = 0;

        for (int l = 1; l < len; l++) { // (<= len/2 로 줄여도 됨: 성능 최적화)
            int saved = 0;
            int cnt = 0; // 현재 그룹에서 "추가로 맞춘 블록 수"(첫 블록 제외)
            String prev = s.substring(0, l);

            for (int idx = l; idx + l <= len; idx += l) {
                String now = s.substring(idx, idx + l);
                if (prev.equals(now)) {
                    // 같은 블록을 하나 더 발견: 블록 l만큼 아낌
                    saved += l;

                    // 카운트 증가 후 자릿수 증가 체크
                    cnt++;
                    if (cnt == 1 || cnt == 9 || cnt == 99 || cnt == 999) {
                        saved--; // 숫자 자릿수 증가로 1칸 손해
                    }
                } else {
                    // 다른 블록이 나오면 그룹 초기화
                    prev = now;
                    cnt = 0;
                }
            }
            bestSaved = Math.max(bestSaved, saved);
        }
        return len - bestSaved;
    }
}
