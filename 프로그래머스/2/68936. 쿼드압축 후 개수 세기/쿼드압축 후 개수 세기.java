class Solution {
    
    int [][] map;
    int [] ans;
    
    public int[] solution(int[][] arr) {
        ans = new int[2];
        map = arr;
        
        quard(0,0,arr.length);
        
        return ans;
    }
    
    void quard (int row, int col, int size) {
        // 기저조건 (최종압축)
//         if(size == 1){
//             ans[map[row][col]]++;
//             return;
//         }
        
        
        
        // 체크부분 
        int num = map [row][col];
        boolean isSuccess = true;
        
        loop: for(int r= row; r < row + size; r++){
            // System.out.println();
                for(int c= col; c< col + size; c++){
                    // System.out.print(map[r][c]+" ");
                    if(map[r][c] != num) {
                        isSuccess = false;
                        break loop;
                    }

                }
            }
        
        // 체크 실패시 재귀부분 (해당부분 체크 성공하면 압축 x)
        if(isSuccess) {
            ans[num]++;
            return ;
        }
        
        else {
            /*
            첫번째 row col 그대로
            두번째 row 그대로, col + size /2
            세번째 row + size /2 , col 그대로
            네번째 row + size /2, col + size /2
            */
            quard (row, col, size/2);
            quard (row, col + size/2, size/2);
            quard (row + size/2, col, size/2);
            quard (row + size/2, col + size/2, size/2);
        }
        
        
        
        
    }
}
