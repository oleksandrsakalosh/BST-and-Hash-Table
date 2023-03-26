package BST;

public class TwoThreeTree {
    class TTNode {
        String leftKey, rightKey;
        TTNode left, center, right;
        boolean promoting;

        public TTNode(String leftKey, String rightKey, TTNode left, TTNode center, TTNode right){
            this.leftKey = leftKey;
            this.rightKey = rightKey;
            this.left = left;
            this.right = right;
            this.center = center;
            promoting = false;
        }

        public TTNode(String leftKey){
            this.leftKey = leftKey;
            rightKey = null;
            left = center = right = null;
            promoting = false;
        }

        public boolean isLeaf(){ return this.left == null; }

        public boolean isFull(){ return !(this.rightKey == null); }

        public boolean has(String key){ return this.leftKey.equals(key) || (this.isFull() && this.rightKey.equals(key)); }

        public String findLeftKey(){
            return (this.left == null) ? this.leftKey : this.left.findLeftKey();
        }

        public TTNode add(TTNode node){
            if(this.isFull()){
                promoting = true;
                if(compareKeys(leftKey, node.leftKey) > 0){ //Add left
                    left = node;
                    center = new TTNode(rightKey);
                    rightKey = null;
                }
                else if(compareKeys(rightKey, node.leftKey) < 0){ //Add right
                    center = node;
                    left = new TTNode(leftKey);
                    leftKey = rightKey;
                    rightKey = null;
                }
                else{ //Add center
                    left = new TTNode(leftKey);
                    center = new TTNode(rightKey);
                    leftKey = node.leftKey;
                    rightKey = null;
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
            }
            return this;
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
    boolean successDeletion;

    public TwoThreeTree() { root = null;  successDeletion = true; }

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

        if(node.isLeaf())
            if(node != root)
                return node.add(new TTNode(key));
            else{
                node.add(new TTNode(key));
                node.promoting = false;
                return node;
            }


        TTNode returnedNode;
        if(compareKeys(key, node.leftKey) < 0) {
            returnedNode = insert(node.left, key);
            if(returnedNode.promoting){
                returnedNode.promoting = false;
                if(node.isFull()){
                    if(node != root)
                        node.promoting = true;
                    node.left = returnedNode;
                    node.center = new TTNode(node.rightKey, null, node.center, node.right, null);
                    node.right = null;
                    node.rightKey = null;
                }
                else{
                    node.rightKey = node.leftKey;
                    node.right = node.center;
                    node.leftKey = returnedNode.leftKey;
                    node.left = returnedNode.left;
                    node.center = returnedNode.center;
                }
            }
        }
        else if(!node.isFull() || compareKeys(key, node.rightKey) < 0) {
            returnedNode = insert(node.center, key);
            if(returnedNode.promoting){
                returnedNode.promoting = false;
                if(node.isFull()){
                    if(node != root)
                        node.promoting = true;
                    TTNode newLeft = new TTNode(node.leftKey, null, node.left, returnedNode.left, null);
                    TTNode newCenter = new TTNode(node.rightKey, null, returnedNode.center, node.right, null);
                    node.leftKey = returnedNode.leftKey;
                    node.rightKey = null;
                    node.left = newLeft;
                    node.center = newCenter;
                    node.right = null;
                }
                else{
                    node.rightKey = returnedNode.leftKey;
                    node.center = returnedNode.left;
                    node.right = returnedNode.center;
                }
            }
        }
        else {
            returnedNode = insert(node.right, key);
            if(returnedNode.promoting){
                returnedNode.promoting = false;
                if(node != root)
                    node.promoting = true;

                TTNode newLeft = new TTNode(node.leftKey, null, node.left, node.center, null);
                node.leftKey = node.rightKey;
                node.rightKey = null;
                node.left = newLeft;
                node.center = returnedNode;
                node.right = null;
            }

        }

        return node;
    }

    public void insert(String key){
        root = insert(root, key);
    }

    public TTNode delete(TTNode node, String key){
        if(node == null)
            return null;

        if(compareKeys(node.leftKey, key) == 0){
            if(!node.isLeaf() && !node.center.isLeaf()) {
                node.leftKey = node.center.findLeftKey();
                node.center = delete(node.center, node.leftKey);

                if(node.center.leftKey == null){// Checking whether the deletion did not cause empty node
                    // If so, merging it with siblings
                    if(node.left.isFull()){
                        node.center.leftKey = node.leftKey;
                        node.leftKey = node.left.rightKey;
                        node.center.center = node.center.left;
                        node.center.left = node.left.right;
                        node.left.rightKey = null;
                        node.left.right = null;
                    }
                    else if(node.isFull()){
                        node.left.rightKey = node.leftKey;
                        node.left.right = node.center.left;
                        node.leftKey = node.rightKey;
                        node.center = node.right;
                        node.rightKey = null;
                        node.right = null;
                    }
                    else{
                        node.left.rightKey = node.leftKey;
                        node.left.right = node.center.left;
                        node.center = null;
                        node.leftKey = null;
                    }
                }
            }
            else if(!node.isLeaf() && node.center.isLeaf()){
                if(node.center.isFull()){
                    node.leftKey = node.center.leftKey;
                    node.center.leftKey = node.center.rightKey;
                    node.center.rightKey = null;
                }
                else if(node.left.isFull()){
                    node.leftKey = node.left.rightKey;
                    node.left.rightKey = null;
                }
                else if(node.isFull()){
                    node.left.rightKey = node.center.leftKey;
                    node.leftKey = node.rightKey;
                    node.rightKey = null;
                    node.center = node.right;
                    node.right = null;
                }
                else{
                    node.left.rightKey = node.center.leftKey;
                    node.center = null;
                    node.leftKey = null;
                }
            }

        }
        else if(node.isFull() && compareKeys(node.rightKey, key) == 0){
            if(!node.isLeaf() && !node.right.isLeaf()){
                node.rightKey = node.right.findLeftKey();
                node.right = delete(node.right, node.rightKey);

                if(node.right.leftKey == null){// Checking whether the deletion did not cause empty node
                    // If so, merging it with siblings
                    if(node.center.isFull()){
                        node.right.leftKey = node.rightKey;
                        node.rightKey = node.center.rightKey;
                        node.right.center = node.right.left;
                        node.right.left = node.center.right;
                        node.center.rightKey = null;
                        node.center.right = null;
                    }
                    else{
                        node.center.rightKey = node.rightKey;
                        node.center.right = node.right.left;
                        node.rightKey = null;
                        node.right = null;
                    }
                }
            }
            else if(!node.isLeaf() && node.right.isLeaf()){
                if(node.right.isFull()){
                    node.rightKey = node.right.leftKey;
                    node.right.leftKey = node.right.rightKey;
                    node.right.rightKey = null;
                }
                else if(node.center.isFull()){
                    node.rightKey = node.center.rightKey;
                    node.center.rightKey = null;
                }
                else if(node.left.isFull()){
                    node.rightKey = node.center.leftKey;
                    node.center.leftKey = node.leftKey;
                    node.leftKey = node.left.rightKey;
                    node.left.rightKey = null;
                }
                else{
                    node.rightKey = null;
                    node.center.rightKey = node.right.leftKey;
                    node.right = null;
                }
            }
        }

        else if(compareKeys(key, node.leftKey) < 0){
            if(node.left.isLeaf()){
                if(node.left.has(key)){// if left children is leaf and has a key
                    if (node.left.isFull()) {// if it's full we can simply delete the key
                        if(compareKeys(key, node.left.leftKey) == 0)
                            node.left.leftKey = node.left.rightKey;
                        node.left.rightKey = null;
                    }
                    else {//if it's not full than we borrow key from another children or from parent
                        if(node.center.isFull()){
                            node.left.leftKey = node.leftKey;
                            node.leftKey = node.center.leftKey;
                            node.center.leftKey = node.center.rightKey;
                            node.center.rightKey = null;
                        }
                        else if(node.isFull()){
                            node.left.leftKey = node.leftKey;
                            node.left.rightKey = node.center.leftKey;
                            node.leftKey = node.rightKey;
                            node.rightKey = null;
                            node.center = node.right;
                            node.right = null;
                        }
                        else{
                            node.left.leftKey = node.leftKey;
                            node.left.rightKey = node.center.leftKey;
                            node.center = null;
                            node.leftKey = null;
                        }
                    }
                }
                else {// if it doesn't have a key to be found the deletion can't be done
                    successDeletion = false;
                }
            }
            else {
                node.left = delete(node.left, key);// If children isn't leaf diving into left children to find key
                if(node.left.leftKey == null){// Checking whether the deletion did not cause empty node
                    // If so, merging it with siblings
                    if(node.center.isFull()){
                        node.left.leftKey = node.leftKey;
                        node.left.center = node.center.left;
                        node.leftKey = node.center.leftKey;
                        node.center.leftKey = node.center.rightKey;
                        node.center.left = node.center.center;
                        node.center.center = node.center.right;
                        node.center.rightKey = null;
                        node.center.right = null;
                    }
                    else if(node.isFull()){
                        node.left.leftKey = node.leftKey;
                        node.left.rightKey = node.center.leftKey;
                        node.left.center = node.center.left;
                        node.left.right = node.center.center;
                        node.leftKey = node.rightKey;
                        node.center = node.right;
                        node.rightKey = null;
                        node.right = null;
                    }
                    else{
                        node.left.leftKey = node.leftKey;
                        node.left.rightKey = node.center.leftKey;
                        node.left.center = node.center.left;
                        node.left.right = node.center.center;
                        node.center = null;
                        node.leftKey = null;
                    }
                }
            }
        }
        else if(!node.isFull() || compareKeys(key, node.rightKey) < 0){
            if(node.center.isLeaf()){
                if(node.center.has(key)){// if center children is leaf and has a key
                    if (node.center.isFull()) {// if it's full we can simply delete the key
                        if(compareKeys(key, node.center.leftKey) == 0)
                            node.center.leftKey = node.center.rightKey;
                        node.center.rightKey = null;
                    }
                    else {//if it's not full than we borrow key from another children or from parent
                        if(node.left.isFull()){
                            node.center.leftKey = node.leftKey;
                            node.leftKey = node.left.rightKey;
                            node.left.rightKey = null;
                        }
                        else if(node.isFull()){
                            node.left.rightKey = node.leftKey;
                            node.leftKey = node.rightKey;
                            node.rightKey = null;
                            node.center = node.right;
                            node.right = null;
                        }
                        else{
                            node.left.rightKey = node.leftKey;
                            node.center = null;
                            node.leftKey = null;
                        }
                    }
                }
                else {// if it doesn't have a key to be found the deletion can't be done
                    successDeletion = false;
                }
            }
            else {
                node.center = delete(node.center, key);// If children isn't leaf diving into center children to find key
                if(node.center.leftKey == null){// Checking whether the deletion did not cause empty node
                    // If so, merging it with siblings
                    if(node.left.isFull()){
                        node.center.leftKey = node.leftKey;
                        node.leftKey = node.left.rightKey;
                        node.center.center = node.center.left;
                        node.center.left = node.left.right;
                        node.left.rightKey = null;
                        node.left.right = null;
                    }
                    else if(node.isFull()){
                        node.left.rightKey = node.leftKey;
                        node.left.right = node.center.left;
                        node.leftKey = node.rightKey;
                        node.center = node.right;
                        node.rightKey = null;
                        node.right = null;
                    }
                    else{
                        node.left.rightKey = node.leftKey;
                        node.left.right = node.center.left;
                        node.center = null;
                        node.leftKey = null;
                    }
                }
            }
        }
        else{
            if(node.right.isLeaf()){
                if(node.right.has(key)){// if center children is leaf and has a key
                    if (node.right.isFull()) {// if it's full we can simply delete the key
                        if(compareKeys(key, node.right.leftKey) == 0)
                            node.right.leftKey = node.right.rightKey;
                        node.right.rightKey = null;
                    }
                    else {//if it's not full than we borrow key from another children or from parent
                        if(node.center.isFull()){
                            node.right.leftKey = node.rightKey;
                            node.rightKey = node.center.rightKey;
                            node.center.rightKey = null;
                        }
                        else{
                            node.center.rightKey = node.rightKey;
                            node.rightKey = null;
                            node.right = null;
                        }
                    }
                }
                else {// if it doesn't have a key to be found the deletion can't be done
                    successDeletion = false;
                }
            }
            else {
                node.right = delete(node.right, key);// If children isn't leaf diving into right children to find key
                if(node.right.leftKey == null){// Checking whether the deletion did not cause empty node
                    // If so, merging it with siblings
                    if(node.center.isFull()){
                        node.right.leftKey = node.rightKey;
                        node.rightKey = node.center.rightKey;
                        node.right.center = node.right.left;
                        node.right.left = node.center.right;
                        node.center.rightKey = null;
                        node.center.right = null;
                    }
                    else{
                        node.center.rightKey = node.rightKey;
                        node.center.right = node.right.left;
                        node.rightKey = null;
                        node.right = null;
                    }
                }
            }
        }

        return node;

    }

    public void delete(String key){
        root = delete(root, key);
        if(successDeletion)
            System.out.println(key + " was successfully deleted.");
        else
            System.out.println(key + " was not found.");
        successDeletion = true;
    }

    public void print(){
        root.print("", root, false);
    }
}