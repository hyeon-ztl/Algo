class Solution {
    
    int answer ; 
    
    public int solution(int[] picks, String[] minerals) {
        answer = 987654321;
        PICK = picks;
        MINERAL = minerals;
        
        int size = minerals.length; // 캐야할 광물수
        
        gokSize = Math.min(picks[0] + picks[1] + picks[2], (size / 5) + ((size % 5) == 0 ? 0 : 1)); // 곡괭이 수
        // gokSize = (size / 5) + ((size % 5) == 0 ? 0 : 1); // 곡괭이 수
        
        System.out.println(gokSize);
        
        gokArr = new int [gokSize];
        
        piro = new int [3][3]; // 곡괭이 광물 순으로
        
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++)
                piro[i][j] = 1;
        }
        
        
        piro[STONE][DIA] = 25;
        piro[IRON][DIA] = 5;
        piro[STONE][IRON] = 5;
        
        sun(0, picks[0], picks[1], picks[2]);
        
        
        return answer;
    }
    
    int gokSize; 
    int piro [][] ;
    

    int gokArr [] ;
    final int DIA = 0;
    final int IRON = 1;
    final int STONE = 2;
    
    int [] PICK ;
    String [] MINERAL;
    
    
    void sun (int depth, int dia, int iron, int stone) {
       if(depth == gokSize) {
           
           simul();
           
           return;
       }
        
        // 갯수 인자로 가지고다니지말고 배열로 따로 관리하도록바꾸는게 나을듯
        if(dia != 0) {
            gokArr[depth] = DIA;
            sun (depth +1, dia-1, iron, stone);
        } 
        
        if(iron != 0) {
            gokArr[depth] = IRON;
            sun (depth +1, dia, iron-1, stone);
        } 
        
        if(stone != 0) {
            gokArr[depth] = STONE;
            sun (depth +1, dia, iron, stone-1);
        }    
    } // end of sun

    
    void simul (){
        int tmpPiro = 0;
        int gokCnt = 0;
        int gokIdx = 0;
        
        for(String m : MINERAL){
            if(gokIdx >= gokSize) break;
            int mNum = -1;
            
            if(m.equals("diamond")) mNum = DIA;
            else if(m.equals("iron")) mNum = IRON;
            else if(m.equals("stone")) mNum = STONE;
            
            tmpPiro += piro[gokArr[gokIdx]][mNum]; 
            
            gokCnt ++;
            if(gokCnt >= 5) {
                gokCnt = 0;
                gokIdx ++;
            }
        }
        
        answer = Math.min(answer, tmpPiro);
        
    } // end of simul
    
    // Map <String, Integer>
}