import java.io.*;
import java.util.*;


class Solution {
    
    class Node {
        int inTime;
        int time;
        boolean isIn;
        
        Node (int inTime) {
            this. inTime = inTime;
            this. time = 0;
            this. isIn = true;
        }
        
        
        void getIn (int inTime) {
            this. inTime = inTime;
            this. isIn = true;
        }
        
        void getOut (int time){
            this. time += time - inTime;
            this. isIn = false;
        }
        
        // public int compareTo(Node o){
        //     return this. num - o.num;
        // }
        
    }
    
    
    public int[] solution(int[] fees, String[] records) {
        
        int basicTime = fees[0];
        int basicFee = fees[1];
        int plusTime = fees[2];
        int plusFee = fees[3];
        
        
        TreeMap <String, Node> map = new TreeMap<>();
        
        for(String record: records){
            String [] tmpArr = record.split(" ");

            String [] time = tmpArr[0].split(":");
            int hour = (time[0].charAt(0) - '0') * 10 + 
                         (time[0].charAt(1) - '0');
            int min = (time[1].charAt(0) - '0') * 10 + 
                        (time[1].charAt(1) - '0') + (hour * 60);
            
            // System.out.println(record);
            // System.out.println(min);
            
            
            // 작업
            if(map.containsKey(tmpArr[1])){
                Node tmpNode = map.get(tmpArr[1]);
                
                if(tmpArr[2].equals("IN")){ // 들어갈때
                    tmpNode.getIn(min);
                }
                else { // 나갈때
                    tmpNode.getOut(min);
                }
            }
            else {
                map.put(tmpArr[1], new Node (min));
            }
        }// end of for
        
                
        int[] answer = new int [map.size()];
        int idx = 0;
        
        for(Map.Entry <String, Node> en : map.entrySet()){
            Node tmpNode = en.getValue();
           
            // 아직 못나가고 있는애 처리
            if(tmpNode.isIn){
                tmpNode.getOut(23*60 + 59);
            }
            
            int tmpFee = 0;
            
            int tmpTime = tmpNode.time; // 총 머무른 시간
//             System.out.println(en.getKey());
    
//             System.out.println(tmpTime);
            
            int checkBasic = (tmpTime - basicTime);
                
            if(checkBasic <= 0){
                tmpFee = basicFee;
            }
            else {
                tmpFee = basicFee + (checkBasic / plusTime) * plusFee;
                if(checkBasic % plusTime != 0) tmpFee += plusFee;
            }

            
            answer[idx ++] = tmpFee;
        }

        

        
        // treeMap에 마지막 남은 원소 꼭 처리해주는거 잊지말기
        return answer;        
        
    }
}

/*

출차기록 x -> 23:59 출차 간주
기본시간 초과시 기본요금 + 단위시간 * 단위요금
단위시간으로 안나눠지면 올림처리


차량번호 작은 자동차부터 주차요금을 담아서 return ;
1. 일반 map 써서 입차기록 넣어놓기 -> 애초에 treeMap 쓰기?
2. pq 써서 차량번호순으로 적재해놓고 한번에 빼기 

*/