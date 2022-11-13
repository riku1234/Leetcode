import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class EatNOranges {

    private static class Node {
        private final int num;
        private final int minDays;

        Node(int num, int minDays) {
            this.num = num;
            this.minDays = minDays;
        }

        int getMinDays() {
            return minDays;
        }

        int getNum() {
            return num;
        }
    }

    private static final Comparator<Node> NODE_COMPARATOR = Comparator.comparingInt(Node::getMinDays);

    static int minDays(int n) {
        PriorityQueue<Node> queue = new PriorityQueue<>(NODE_COMPARATOR);
        queue.add(new Node(n, 0));
        Set<Integer> visitedNodes = new HashSet<>();

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            int num = curNode.getNum();
            int curMinDays = curNode.getMinDays();
            System.out.println("Queru elem = " + num + " min = " + curMinDays);
            if (num == 0) {
                return curMinDays;
            }

            if (visitedNodes.contains(num)) {
                continue;
            } else {
                visitedNodes.add(num);
            }

            if (num%3 == 0) {
                int remainingNum = num/3;
                if (!visitedNodes.contains(remainingNum)) {
                    queue.add(new Node(remainingNum, curMinDays + 1));
                }
            }

            if (num%2 == 0) {
                int remainingNum = num/2;
                if (!visitedNodes.contains(remainingNum)) {
                    queue.add(new Node(remainingNum, curMinDays + 1));
                }
            }

            if (num%1 == 0) {
                int remainingNum = num - 1;
                if (!visitedNodes.contains(remainingNum)) {
                    queue.add(new Node(remainingNum, curMinDays + 1));
                }
            }
        }

        return n;
    }

    public static void main(String[] args) {
        System.out.println(String.format("minDays for %d is %d", 10, minDays(10)));
    }
}
