import java.io.*;
import java.util.*;

class Solution {
    
    class Node {
        int dis;
        int box;
        
        Node (int dis, int box){
            this.dis = dis;
            this.box = box;
        }
    }
    
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        
        long answer = 0;
        
        Stack<Node> dStack = new Stack <> ();
        Stack<Node> pStack = new Stack <> ();
        
        for(int i=0; i<n; i++){
            if(deliveries[i] != 0) dStack.push(new Node(i+1, deliveries[i]));
            if(pickups[i] != 0) pStack.push(new Node(i+1, pickups[i]));
        }
        
        Node blank = new Node (0,0);
        Node fromD = new Node(0, 0);
        Node fromP = new Node(0, 0);
        
        while (!dStack.isEmpty() || !pStack.isEmpty()){
            if(!dStack.isEmpty()) fromD = dStack.peek();
            else fromD = blank;
            if(!pStack.isEmpty()) fromP = pStack.peek();
            else fromP = blank;

            answer += Math.max(fromD.dis, fromP.dis) * 2;
            
            int dCap = cap;
            int pCap = cap;
            
            while (dCap > 0 && !dStack.isEmpty()) {
                fromD = dStack.peek();
                if(fromD.box > dCap) {
                    fromD.box -= dCap;
                    dCap = 0;
                }
                else {
                    dCap -= fromD.box;
                    dStack.pop();
                }
            }
            
            while (pCap > 0 && !pStack.isEmpty()){
                fromP = pStack.peek();
                if(pCap >= fromP.box){
                    pCap -= fromP.box;
                    pStack.pop();
                }
                else {
                    fromP.box -= pCap;
                    pCap = 0;
                }
            }
        }        

        return answer;
    }
}