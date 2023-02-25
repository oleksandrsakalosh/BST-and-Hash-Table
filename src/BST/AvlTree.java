package BST;

public class AvlTree {
    class Node{
        String key;
        int height;
        Node left, right;

        public Node(String key){
            this.key = key;
            height = 0;
            left = right = null;
        }

        int getBalance(){
            return ((this.right == null) ? 0 : this.right.height) - ((this.left == null) ? 0 : this.left.height);
        }

        String findLeftKey(){
            return (this.left == null) ? this.key : this.left.findLeftKey();
        }

        public void print() {
            print("", this, false);
        }

        public void print(String prefix, Node n, boolean isRight) {
            if (n != null) {
                System.out.println (prefix + (isRight ? "|-- " : "\\-- ") + n.key);
                print(prefix + (isRight ? "|   " : "    "), n.right, true);
                print(prefix + (isRight ? "|   " : "    "), n.left, false);
            }
        }
    }

    Node root;

    public AvlTree(){
        root = null;
    }

    int compareKeys(String key1, String key2){
        int length1 = key1.length();
        int length2 = key2.length();
        int minLength = Math.min(length1, length2);

        for(int i = 0; i < minLength; i++){
            int val1 = (int)key1.charAt(i);
            int val2 = (int)key2.charAt(i);

            if(val1 != val2)
                return val2 - val1;
        }

        if(length1 != length2)
            return length2 - length1;
        else
            return 0;
    }

    int refreshHeight(Node node){
        if(node != null){
            node.height = 1 + Math.max(refreshHeight(node.left), refreshHeight(node.right));
            return node.height;
        }
        else {
            return -1;
        }
    }

    Node LeftLeft(Node x){
        Node z = x.left;
        Node y = z.right;

        x.left = y;
        z.right = x;

        return z;
    }

    Node LeftRight(Node x){
        Node z = x.left;
        Node y = z.right;

        x.left = y;
        z.right = y.left;
        y.left = z;

        x = LeftLeft(x);

        return x;
    }

    Node RightRight(Node x){
        Node z = x.right;
        Node y = z.left;

        x.right = y;
        z.left = x;

        return z;
    }

    Node RightLeft(Node x){
        Node z = x.right;
        Node y = z.left;

        x.right = y;
        z.left = y.right;
        y.right = z;

        x = RightRight(x);

        return x;
    }

    Node rebalance(Node node){
        refreshHeight(node);

        if(node.getBalance() > 1){
            if(node.right.getBalance() > 0)
                node = RightRight(node);
            else
                node = RightLeft(node);
        }
        else if(node.getBalance() < -1){
            if(node.left.getBalance() < 0)
                node = LeftLeft(node);
            else
                node = LeftRight(node);
        }

        return node;
    }

    public void insert(String key){
        root = insert(root, key);
    }

    Node insert(Node node, String key){
        if(node == null){
            return new Node(key);
        }

        int compareValue = compareKeys(key, node.key);

        if(compareValue > 0){
            node.right = insert(node.right, key);
        }
        else{
            node.left = insert(node.left, key);
        }
        return rebalance(node);
    }

    Node delete(String key){
        return delete(root, key);
    }

    Node delete(Node node, String key){
        if(node != null){
            if(compareKeys(key, node.key) > 0){
                delete(node.right, key);
            }
            else if(compareKeys(key, node.key) < 0){
                delete(node.left, key);
            }
            else if(node.left == null)
                node = node.right;
            else if(node.right == null)
                node = node.left;
            else{
                node.key = node.right.findLeftKey();
                node.right = delete(node.right, node.key);
            }


            node = rebalance(node);
        }

        return node;
    }

    public void print(){
        root.print();
    }
}
