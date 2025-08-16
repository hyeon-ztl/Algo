import java.io.*;
import java.util.*;

class Solution {
    
    int defTime;
    int defFee;
    int countTime;
    int countFee;
    
    public int[] solution(int[] fees, String[] records) {
    
        defTime = fees[0];
        defFee = fees[1];
        countTime = fees[2];
        countFee = fees[3];
        
        /*
        1) record를 전체 순회하면서 차량의 대수를 파악해야함 -> answer 배열 크기 선언
        2) 차량 번호를 기준으로 정렬을 해야함. -> answer 배열 index 정하기 
        3) 그리고 그 차량번호에 맞춰서 그 애들끼리 분류를 하면서 정산하기 => stack 쓰기? -> List에 stack
        4) 그 이후에 주차 정산하기, 답을 먼저 구하고 정렬을 마지막에할까? 그게더 효율적일듯
        5) 
        */
        List<Integer> idxOfStack = new ArrayList<>() ;
        List<Stack<String>> stackList = new ArrayList<>();
        List<Integer> timeList = new ArrayList<>();
        
        int sizeOfRecords = records.length;
        
        String [] tmpArr = new String[3];
        for(int i=0; i<sizeOfRecords ; i++){
            tmpArr = records[i].split(" ");
           
            // 기록 분리
            String time = tmpArr[0];
            int carNum = Integer.parseInt(tmpArr[1]);
            boolean isIn = tmpArr[2].equals("IN")? true : false; // 사실상 필요 없음
            
            //이미 넣어놓은 기록인지 탐색
            int idx = idxOfStack.indexOf(carNum); // 
            
            // 이미 넣어놓은 애라면
            if(idx >= 0){
                // 입차면 넣어주고
                if(isIn){
                    stackList.get(idx).push(time);
                }
                // 출차면 pop해주고 계산보내기
                else {
                    String pastTime = stackList.get(idx).pop();
                    // 메서드 호출
                    int calTime = calculateTime(pastTime, time);
                    
                    // 더해주기
                    timeList.set(idx, timeList.get(idx) + calTime);
                    
                }
            } // end of 이미 넣음
            
            // 처음 들어오는 애라면
            else {
                idxOfStack.add(carNum);
                
                Stack<String> tmpStack = new Stack<>();
                tmpStack.push(time);
                stackList.add(tmpStack);
                timeList.add(0);
                
            }
            
        }
        
        int sizeOfAnswer = idxOfStack.size();

        //아직 출차 안하고 남아이는 차들 빼주고, 요금정산하기
        int [] unSortAnswer = new int [sizeOfAnswer];
        
        for(int i=0; i<sizeOfAnswer; i++){
            Stack<String> tmpStack = stackList.get(i);
            
            // 출차 안했으면 빼주고
            if(!tmpStack.isEmpty()){
                String time = tmpStack.pop();
                int addTime = calculateTime(time, "23:59");
                
                timeList.set(i, timeList.get(i)+addTime);
            }
            
            // 최종 요금계산
            unSortAnswer[i] = calculateFee(timeList.get(i));
        }
        
        
        int [] answer = new int [sizeOfAnswer];
        int [] order = new int [sizeOfAnswer]; // 크기 순을 저장해놓을 배열
        
        // 순서배열을 순서대로 채워놓음
        for(int i=0; i<sizeOfAnswer ; i++){
            order[i] = i;
        }
        
        // 크기순 인덱스 만들어주기
        for(int i = 0; i < sizeOfAnswer -1; i++){
            for(int j = i+1; j < sizeOfAnswer; j++){
                int firstValue = idxOfStack.get(order[i]);
                int secondValue = idxOfStack.get(order[j]);
                
                if(firstValue > secondValue){
                    int tmpOrder = order[i];
                    order[i] = order[j];
                    order[j] = tmpOrder;
                }
            } 
        } 
                
        // order 순서대로 answer 배열에 저장해주기
        for(int i=0; i < sizeOfAnswer ; i++){
            answer[i] = unSortAnswer[order[i]];
        }
        
        System.out.println(unSortAnswer);
        
        return answer;
    }
    
    int calculateTime(String in, String out) {
        String [] inArr = in.split(":");
        String [] outArr = out.split(":");
        
        int inTime = Integer.parseInt(inArr[0])*60 + Integer.parseInt(inArr[1]);
        int outTime = Integer.parseInt(outArr[0])*60 + Integer.parseInt(outArr[1]);
        
        return outTime - inTime;
    }
    
    int calculateFee(int time){
        if(time <= defTime){
            return defFee;
        }
        
        int restTime = time - defTime; 
        int restFee;
        if (restTime % countTime == 0){
            restFee = (restTime / countTime) * countFee;
        } 
        else {
            restFee = (restTime / countTime + 1) * countFee; 
        }
        
        return defFee + restFee;
    }
    
}