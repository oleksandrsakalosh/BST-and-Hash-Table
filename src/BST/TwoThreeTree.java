package BST;

public class TwoThreeTree {
    class TTNode {
        String leftKey, rightKey;
        TTNode left, center, right;

        public TTNode(String leftKey, String rightKey, TTNode left, TTNode center, TTNode right){
            this.leftKey = leftKey;
            this.rightKey = rightKey;
            this.left = left;
            this.right = right;
            this.center = center;
        }

        public TTNode(String leftKey){
            this.leftKey = leftKey;
            rightKey = null;
            left = center = right = null;
        }

        public boolean isLeaf(){ return this.left == null; }

        public boolean isFull(){ return !(this.rightKey == null); }

        public TTNode add(TTNode node){
            if(this.isFull()){
                if(compareKeys(leftKey, node.leftKey) > 0){ //Add left
                    TTNode newNode = new TTNode(leftKey, null, node, this, null);

                    node.left = left;
                    leftKey = rightKey; rightKey = null;
                    left = center; center = right; right = null;

                    return newNode;
                }
                else if(compareKeys(rightKey, node.leftKey) < 0){ //Add right
                    TTNode newNode = new TTNode(rightKey, null, this, node, null);

                    node.left = right;
                    rightKey = null;
                    right = null;

                    return newNode;
                }
                else{ //Add center
                    TTNode newNode = new TTNode(rightKey, null, node.center, this, null);

                    node.center = newNode;
                    node.left = this;
                    rightKey = null;
                    right = null;

                    return node;
                }

            }
            else{
                if(compareKeys(leftKey, node.leftKey) < 0){
                    rightKey = node.leftKey;
                    center = node.left;
                    right = node.center;
                }
                else {
                    rightKey = leftKey;
                    right = center;
                    leftKey = node.leftKey;
                    center = node.center;
                }
                return this;
            }
        }

        public void print(String prefix, TTNode n, boolean isLeft) {
            if (n != null) {
                System.out.println (prefix + (isLeft ? "\\-- " : "|-- ") + n.leftKey + " | " + n.rightKey);
                print(prefix + (isLeft ? "    " : "|   "), n.right, false);
                print(prefix + (isLeft ? "    " : "|   "), n.center, false);
                print(prefix + (isLeft ? "    " : "|   "), n.left, true);
            }
        }
    }

    TTNode root;

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

    public TTNode insert(TTNode node, String key){
        if(node == null)
            return new TTNode(key);

        else if(node.isLeaf()){
            return node.add(new TTNode(key));
        }

        TTNode returnedNode;
        if(compareKeys(key, node.leftKey) < 0) {
            returnedNode = insert(node.left, key);
            if(returnedNode == node.left)
                return node;
            else
                return node.add(returnedNode);
        }
        else if(!node.isFull() || compareKeys(key, node.rightKey) < 0) {
            returnedNode = insert(node.center, key);
            if(returnedNode == node.center)
                return node;
            else
                return node.add(returnedNode);
        }
        else {
            returnedNode = insert(node.right, key);
            if(returnedNode == node.right)
                return node;
            else
                return node.add(returnedNode);
        }
    }

    public void insert(String key){
        root = insert(root, key);
    }

    public void print(){
        root.print("", root, false);
    }
}