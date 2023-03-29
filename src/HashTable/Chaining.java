package HashTable;

public class Chaining extends HashTable {
    //Node in chain of keys that have the same hash value
    private class ChainNode{
        String key;
        ChainNode next;

        // constructor for new node
        public ChainNode(String key){
            this.key = key;
            next = null;
        }
    }

    // Hash table that contains table with nodes, size of table and count of node
    private class Table{
        ChainNode[] table;
        int size, nodeCount;

        // constructor for new Hash table
        public Table(int size){
            table = new ChainNode[size];
            this.size = size;
            nodeCount = 0;
        }

        // insert new key in table
        void insert(String key){
            // getting hash value for key and making new Node to be inserted
            long hash1 = getHash(key);
            int hash = (int) (hash1 % this.size);
            ChainNode newNode = new ChainNode(key);

            // if position at this hash is free adding new Node
            if(this.table[hash] == null){
                this.table[hash] = newNode;
            }
            // if collision occurred adding node at the end of chain
            else{
                ChainNode temp = this.table[hash];
                while(temp.next != null)
                    temp = temp.next;
                temp.next = newNode;
            }
            this.nodeCount++;
        }
    }

    // First table is regular and the second one is used when increasing table size
    Table table1, table2;

    // Constructor for new Hash table
    public Chaining(){
        table1 = new Table(INIT_SIZE);
        table2 = null;
    }

    // inserting in table and checking if it need to be increased
    public void insert(String key){
        table1.insert(key);
        if((float) table1.nodeCount / (float) table1.size > 0.75){
            increaseTableSize();
        }
    }

    // increasing the size of the table
    void increaseTableSize(){
        // creating temporary table with new size
        table2 = new Table(getNextPrime(table1.size * 2));

        // filling it with old keys
        for (int i = 0; i < table1.size; i ++){
            if(table1.table[i] != null){
                ChainNode temp = table1.table[i];
                while(temp.next != null){
                    table2.insert(temp.key);
                    temp = temp.next;
                }
                table2.insert(temp.key);
            }
        }

        // replacing regular table with new one
        table1 = table2;
        table2 = null;
    }

    //deleting tke key from table
    public void delete(String key){
        // getting hash value for key
        long hash1 = getHash(key);
        int hash = (int) (hash1 % table1.size);
        if(table1.table[hash] != null){
            // Creating temporary node to go through entire chain in this cell
            ChainNode temp = table1.table[hash];
            // if first node needs to be deleted then inserting at his place next node
            if(temp.key.equals(key)) {
                table1.table[hash] = temp.next;
                table1.nodeCount--;
            }
            // otherwise going through entire chain to find needed node
            else {
                boolean delete = false;
                while (temp.next != null) {
                    // if we found needed node then deleting it
                    if (temp.next.key.equals(key)) {
                        temp.next = temp.next.next;
                        table1.nodeCount--;
                        delete = true;
                        break;
                    }
                    temp = temp.next;
                }
                // if we didn't find needed node them it's missing in the tree
                if(!delete)
                    System.out.println(key + " was not found.");
            }
        }
        // if cell is empty then key is missing in the tree
        else{
            System.out.println(key + " was not found.");
        }
    }

    // Finding key in the tree
    public ChainNode find(String key){
        // getting hash value for key
        long hash1 = getHash(key);
        int hash = (int) (hash1 % table1.size);

        if(table1.table[hash] != null){
            // Creating temporary node to go through entire chain in this cell until we find needed key
            ChainNode temp = table1.table[hash];
            while (temp != null) {
                if (temp.key.equals(key))
                    return temp;
                temp = temp.next;
            }
        }
        // if cell is empty then key is missing in the tree
        System.out.println(key + " was not found.");
        return null;
    }
}
