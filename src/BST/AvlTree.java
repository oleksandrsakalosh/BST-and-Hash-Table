package BST;

public class AvlTree {
    class Node{
        String key;
        int height;
        Node left, right;

        public Node(String data){
            key = data;
            left = right = null;
        }
    }

    Node root;

    public AvlTree(){
        root = null;
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
}
