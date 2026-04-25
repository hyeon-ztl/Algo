import java.io.*;
import java.util.*;

class Solution {
    
    class Node {
        int num;
        List<Node> list = new LinkedList<>();
        int size = 0;
        
        Node (int num) {
            this. num = num;
        }
        
        int add (Node o) {
            list. add (o);
            size ++;
            return size;
        }
        
        int getSize() {
            return size;
        }
        
        // @Override 
        // public String toString(){
        //     return num +" "+ list;
        // }
    }
    
    int answer;
    Node root;
    
    public int solution(int n, int[][] lighthouse) {
        
        answer = 0;
        int rootSize = 0;
        
        Map <Integer, Node> map = new HashMap<>();
        
        for(int [] l : lighthouse){
            Node first = map.getOrDefault(l[0], new Node(l[0]));
            Node second = map.getOrDefault(l[1], new Node(l[1]));
            
            map.put(l[0], first);
            map.put(l[1], second);
        
            if(rootSize < first.add(second)) {
                root = first;
                rootSize = first.getSize();
            }
            
             if(rootSize < second.add(first)){
                 root = second;
                 rootSize = second.getSize();
             }
            
            
        }
        
        visited = new boolean [n+1];
        // System.out.println(root.num);
        
        DFS(root);
        
        
        return answer;
        
    }
    
    final int OFF = 0;
    final int BRIGHT = 1;
    final int ON = 2;
    
    boolean [] visited ;
    
    int DFS (Node start){
        
        // 기저 조건 없음
        visited[start.num] = true;
        
        List<Node> tmpList = start.list;
        
        int son = 4; // 기본은 이외 상태
        int me = 0; // 기본은 꺼진상태 -> 자식 없으면 바로 꺼진거 반환
        
        for(Node o : tmpList){
            if(visited[o.num]) continue;
            
            int tmp = DFS(o);
            if(tmp == ON) me = BRIGHT; // 자식이 켜져있으면 난 밝혀짐
            son = Math.min(son, tmp); // 자식중 최소값으로?
        }
        
        if(son <= BRIGHT) { // 자식 꺼져있으면 내껀 켜야됨
            me = ON;
            answer ++;
        }
        
        if(start == root && me == OFF) { // 루트노드인데 내가 꺼져있으면켜주기
            me = ON;
            answer ++;
        }
        
        return me;
        
    }
}