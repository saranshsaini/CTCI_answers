import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Chapter2 {
    static class Node {
        Node next = null;
        int data;

        public Node(int d) {
            data = d;
        }

        public Node(int d, Node n) {
            data = d;
            next = n;
        }
    }

    public static void main(String[] args) {
        Node h = new Node(1, new Node(6, new Node(4, new Node(5, new Node(8, new Node(2, new Node(3)))))));
        Node s1 = toLinkedList(new int[]{7, 1, 6});
        Node s2 = toLinkedList(new int[]{5, 9, 2});
        Node s3 = toLinkedList(new int[]{5, 9, 2, 9, 2});
        Node s4 = toLinkedList(new int[]{9, 9, 9, 8, 9, 9, 9});
        Node s5 = toLinkedList(new int[]{9, 9, 9, 9});
        System.out.println(isPal(s4));


    }

    public static Node toLinkedList(int[] nums) {
        Node ans = new Node(nums[0]);
        Node p = ans;
        for (int i = 1; i < nums.length; i++) {
            p.next = new Node(nums[i]);
            p = p.next;
        }
        return ans;

    }

    public static void printList(Node p) {
        while (p != null) {
            System.out.println(p.data);
            p = p.next;
        }
        System.out.println("done");
    }

    public static void remDups(Node head) {
        Node p = head;
        HashSet<Integer> seen = new HashSet<>();
        seen.add(p.data);
        while (p.next != null) {
            if (seen.contains(p.next.data)) {
                p.next = p.next.next;
            } else {
                seen.add(p.next.data);
                p = p.next;
            }
        }
    }

    public static int kLast(Node head, int last) {
        Node runner = head;
        Node curr = head;
        int total = 0;
        int currSpot = 0;
        while (true) {
            if (runner.next != null && runner.next.next != null) {
                runner = runner.next.next;
                curr = curr.next;
                currSpot += 1;
                total += 2;
            } else if (runner.next == null) {
                total += 1;
                break;
            } else {
                runner = runner.next;
                curr = curr.next;
                total += 2;
                currSpot += 1;
                break;
            }
        }
        if (total - last >= total / 2) {
            for (int i = 0; i < total - last - currSpot; i++) {
                curr = curr.next;
            }
            return curr.data;
        }
        curr = head;
        for (int i = 0; i < total - last; i++) {
            curr = curr.next;
        }
        return curr.data;
    }

    public static Node kLastRec(Node head, int k, int[] ind) {
        if (head == null) {
            return null;
        }
        Node p = kLastRec(head.next, k, ind);
        ind[0] += 1;
        if (ind[0] == k) {
            return head;
        }
        return p;
    }

    public static void delMil(Node head) {
        head.data = head.next.data;
        head.next = head.next.next;
    }

    public static Node partition(Node head, int part) {
        Node lessHead = null;
        Node less = null;
        Node greater = null;
        Node greaterHead = null;
        Node after;
        while (head != null) {
            after = head.next;
            head.next = null;
            if (head.data < part) {
                if (less == null) {
                    less = head;
                    lessHead = less;
                } else {
                    less.next = head;
                    less = head;
                }
            }
            if (head.data >= part) {
                if (greater == null) {
                    greater = head;
                    greaterHead = greater;
                } else {
                    greater.next = head;
                    greater = head;
                }
            }
            head = after;
        }
        less.next = greaterHead;
        return lessHead;
    }

    public static Node sumLists(Node a, Node b) {
        Node ans = new Node(0);
        Node p = ans;
        int carryOver = 0;
        while (a != null || b != null) {
            int sum = 0;
            if (a != null) {
                sum += a.data;
                a = a.next;
            }
            if (b != null) {
                sum += b.data;
                b = b.next;
            }
            sum += carryOver;
            carryOver = sum / 10;
            p.next = new Node(sum % 10);
            p = p.next;
        }
        if (carryOver > 0) {
            p.next = new Node(carryOver);
        }
        return ans.next;
    }

    public static int sumListsInt(Node a, Node b) {
        int carryOver = 0;
        int num = 0;
        int place = 1;
        int sum = 0;
        while (a != null || b != null) {
            sum = 0;
            if (a != null) {
                sum += a.data;
                a = a.next;
            }
            if (b != null) {
                sum += b.data;
                b = b.next;
            }
            sum += carryOver;
            num = (sum % 10) * place + num;
            carryOver = sum / 10;
            place *= 10;
        }
        if (carryOver > 0) {
            num = carryOver * place + num;
        }
        return num;
    }

    public static boolean isPal(Node head) {
        Stack<Integer> stack = new Stack<>();
        Node fast = head;
        Node slow = head;
        while (fast != null) {
            if (fast.next == null) {
                slow = slow.next;
                break;
            }
            fast = fast.next.next;
            stack.add(slow.data);
            slow = slow.next;
        }
        while (slow != null) {
            if (slow.data != stack.pop()) {
                return false;
            }
            slow = slow.next;
        }
        return true;
    }

    public static Node intersection(Node a, Node b) {
        Set<Node> seen = new HashSet<>();
        while (a != null || b != null) {
            if (a != null) {
                if (seen.contains(a)) {
                    return a;
                }
                seen.add(a);
                a = a.next;
            }
            if (b != null) {
                if (seen.contains(b)) {
                    return b;
                }
                seen.add(b);
                b = b.next;
            }
        }
        return null;
    }

    public static Node loopDetect(Node head) {
        Node fast = head;
        Node slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        if (slow == null || fast == null) {
            return null;
        }
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;

    }
}
