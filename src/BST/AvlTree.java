package BST;

public class AvlTree {
    class Node{
        String key;
        int height;
        Node left, right;

        public Node(String key){
            this.key = key;
            left = right = null;
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
        if(node == null){
            node.height = -1;
            return -1;
        }
        else {
            node.height = 1 + Math.max(refreshHeight(node.left), refreshHeight(node.right));
            return node.height;
        }
    }

    int getBalance(Node node){
        return (node == null) ? 0 : (node.right.height - node.left.height);
    }

    Node rebalance(Node node){
        refreshHeight(node);

        return node;
    }

    void insert(String key){
        root = insert_new(root, key);
    }

    Node insert_new(Node node, String key){
        if(node == null){
            return new Node(key);
        }

        int compareValue = compareKeys(key, node.key);

        if(compareValue > 0){
            node.right = insert_new(node.right, key);
        }
        else{
            node.left = insert_new(node.left, key);
        }
        return rebalance(node);
    }
}
