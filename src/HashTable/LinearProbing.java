package HashTable;

public class LinearProbing {

    private class HashTable{
        String[] table;
        int size, nodeCount;

        public HashTable(int size){
            table = new String[size];
            this.size = size;
            nodeCount = 0;
        }
    }

    int INIT_SIZE = 7;
    HashTable table1, table2;

    public LinearProbing(){
        table1 = new HashTable(INIT_SIZE);
        table2 = null;
    }

    long getHash(String key){
        return (key.length() != 1) ? (33 * getHash(key.substring(1)) + (int)key.charAt(0)) : ((int)key.charAt(0));
    }

    void insert(String key, HashTable hTable){
        long hash1 = getHash(key);
        int hash = (int) (hash1 % hTable.size);

        while (hTable.table[hash] != null) {
            if (hash == hTable.size - 1)
                hash = 0;
            else
                hash++;
        }

        hTable.table[hash] = key;
        hTable.nodeCount++;
    }

    public void insert(String key){
        insert(key, table1);
        if((float) table1.nodeCount / (float) table1.size > 0.75){
            increaseTableSize();
        }
    }

    void increaseTableSize(){
        table2 = new HashTable(getNextPrime(table1.size * 2));

        for (int i = 0; i < table1.size; i ++){
            if(table1.table[i] != null){
                insert(table1.table[i], table2);
            }
        }

        table1 = table2;
        table2 = null;
    }

    int getNextPrime(int num){
        num++;
        for(int i = 2; i < (num / 2); i++){
            if(num % i == 0){
                num++;
                i = 2;
            }
        }
        return num;
    }

    public void delete(String key){
        long hash1 = getHash(key);
        int hash = (int) (hash1 % table1.size);
        int native_hash = hash;
        while(table1.table[hash] == null || !table1.table[hash].equals(key)) {
            if(hash == table1.size - 1)
                hash = 0;
            else
                hash++;
            if(hash == native_hash){
                System.out.println(key + " was not found.");
                return;
            }
        }
        table1.table[hash] = null;
        table1.nodeCount--;
    }

    public String find(String key){
        long hash1 = getHash(key);
        int hash = (int) (hash1 % table1.size);
        int native_hash = hash;
        while(table1.table[hash] == null || !table1.table[hash].equals(key)) {
            if(hash == table1.size - 1)
                hash = 0;
            else
                hash++;
            if(hash == native_hash){
                System.out.println(key + " was not found.");
                return null;
            }
        }
        return table1.table[hash];
    }

    public void print(){
        System.out.print("[");
        for(int i = 0; i < table1.size; i++){
            if(table1.table[i] != null){
                System.out.print(table1.table[i] + ",\n");
            }
        }
        System.out.println("]\n" + table1.size + "\n" + table1.nodeCount);
    }
}
