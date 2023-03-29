package HashTable;

public class LinearProbing extends HashTable {

    // Hash table that contains table with keys, size of table and count of node
    private class Table{
        String[] table;
        int size, nodeCount;

        // Constructor for new table
        public Table(int size){
            table = new String[size];
            this.size = size;
            nodeCount = 0;
        }

        // insert new key in table
        void insert(String key){
            // getting hash value for key
            long hash1 = getHash(key);
            int hash = (int) (hash1 % this.size);

            // going through all table until we find free cell
            while (this.table[hash] != null) {
                if (hash == this.size - 1)
                    hash = 0;
                else
                    hash++;
            }

            // if position at this hash is free adding new key
            this.table[hash] = key;
            this.nodeCount++;
        }
    }

    // First table is regular and the second one is used when increasing table size
    Table table1, table2;

    // Constructor for new Hash table
    public LinearProbing(){
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
                table2.insert(table1.table[i]);
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
        int native_hash = hash;// remembering position we started from
        // going through entire table until we find needed key
        while(table1.table[hash] == null || !table1.table[hash].equals(key)) {
            //if we got to the end of table going to the first cell
            if(hash == table1.size - 1)
                hash = 0;
            else
                hash++;
            // if we made a loop then key is missing in the tree
            if(hash == native_hash){
                System.out.println(key + " was not found.");
                return;
            }
        }
        //deleting key from tree
        table1.table[hash] = null;
        table1.nodeCount--;
    }

    // Finding key in the tree
    public String find(String key){
        // getting hash value for key
        long hash1 = getHash(key);
        int hash = (int) (hash1 % table1.size);
        int native_hash = hash;// remembering position we started from
        // going through entire table until we find needed key
        while(table1.table[hash] == null || !table1.table[hash].equals(key)) {
            //if we got to the end of table going to the first cell
            if(hash == table1.size - 1)
                hash = 0;
            else
                hash++;
            // if we made a loop then key is missing in the tree
            if(hash == native_hash){
                System.out.println(key + " was not found.");
                return null;
            }
        }
        // returning found key
        return table1.table[hash];
    }
}
