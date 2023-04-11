import java.util.ArrayList;
import java.util.List;

public class DLL {
    Node head;
    Node tail;
    int size;

    public DLL() {
        head = null;
        tail = null;
        size = 0;
    }
    public Node getHead() {
        return head;
    }

    public Node append(Album data) {
        Node newNode = new Node(data);
        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }
        size++;
        return newNode;
    }

    public Node insert(int location, Album data) {
        if (location < 0 || location > size) {
            throw new IllegalArgumentException("Invalid location: " + location);
        }
        Node newNode = new Node(data);
        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else if (location == 0) {
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        } else if (location == size) {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        } else {
            Node current = head;
            for (int i = 0; i < location - 1; i++) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.getNext().setPrev(newNode);
            current.setNext(newNode);
            newNode.setPrev(current);
        }
        size++;
        return newNode;
    }

    public Node delete(int location) {
        if (location < 0 || location >= size) {
            throw new IllegalArgumentException("Invalid location: " + location);
        }
        Node deletedNode;
        if (size == 1) {
            deletedNode = head;
            head = null;
            tail = null;
        } else if (location == 0) {
            deletedNode = head;
            head = head.getNext();
            head.setPrev(null);
        } else if (location == size - 1) {
            deletedNode = tail;
            tail = tail.getPrev();
            tail.setNext(null);
        } else {
            Node current = head;
            for (int i = 0; i < location; i++) {
                current = current.getNext();
            }
            deletedNode = current;
            current.getPrev().setNext(current.getNext());
            current.getNext().setPrev(current.getPrev());
        }
        size--;
        return deletedNode;
    }

    public int getIndex(Album data) {
        Node current = head;
        for (int i = 0; i < size; i++) {
            if (current.getData().equals(data)) {
                return i;
            }
            current = current.getNext();
        }
        return -1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node current = head;
        while (current != null) {
            sb.append(current.getData().toString());
            if (current.getNext() != null) {
                sb.append(" -> ");
            }
            current = current.getNext();
        }
        sb.append("NULL");
        return sb.toString();
    }

    public Node shuffle() {
        List<Node> nodeList = new ArrayList<>();
        Node current = head;
        while (current != null) {
            nodeList.add(current);
            current = current.getNext();
        }
        int n = nodeList.size();
        for (int i = 0; i < n - 1; i += 2) {
            Node node1 = nodeList.get(i);
            Node node2 = nodeList.get(i + 1);
            Node temp = node1.getNext();
            node1.setNext(node2.getNext());
            node2.getNext().setPrev(node1);
            node2.setNext(temp);
            temp.setPrev(node2);
        }
        return head;
    }

    public DLL partition(int data) {
        DLL newList = new DLL();
        Node current = head;
        while (current != null) {
            if (current.getData().getNumSongs() >= data) {
                newList.append(current.getData());
                if (current == head) {
                    head = head.getNext();
                    if (head != null) {
                        head.setPrev(null);
                    }
                } else if (current == tail) {
                    tail = tail.getPrev();
                    tail.setNext(null);
                } else {
                    current.getPrev().setNext(current.getNext());
                    current.getNext().setPrev(current.getPrev());
                }
                size--;
            }
            current = current.getNext();
        }
        return newList;
    }

    class Node {
        private Album data;
        private Node prev;
        private Node next;

        public Node(Album data) {
            this.data = data;
            prev = null;
            next = null;
        }

        public Album getData() {
            return data;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public DLL.Node getHead() {
            return null;
        }
    }
}

