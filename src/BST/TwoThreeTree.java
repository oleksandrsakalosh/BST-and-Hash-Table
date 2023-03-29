package BST;

public class TwoThreeTree {
    // Inner class representing a node in the 2-3 tree
    class TTNode {
        String leftKey, rightKey;
        TTNode left, center, right;
        boolean promoting;

        // Constructor for a node with three children
        public TTNode(String leftKey, String rightKey, TTNode left, TTNode center, TTNode right){
            this.leftKey = leftKey;
            this.rightKey = rightKey;
            this.left = left;
            this.right = right;
            this.center = center;
            promoting = false;
        }

        // Constructor for a node with one child
        public TTNode(String leftKey){
            this.leftKey = leftKey;
            rightKey = null;
            left = center = right = null;
            promoting = false;
        }

        // Returns whether the node is a leaf
        public boolean isLeaf(){ return this.left == null; }

        // Returns whether the node is full
        public boolean isFull(){ return !(this.rightKey == null); }

        // Returns whether the node has the given key
        public boolean has(String key){ return this.leftKey.equals(key) || (this.isFull() && this.rightKey.equals(key)); }

        // Returns the leftmost key in the subtree rooted at this node
        public String findLeftKey(){
            return (this.left == null) ? this.leftKey : this.left.findLeftKey();
        }

        //Adds the given node to this node. If this node is full, it will split into two or three nodes as needed.
        public TTNode add(TTNode node){
            if(this.isFull()){ // check if node is full
                promoting = true; // set the flag for promoting nodes
                if(compareKeys(leftKey, node.leftKey) > 0){ // node goes to the left
                    left = node;
                    center = new TTNode(rightKey); // create new node for the center key
                    rightKey = null;
                }
                else if(compareKeys(rightKey, node.leftKey) < 0){ // node goes to the right
                    center = node;
                    left = new TTNode(leftKey); // create new node for the left key
                    leftKey = rightKey;
                    rightKey = null;
                }
                else{ // node goes to the center
                    left = new TTNode(leftKey); // create new node for the left key
                    center = new TTNode(rightKey); // create new node for the center key
                    leftKey = node.leftKey;
                    rightKey = null;
                }
            }
            else{ // if node is not full
                if(compareKeys(leftKey, node.leftKey) < 0){ // node goes to the right
                    rightKey = node.leftKey;
                    center = node.left;
                    right = node.center;
                }
                else { // node goes to the left
                    rightKey = leftKey;
                    right = center;
                    leftKey = node.leftKey;
                    center = node.center;
                }
            }
            return this; // return the updated node
        }
    }

    TTNode root; // the root node of the 2-3 tree

    //Constructs an empty 2-3 tree.
    public TwoThreeTree() { root = null; }

    // Method to compare two keys lexicographically and return the difference between them
    int compareKeys(String key1, String key2){
        // Get the lengths of both keys
        int length1 = key1.length();
        int length2 = key2.length();
        // Determine the minimum length of the two keys
        int minLength = Math.min(length1, length2);

        // Iterate over the characters in both keys and compare them
        for(int i = 0; i < minLength; i++){
            int val1 = (int)key1.charAt(i);
            int val2 = (int)key2.charAt(i);

            if(val1 != val2)
                return val2 - val1; // If characters differ, return their difference
        }

        // If the keys have a different length, return the difference between the lengths
        if(length1 != length2)
            return length2 - length1;
        else
            return 0; // Otherwise, the keys are equal
    }

    // Insert a key into the 2-3 tree
    public TTNode insert(TTNode node, String key){
        // If the node is null, create a new node with the given key
        if(node == null)
            return new TTNode(key);

        // If the node is a leaf node
        if(node.isLeaf()) {
            // If the node is not the root node, add a new node with the given key
            if(node != root)
                return node.add(new TTNode(key));
                // If the node is the root node, add a new node with the given key and promote it to be the new root
            else{
                node.add(new TTNode(key));
                node.promoting = false;
                return node;
            }
        }

        // If the key is less than the left key of the node, recursively insert the key into the left subtree
        TTNode returnedNode;
        if(compareKeys(key, node.leftKey) < 0) {
            returnedNode = insert(node.left, key);
            // If the returned node is promoting, promote it to the current node
            if(returnedNode.promoting){
                returnedNode.promoting = false;
                // If the node is full, split the node and promote the middle key to the parent node
                if(node.isFull()){
                    if(node != root)
                        node.promoting = true;
                    node.left = returnedNode;
                    node.center = new TTNode(node.rightKey, null, node.center, node.right, null);
                    node.right = null;
                    node.rightKey = null;
                }
                // If the node is not full, add the returned node to the current node
                else{
                    node.rightKey = node.leftKey;
                    node.right = node.center;
                    node.leftKey = returnedNode.leftKey;
                    node.left = returnedNode.left;
                    node.center = returnedNode.center;
                }
            }
        }
        // If the key is greater than or equal to the left key of the node and less than the right key of the node, recursively insert the key into the center subtree
        else if((!node.isFull() || compareKeys(key, node.rightKey) < 0) && !key.equals(node.leftKey)) {
            returnedNode = insert(node.center, key);
            // If the returned node is promoting, promote it to the current node
            if(returnedNode.promoting){
                returnedNode.promoting = false;
                // If the node is full, split the node and promote the middle key to the parent node
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
                // If the node is not full, add the returned node to the current node
                else{
                    node.rightKey = returnedNode.leftKey;
                    node.center = returnedNode.left;
                    node.right = returnedNode.center;
                }
            }
        }
        // If the key is greater than the right key of the node, recursively insert the key into the right subtree
        else if(node.isFull() && compareKeys(key, node.rightKey) > 0){
            returnedNode = insert(node.right, key);
            if(returnedNode.promoting){
                returnedNode.promoting = false;
                // If the current node is not the root, it needs to be split
                if(node != root)
                    node.promoting = true;

                // Create two new nodes to split the current node
                TTNode newLeft = new TTNode(node.leftKey, null, node.left, node.center, null);
                node.leftKey = node.rightKey;
                node.rightKey = null;
                node.left = newLeft;
                node.center = returnedNode;
                node.right = null;
            }
        }
        else{
            System.out.println("The key " + key + " is already exists.");
        }

        return node;
    }

    public void insert(String key){
        root = insert(root, key);
    }

    public TTNode delete(TTNode node, String key){

        if(node == null) {
            System.out.println("The key " + key + " was not found.");
            return null;
        }

        if(compareKeys(node.leftKey, key) == 0){

            // if the left key of node is equal to the key to be deleted
            if(!node.isLeaf() && !node.center.isLeaf()) {
                // if node is not a leaf node and the center child is not a leaf node
                // replace the left key with the leftmost key of the center child
                node.leftKey = node.center.findLeftKey();
                // delete the leftmost key of the center child recursively
                node.center = delete(node.center, node.leftKey);

                if(node.center.leftKey == null){// Checking whether the deletion did not cause empty node
                    // If so, merging it with siblings

                    if(node.left.isFull()){
                        // if left sibling is full, borrow a key from it
                        node.center.leftKey = node.leftKey;
                        node.leftKey = node.left.rightKey;
                        node.center.center = node.center.left;
                        node.center.left = node.left.right;
                        node.left.rightKey = null;
                        node.left.right = null;
                    }
                    else if(node.isFull()){
                        // if node is full, borrow a key from right sibling
                        node.left.rightKey = node.leftKey;
                        node.left.right = node.center.left;
                        node.leftKey = node.rightKey;
                        node.center = node.right;
                        node.rightKey = null;
                        node.right = null;
                    }
                    else{
                        // otherwise, merge with left sibling
                        node.left.rightKey = node.leftKey;
                        node.left.right = node.center.left;
                        node.center = null;
                        node.leftKey = null;
                        if(node == root)
                            node = node.left;
                    }
                }
            }
            else if(!node.isLeaf() && node.center.isLeaf()){
                // if node is not a leaf node but center child is a leaf node

                if(node.center.isFull()){
                    // if center child is full, borrow a key from it
                    node.leftKey = node.center.leftKey;
                    node.center.leftKey = node.center.rightKey;
                    node.center.rightKey = null;
                }
                else if(node.left.isFull()){
                    // if left sibling is full, borrow a key from it
                    node.leftKey = node.left.rightKey;
                    node.left.rightKey = null;
                }
                else if(node.isFull()){
                    // if node is full, borrow a key from right sibling
                    node.left.rightKey = node.center.leftKey;
                    node.leftKey = node.rightKey;
                    node.rightKey = null;
                    node.center = node.right;
                    node.right = null;
                }
                else{
                    // otherwise, merge with left sibling
                    node.left.rightKey = node.center.leftKey;
                    node.center = null;
                    node.leftKey = null;
                    if(node == root)
                        node = node.left;
                }
            }

        }
        else if(node.isFull() && compareKeys(node.rightKey, key) == 0){
            // if the right key of node is equal to the key to be deleted

            if(!node.isLeaf() && !node.right.isLeaf()){
                // if node is not a leaf node and the right child is not a leaf node
                // replace the right key with the leftmost key of the right child
                node.rightKey = node.right.findLeftKey();
                // delete the leftmost key of the right child recursively
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
                // if node is not a leaf node but right child is a leaf node
                // if right child is full, borrow a key from it
                if(node.right.isFull()){
                    node.rightKey = node.right.leftKey;
                    node.right.leftKey = node.right.rightKey;
                    node.right.rightKey = null;
                }
                // if center child is full, borrow a key from it
                else if(node.center.isFull()){
                    node.rightKey = node.center.rightKey;
                    node.center.rightKey = null;
                }
                // if left sibling is full, borrow a key from it
                else if(node.left.isFull()){
                    node.rightKey = node.center.leftKey;
                    node.center.leftKey = node.leftKey;
                    node.leftKey = node.left.rightKey;
                    node.left.rightKey = null;
                }
                // otherwise, merge with center sibling
                else{
                    node.rightKey = null;
                    node.center.rightKey = node.right.leftKey;
                    node.right = null;
                }
            }
        }
        // if key value is less than left key's
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
                            if(node == root)
                                node = node.left;
                        }
                    }
                }
                else {// if it doesn't have a key to be found the deletion can't be done
                    System.out.println("The key " + key + " was not found.");
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
                        if(node == root)
                            node = node.left;
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
                            if(node == root)
                                node = node.left;
                        }
                    }
                }
                else {// if it doesn't have a key to be found the deletion can't be done
                    System.out.println("The key " + key + " was not found.");
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
                        if(node == root)
                            node = node.left;
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
                    System.out.println("The key " + key + " was not found.");
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

    public void delete(String key){ root = delete(root, key); }

    public TTNode find(TTNode node, String key){
        //if current node is null then key is missing in tree
        if(node == null) {
            System.out.println("The key " + key + " was not found.");
            return null;
        }
        // if node has key then we found it
        if(node.has(key))
            return node;
        //diving into left children to find key
        if(compareKeys(key, node.leftKey) < 0)
            return find(node.left, key);
        // diving into center children to find key
        if(!node.isFull() || compareKeys(key, node.rightKey) < 0)
            return find(node.center, key);
        // diving into right children to find key
        else
            return find(node.right, key);
    }

    public TTNode find(String key){ return find(root, key); }
}