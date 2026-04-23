import java.util.*;

class Solution {
    class Node {
        int num;
        Set<Node> neighbors = new HashSet<>();
        int degree = 0;      // 현재 연결된 간선 수
        boolean isOn = false;      // 내가 등대를 켰는가?
        boolean isDeleted = false; // 트리에서 제거되었는가?

        Node(int num) {
            this.num = num;
        }

        void addNeighbor(Node n) {
            neighbors.add(n);
            degree++;
        }
    }

    public int solution(int n, int[][] lighthouse) {
        int answer = 0;
        Map<Integer, Node> map = new HashMap<>();

        // 1. 노드 생성 및 연결
        for (int i = 1; i <= n; i++) map.put(i, new Node(i));
        for (int[] l : lighthouse) {
            map.get(l[0]).addNeighbor(map.get(l[1]));
            map.get(l[1]).addNeighbor(map.get(l[0]));
        }

        // 2. 초기 리프 노드(차수가 1인 노드)를 큐에 삽입
        Queue<Node> queue = new LinkedList<>();
        for (Node node : map.values()) {
            if (node.degree == 1) queue.add(node);
        }

        // 3. 리프 노드부터 하나씩 쳐내기 (Pruning)
        while (!queue.isEmpty()) {
            Node leaf = queue.poll();
            if (leaf.isDeleted) continue;

            // 리프 노드의 유일한 부모 찾기
            Node parent = null;
            for (Node neighbor : leaf.neighbors) {
                if (!neighbor.isDeleted) {
                    parent = neighbor;
                    break;
                }
            }

            if (parent == null) continue; // 이미 모든 연결이 끊긴 경우

            // [핵심 로직] 내가 아직 불이 안 켜졌다면(부모도 안 켜졌다면), 부모를 무조건 켠다!
            if (!leaf.isOn && !parent.isOn) {
                parent.isOn = true;
                answer++;
            }

            // 리프 노드만 삭제 처리 (부모는 아직 삭제하지 않음!)
            leaf.isDeleted = true;
            
            // 부모의 차수를 줄이고, 부모가 새로운 리프가 되면 큐에 삽입
            parent.degree--;
            if (parent.degree == 1) {
                queue.add(parent);
            }
        }

        return answer;
    }
}