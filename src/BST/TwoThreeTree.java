package BST;

public class TwoThreeTree {
    class Node{
        String leftKey, rightKey;
        Node left, center, right;

        public Node(String leftKey, String rightKey){
            this.leftKey = leftKey;
            this.rightKey = rightKey;
            left = center = right = null;
        }

        public Node(String leftKey){
            this.leftKey = leftKey;
            left = center = right = null;
        }

        public Node(){
            left = center = right = null;
        }
    }

    Node root;

    public TwoThreeTree() { root = null; }

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


}
