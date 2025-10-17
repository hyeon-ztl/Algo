class Solution {
    int answer ;
    int rowSize ;
    int colSize ;
    
    public int solution(int[][] beginning, int[][] target) {
        answer = -1;
        rowSize = target.length;
        colSize = target[0].length;
        
        int D [][] = new int [target.length][target[0].length];
        
        for(int r=0; r< rowSize; r++){
            for(int c=0; c<colSize; c++){
                D[r][c] = beginning[r][c] ^ target[r][c];
            }
        }
    
        
        for(int i=0; i <=1; i++){
            check(D, i);
        }

        
        return answer;
    }
    
    boolean check (int [][] D, int num){
        
        int [] R = new int [rowSize];
        int [] C = new int [colSize];
        
        // r[0] = num (0 혹은 1) 이라고 가정
        // C먼저 픽스해주기
        for(int c=0; c<colSize; c++){
            C[c] = D[0][c] ^ num; // r이 정해져있으니 c에서 바꿔줘야함
        }
        
        loop: for(int r=0; r<rowSize; r++){
                R[r] = D[r][0] ^ C[0]; // R 픽스해주기 (이미 C가 정해져있으니, 그거에 맞게 R은 바뀔 수 없음 )
            for(int c=1; c<colSize; c++){
                // 하나라도 안맞으면 실패
                if(D[r][c] != (C[c] ^ R[r])) {
                    return false;
                }
                
            }
        }
        
        int tmpAnswer = 0;
        
        for(int r : R) tmpAnswer += r;
        for(int c : C) tmpAnswer += c;
        
        if(answer != -1) answer = Math.min(tmpAnswer, answer);
        else answer = tmpAnswer;
        
        return true;
        
    }// end of check
}