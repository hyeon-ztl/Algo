class Solution {
    public int[] solution(String s) {
        int transformCount = 0;
        int removedZeros = 0;

        while (!s.equals("1")) {
            // 0의 개수 세기
            int zeroCount = 0;
            for (char c : s.toCharArray()) {
                if (c == '0') zeroCount++;
            }

            removedZeros += zeroCount;

            // 0 제거 후 길이 구하기
            int lengthAfterRemoval = s.length() - zeroCount;

            // 이진수 변환
            s = Integer.toBinaryString(lengthAfterRemoval);

            transformCount++;
        }

        return new int[]{transformCount, removedZeros};
    }
}
