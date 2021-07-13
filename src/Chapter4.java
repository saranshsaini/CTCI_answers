import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

public class Chapter4 {
    public static class Node {
        public int data;
        public Node[] children;
        public Node left;
        public Node right;
        public boolean visited = false;

        public Node(int d, Node[] kids) {
            children = kids;
            data = d;
        }

        public Node(int d, Node l, Node r) {
            data = d;
            children = null;
            left = l;
            right = r;
        }

        public Node(int d) {
            data = d;
        }

        public void mark() {
            visited = true;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Node)) {
                return false;
            }
            Node c = (Node) o;
            return this.data == c.data;
        }

        @Override
        public int hashCode() {
            return data;
        }
    }


    public static void preorder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.data);
        preorder(node.left);
        preorder(node.right);
    }

    public static void postorder(Node node) {
        if (node == null) {
            return;
        }
        postorder(node.left);
        postorder(node.right);
        System.out.println(node.data);
    }

    public static void inorder(Node node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        System.out.println(node.data);
        inorder(node.right);
    }

    public static void dfs(Node node) {
        if (node == null) return;
        node.mark();
        System.out.println(node.data);
        for (Node n : node.children) {
            if (!n.visited) {
                dfs(n);
            }
        }
    }

    public static void bfs(Node node) {
        Queue<Node> pq = new LinkedList<>();
        pq.add(node);
        node.mark();
        while (!pq.isEmpty()) {
            Node curr = pq.remove();
            System.out.println(curr.data);
            curr.mark();
            for (Node n : node.children) {
                if (!n.visited) {
                    pq.add(n);
                }
            }
        }
    }

    public static Node makeMinBinTree(int[] arr) {
        return binMakeHelp(arr, 0, arr.length - 1);
    }

    public static Node binMakeHelp(int[] arr, int start, int end) {
        if (end == start) {
            return new Node(arr[end]);
        }
        if (end - start == 1) {
            Node n = new Node(arr[end]);
            n.left = new Node(arr[start]);
            return n;
        }
        int diff = end - start;
        int middle = start + (diff / 2);
        Node node = new Node(arr[middle]);
        node.left = binMakeHelp(arr, start, middle - 1);
        node.right = binMakeHelp(arr, middle + 1, end);
        return node;
    }

    public static void levelOrderPrint(Node head) {
        Queue<Node> q = new LinkedList<>();
        q.add(head);
        while (!q.isEmpty()) {
            int len = q.size();
            for (int i = 0; i < len; i++) {
                Node n = q.remove();
                System.out.println(n.data);
                if (n.left != null) {
                    q.add(n.left);
                }
                if (n.right != null) {
                    q.add(n.right);
                }

            }
        }
    }

    public static boolean checkBalanced(Node head) {
        return checkBalancedHelper(head, 0) != -1;

    }

    private static int checkBalancedHelper(Node head, int height) {
        if (head == null) {
            return height;
        }
        if (height == -1) {
            return -1;
        }
        int lheight = checkBalancedHelper(head.left, height + 1);
        int rheight = checkBalancedHelper(head.right, height + 1);
        if (Math.abs(lheight - rheight) <= 1) {
            return Math.max(lheight, rheight);
        } else {
            return -1;
        }
    }

    public static boolean isBST(Node head) {
        return isBSTHelper(head, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean isBSTHelper(Node head, long max, long min) {
        if (head == null) {
            return true;
        }
        if (head.data >= max || head.data <= min) {
            return false;
        }
        boolean l = isBSTHelper(head.left, min, head.data);
        boolean r = isBSTHelper(head.right, head.data, max);
        return l && r;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{3, 1, 5, 0, 2, 4, 6};
        Node head = makeMinBinTree(arr);


    }

}
