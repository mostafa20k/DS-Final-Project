package Second_Part;

public class PriorityQueue {
    private Node front, rear;


    static class Node {
        String info;
        int priority;
        Node prev, next;
    }

    public void push(String n, int p) {
        Node news = new Node();
        news.info = n;
        news.priority = p;

        if (front == null) {
            front = news;
            rear = news;
            news.next = null;
        } else {

            if (p <= (front).priority) {
                news.next = front;
                (front).prev = news.next;
                front = news;
            } else if (p > (rear).priority) {
                news.next = null;
                (rear).next = news;
                news.prev = (rear).next;
                rear = news;
            } else {
                Node start = (front).next;
                while (start.priority > p)
                    start = start.next;
                (start.prev).next = news;
                news.next = start.prev;
                news.prev = (start.prev).next;
                start.prev = news.next;
            }
        }
    }


    public String peek() {
        return front.info;
    }

    public boolean isEmpty() {
        return (front == null);
    }

    public String pop() {
        Node temp = front;
        String res = temp.info;
        (front) = (front).next;
        if (front == null)
            rear = null;

        return res;
    }

    Node search(String x) {
        Node temp = front;

        while (temp != null) {
            if (temp.info.equalsIgnoreCase(x)) {
                return temp;
            }
            temp = temp.next;
        }

        return null;
    }

    public void deleteNode(String info) {
        Node del = search(info);
        if (front == null) {
            return;
        }

        if (front.info.equals(info)) {
            front = front.next;
        } else if (del.next != null) {
            del.next.prev = del.prev;
        } else if (del.prev != null) {
            del.prev.next = del.next;
        }

    }

    public void printPQ() {
        Node temp = front;
        int pos = 1;
        while (temp.next != null) {
            System.out.println(pos + ". " + temp.info);
            pos++;
            temp = temp.next;
        }
    }
}
