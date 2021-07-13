import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class Chapter3 {
    public class theStack<T> {
        private class StackNode<T> {
            private T data;
            private StackNode<T> next;

            public StackNode(T data) {
                this.data = data;
            }
        }

        private StackNode<T> top;

        public T pop() {
            if (top == null) {
                throw new EmptyStackException();
            }
            T returnVal = top.data;
            top = top.next;
            return returnVal;
        }

        public void push(T newData) {
            StackNode<T> newNode = new StackNode<>(newData);
            newNode.next = top;
            top = newNode;
        }

        public T peek() {
            if (top == null) {
                throw new EmptyStackException();
            }
            return top.data;
        }

        public boolean isEmpty() {
            return top == null;
        }
    }

    public class theQueue<T> {
        private class QueueNode<T> {
            private T data;
            private QueueNode<T> next;

            public QueueNode(T data) {
                this.data = data;
            }

            private QueueNode<T> first;
            private QueueNode<T> last;

            public void add(T data) {
                QueueNode<T> q = new QueueNode<>(data);
                if (last == null) {
                    first = last = q;
                    return;
                }
                last.next = q;
                last = q;
            }

            public T remove() {
                if (first == null) {
                    throw new NoSuchElementException();
                }
                T d = first.data;
                first = first.next;
                last = first == null ? null : last;
                return d;
            }

            public T peek() {
                if (first == null) {
                    return null;
                }
                return first.data;
            }

            public boolean isEmpty() {
                return first == null;
            }
        }
    }

    
}
