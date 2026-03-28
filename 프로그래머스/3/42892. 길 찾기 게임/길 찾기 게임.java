import java.io.*;
import java.util.*;

class Solution {
    
    class Node {
        int x;
        int y;
        int idx;
        
        Node parents;
        Node left;
        Node right;
        
        Node (int x, int y, int idx){
            this. x = x;
            this. y = y;
            this. idx = idx;
        }
    }
    
    // int [][] answer;
    TreeMap<Integer, Set<int[]>> map;
        
    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = {};
        
        map = new TreeMap<>(Collections.reverseOrder());
        int idx = 1;
        
        for(int[] n : nodeinfo){
            Set<int[]> tmpSet = map.getOrDefault(n[1], new HashSet<>());    
            tmpSet.add(new int[]{n[0], idx++});
            map.put(n[1], tmpSet);
        } // y기준 x기준 정렬해놓기
        

        Node head = null;
        int headX = 0;

        for(int key : map.keySet()){
            headX = key;
            
            for(int[] s : map.get(key)){
                head = new Node(s[0], key, s[1]);
                break;
            }
            break;
        }
        
        map.remove(headX); 
                
        // tree 만들기
        for(int y : map.keySet()){            
            for(int[] x : map.get(y)){
                makeTree(head, new Node(x[0], y, x[1]));
            }
        }
        
        // 전위 후위 순회 하면서 정답 만들기
        firstList = new ArrayList<>();
        secondList = new ArrayList<>();
        
        goFirst(head);
        goSecond(head);
        
        
        int[] first  = firstList.stream().mapToInt(Integer::intValue).toArray();
        int[] second = secondList.stream().mapToInt(Integer::intValue).toArray();

        return new int[][] { first, second };
        
    }
    
    List<Integer> firstList ;
    List<Integer> secondList ;
    
    void goFirst (Node n){
        firstList.add(n.idx);
        if(n.left!=null) goFirst(n.left);
        if(n.right!=null) goFirst(n.right);
    }
    
    void goSecond (Node n){
        
        if(n.left!=null) goSecond(n.left);
        if(n.right!=null) goSecond(n.right);
        secondList.add(n.idx);
    }
    
    
    void makeTree(Node parent, Node child){
        
        if(child.x < parent.x){ // left
            
            if(parent.left == null) { // 비어있음
                parent.left = child;
            }
            else { // 안비어있으면 한뎁스 더 내려가서 거기서 비교
                makeTree(parent.left, child);
            }
            
        }
        else { // right
            if(parent.right == null){
                parent.right = child;
            }
            else {
                makeTree(parent.right, child);
            }
        }  
        
    }// end of mathod
    
    
}