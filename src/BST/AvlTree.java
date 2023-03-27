package BST;

public class AvlTree {
    public class AVLNode {
        public String key;
        int height;
        AVLNode left, right;

        public AVLNode(String key){
            this.key = key;
            height = 1;
            left = right = null;
        }

        public int getBalance(){
            return ((this.right == null) ? 0 : this.right.height) - ((this.left == null) ? 0 : this.left.height);
        }

        public String findLeftKey(){
            return (this.left == null) ? this.key : this.left.findLeftKey();
        }

        public void print() {
            print("", this, false);
        }

        public void print(String prefix, AVLNode n, boolean isRight) {
            if (n != null) {
                System.out.println (prefix + (isRight ? "|-- " : "\\-- ") + n.key + " " + n.getBalance());
                print(prefix + (isRight ? "|   " : "    "), n.right, true);
                print(prefix + (isRight ? "|   " : "    "), n.left, false);
            }
        }
    }

    AVLNode root;

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

    int findMaxHeight(AVLNode x, AVLNode y){
        return Math.max((x == null) ? 0 : x.height, (y == null) ? 0 : y.height);
    }

    AVLNode LeftLeft(AVLNode x){
        AVLNode z = x.left;
        AVLNode y = z.right;

        x.left = y;
        z.right = x;

        x.height = 1 + findMaxHeight(x.left, x.right);
        z.height = 1 + findMaxHeight(z.left, z.right);

        return z;
    }

    AVLNode LeftRight(AVLNode x){
        AVLNode z = x.left;
        AVLNode y = z.right;

        x.left = y;
        z.right = y.left;
        y.left = z;

        z.height = 1 + findMaxHeight(z.left, z.right);
        y.height = 1 + findMaxHeight(y.left, y.right);
        x.height = 1 + findMaxHeight(x.left, x.right);

        x = LeftLeft(x);

        return x;
    }

    AVLNode RightRight(AVLNode x){
        AVLNode z = x.right;
        AVLNode y = z.left;

        x.right = y;
        z.left = x;

        x.height = 1 + findMaxHeight(x.left, x.right);
        z.height = 1 + findMaxHeight(z.left, z.right);

        return z;
    }

    AVLNode RightLeft(AVLNode x){
        AVLNode z = x.right;
        AVLNode y = z.left;

        x.right = y;
        z.left = y.right;
        y.right = z;

        z.height = 1 + findMaxHeight(z.left, z.right);
        y.height = 1 + findMaxHeight(y.left, y.right);
        x.height = 1 + findMaxHeight(x.left, x.right);

        x = RightRight(x);

        return x;
    }

    AVLNode rebalance(AVLNode node){

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

    AVLNode insert(AVLNode node, String key){
        if(node == null){
            return new AVLNode(key);
        }

        int compareValue = compareKeys(key, node.key);

        if(compareValue > 0){
            node.right = insert(node.right, key);
        }
        else if(compareValue < 0){
            node.left = insert(node.left, key);
        }
        else {
            System.out.println("The key " + key + " is already exists.");
            return node;
        }

        node.height = 1 + findMaxHeight(node.left, node.right);

        return rebalance(node);
    }

    public void delete(String key){
        root = delete(root, key);
    }

    AVLNode delete(AVLNode node, String key){
        if(node != null){
            int compareValue = compareKeys(key, node.key);
            if(compareValue > 0){
                node.right = delete(node.right, key);
            }
            else if(compareValue < 0){
                node.left = delete(node.left, key);
            }
            else if(node.left == null)
                node = node.right;
            else if(node.right == null)
                node = node.left;
            else{
                node.key = node.right.findLeftKey();
                node.right = delete(node.right, node.key);
            }

            if(node != null) {
                node.height = 1 + findMaxHeight(node.left, node.right);
                node = rebalance(node);
            }
        }
        else
            System.out.println("The key " + key + " was not found.");

        return node;
    }

    public AVLNode find(String key){
        return find(root, key);
    }

    AVLNode find(AVLNode node, String key){
        if(node == null) {
            System.out.println("The key " + key + " was not found.");
            return null;
        }
        int compareValue = compareKeys(key, node.key);
        if(compareValue > 0)
            return find(node.right, key);
        if(compareValue < 0)
            return find(node.left, key);

        return node;
    }

    public void print(){
        if(root != null)
            root.print();
    }
}
