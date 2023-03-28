package HashTable;

public class Chaining {
    private class ChainNode{
        String key;
        ChainNode next;

        public ChainNode(String key){
            this.key = key;
            next = null;
        }
    }

    private class HashTable{
        ChainNode[] table;
        int size, nodeCount;

        public HashTable(int size){
            table = new ChainNode[size];
            this.size = size;
            nodeCount = 0;
        }
    }

    int INIT_SIZE = 7;
    HashTable table1, table2;

    public Chaining(){
        table1 = new HashTable(INIT_SIZE);
        table2 = null;
    }

    long getHash(String key){
        return (key.length() != 1) ? (33 * getHash(key.substring(1)) + (int)key.charAt(0)) : ((int)key.charAt(0));
    }

    void insert(String key, HashTable hTable){
        long hash1 = getHash(key);
        int hash = (int) (hash1 % hTable.size);
        ChainNode newNode = new ChainNode(key);

        if(hTable.table[hash] == null){
            hTable.table[hash] = newNode;
        }
        else{
            ChainNode temp = hTable.table[hash];
            while(temp.next != null)
                temp = temp.next;
            temp.next = newNode;
        }
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
                ChainNode temp = table1.table[i];
                while(temp.next != null){
                    insert(temp.key, table2);
                    temp = temp.next;
                }
                insert(temp.key, table2);
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
        if(table1.table[hash] != null){
            ChainNode temp = table1.table[hash];
            if(temp.key.equals(key)) {
                table1.table[hash] = temp.next;
                table1.nodeCount--;
            }
            else {
                boolean delete = false;
                while (temp.next != null) {
                    if (temp.next.key.equals(key)) {
                        temp.next = temp.next.next;
                        table1.nodeCount--;
                        delete = true;
                        break;
                    }
                    temp = temp.next;
                }
                if(!delete)
                    System.out.println(key + " was not found.");
            }
        }
        else{
            System.out.println(key + " was not found.");
        }
    }

    public ChainNode find(String key){
        long hash1 = getHash(key);
        int hash = (int) (hash1 % table1.size);
        if(table1.table[hash] != null){
            ChainNode temp = table1.table[hash];
            while (temp != null) {
                if (temp.key.equals(key))
                    return temp;
                temp = temp.next;
            }
        }
        System.out.println(key + " was not found.");
        return null;
    }

    public void print(){
        System.out.print("[");
        for(int i = 0; i < table1.size; i++){
            if(table1.table[i] != null){
                ChainNode temp = table1.table[i];
                while(temp.next != null){
                    System.out.print(temp.key + " -> ");
                    temp = temp.next;
                }
                System.out.print(temp.key + ",\n");
            }
        }
        System.out.println("]\n" + table1.size + "\n" + table1.nodeCount);
    }
}
