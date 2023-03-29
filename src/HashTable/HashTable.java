package HashTable;
// Super class of hash tables
public class HashTable {

    int INIT_SIZE = 7;

    // return hash value for given key
    long getHash(String key){
        return (key.length() != 1) ? (33 * getHash(key.substring(1)) + (int)key.charAt(0)) : ((int)key.charAt(0));
    }

    // return the next smallest prime number after the given one
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

}
