package BST;

public class AvlTree {
    // Class represents a node in the AVL tree.
    public class AVLNode {
        public String key;  // The key or value stored in the node
        int height;  // Height of the node
        AVLNode left, right;  // Pointers to the left and right child nodes

        // Constructor to create a new AVLNode with the specified key
        public AVLNode(String key){
            this.key = key;
            height = 1;
            left = right = null;
        }

        // Helper method to get the balance factor of a node
        public int getBalance(){
            // If right subtree is null, consider its height as 0
            int rightHeight = (this.right == null) ? 0 : this.right.height;
            // If left subtree is null, consider its height as 0
            int leftHeight = (this.left == null) ? 0 : this.left.height;
            return rightHeight - leftHeight;  // Return the difference between the heights of left and right subtrees
        }

        // Helper method to find the leftmost key in a subtree
        public String findLeftKey(){
            // If the node has no left child, return its own key
            return (this.left == null) ? this.key : this.left.findLeftKey();
        }
    }

    AVLNode root;  // Pointer to the root node of the AVL tree

    // Constructor to create an empty AVL tree
    public AvlTree(){
        root = null;
    }

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

    // Method to find the maximum height of two nodes
    int findMaxHeight(AVLNode x, AVLNode y){
        // Return the maximum height of the two nodes, or 0 if either node is null
        return Math.max((x == null) ? 0 : x.height, (y == null) ? 0 : y.height);
    }

    // Method to perform a Left-Left rotation
    AVLNode LeftLeft(AVLNode x){
        AVLNode z = x.left;
        AVLNode y = z.right;

        // Perform the rotation
        x.left = y;
        z.right = x;

        // Update the heights of the nodes involved
        x.height = 1 + findMaxHeight(x.left, x.right);
        z.height = 1 + findMaxHeight(z.left, z.right);

        return z;
    }

    // Method to perform a Left-Right rotation
    AVLNode LeftRight(AVLNode x){
        AVLNode z = x.left;
        AVLNode y = z.right;

        // Perform the rotation
        x.left = y;
        z.right = y.left;
        y.left = z;

        // Update the heights of the nodes involved
        z.height = 1 + findMaxHeight(z.left, z.right);
        y.height = 1 + findMaxHeight(y.left, y.right);
        x.height = 1 + findMaxHeight(x.left, x.right);

        // Perform a Left-Left rotation to complete the balancing process
        x = LeftLeft(x);

        return x;
    }

    // Method to perform a Right-Right rotation
    AVLNode RightRight(AVLNode x){
        AVLNode z = x.right;
        AVLNode y = z.left;

        // Perform the rotation
        x.right = y;
        z.left = x;

        // Update the heights of the nodes involved
        x.height = 1 + findMaxHeight(x.left, x.right);
        z.height = 1 + findMaxHeight(z.left, z.right);

        return z;
    }

    // Method to perform a Right-Left rotation
    AVLNode RightLeft(AVLNode x){
        AVLNode z = x.right;
        AVLNode y = z.left;

        // Perform the rotation
        x.right = y;
        z.left = y.right;
        y.right = z;

        // Update the heights of the nodes involved
        z.height = 1 + findMaxHeight(z.left, z.right);
        y.height = 1 + findMaxHeight(y.left, y.right);
        x.height = 1 + findMaxHeight(x.left, x.right);

        // Perform a Right-Right rotation to complete the balancing process
        x = RightRight(x);

        return x;
    }

    AVLNode rebalance(AVLNode node){
        // If the balance factor of the node is greater than 1, then the node is right-heavy.
        if(node.getBalance() > 1){
            // If the balance factor of the right child of the node is also greater than 0, then a right-right rotation is needed.
            if(node.right.getBalance() > 0)
                node = RightRight(node);
                // Otherwise, a right-left rotation is needed.
            else
                node = RightLeft(node);
        }
        // If the balance factor of the node is less than -1, then the node is left-heavy.
        else if(node.getBalance() < -1){
            // If the balance factor of the left child of the node is also less than 0, then a left-left rotation is needed.
            if(node.left.getBalance() < 0)
                node = LeftLeft(node);
                // Otherwise, a left-right rotation is needed.
            else
                node = LeftRight(node);
        }

        // Return the node after rebalancing.
        return node;
    }

    public void insert(String key){
        root = insert(root, key);
    }

    AVLNode insert(AVLNode node, String key){
        // If the current node is null, create a new node with the key and return it.
        if(node == null){
            return new AVLNode(key);
        }

        // Compare the key with the key of the current node.
        int compareValue = compareKeys(key, node.key);

        // If the key is greater than the key of the current node, insert it in the right subtree.
        if(compareValue > 0){
            node.right = insert(node.right, key);
        }
        // If the key is less than the key of the current node, insert it in the left subtree.
        else if(compareValue < 0){
            node.left = insert(node.left, key);
        }
        // If the key is already present in the tree, print a message and return the current node.
        else {
            return node;
        }

        // Update the height of the current node.
        node.height = 1 + findMaxHeight(node.left, node.right);

        // Rebalance the current node.
        return rebalance(node);
    }

    public void delete(String key){
        root = delete(root, key);
    }

    // Delete a node with a specific key from the subtree rooted at node
    AVLNode delete(AVLNode node, String key){
        if(node != null){
            int compareValue = compareKeys(key, node.key);
            if(compareValue > 0){
                node.right = delete(node.right, key);
            }
            else if(compareValue < 0){
                node.left = delete(node.left, key);
            }
            // If the node to be deleted has only one child
            else if(node.left == null)
                node = node.right;
            else if(node.right == null)
                node = node.left;
                // If the node to be deleted has two children
            else{
                // Find the smallest key in the right subtree
                node.key = node.right.findLeftKey();
                // Delete the node with the smallest key from the right subtree
                node.right = delete(node.right, node.key);
            }

            if(node != null) {
                // Update the height of the node
                node.height = 1 + findMaxHeight(node.left, node.right);
                // Rebalance the tree
                node = rebalance(node);
            }
        }

        return node;
    }

    // Find a node with a specific key in the tree
    public AVLNode find(String key){
        return find(root, key);
    }

    // Find a node with a specific key in the subtree rooted at node
    AVLNode find(AVLNode node, String key){
        if(node == null) {
            return null;
        }
        int compareValue = compareKeys(key, node.key);
        if(compareValue > 0)
            return find(node.right, key);
        if(compareValue < 0)
            return find(node.left, key);

        return node;
    }
}
