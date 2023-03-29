import BST.AvlTree;
import BST.TwoThreeTree;
import HashTable.Chaining;
import HashTable.LinearProbing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Tester {
    AvlTree avlTree;
    TwoThreeTree ttTree;
    Chaining hash1;
    LinearProbing hash2;

    FileReader reader;
    BufferedReader bufferedReader;

    String line;

    long start, elapsedTimeMillis;

    public Tester() throws FileNotFoundException {
        avlTree = new AvlTree();
        ttTree = new TwoThreeTree();
        hash1 = new Chaining();
        hash2 = new LinearProbing();

        reader = new FileReader("test.txt");
        bufferedReader = new BufferedReader(reader);
    }

    public void firstScenario() throws IOException {
        start = System.currentTimeMillis();
        for(int i = 0; i < 1000; i++) {
            line = bufferedReader.readLine();
            avlTree.insert(line);
        }
        long elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("AVL Tree insert of 1 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        for(int i = 1000; i < 10000; i++) {
            line = bufferedReader.readLine();
            avlTree.insert(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("AVL Tree insert of 10 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        for(int i = 10000; i < 100000; i++) {
            line = bufferedReader.readLine();
            avlTree.insert(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("AVL Tree insert of 100 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        for(int i = 100000; i < 1000000; i++) {
            line = bufferedReader.readLine();
            avlTree.insert(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("AVL Tree insert of 1 000 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        reader = new FileReader("test.txt");
        bufferedReader = new BufferedReader(reader);

        start = System.currentTimeMillis();
        for(int i = 0; i < 1000; i++) {
            line = bufferedReader.readLine();
            avlTree.find(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("AVL Tree search of 1 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        for(int i = 1000; i < 10000; i++) {
            line = bufferedReader.readLine();
            avlTree.find(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("AVL Tree search of 10 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        for(int i = 10000; i < 100000; i++) {
            line = bufferedReader.readLine();
            avlTree.find(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("AVL Tree search of 100 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        for(int i = 100000; i < 1000000; i++) {
            line = bufferedReader.readLine();
            avlTree.find(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("AVL Tree search of 1 000 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        reader = new FileReader("test.txt");
        bufferedReader = new BufferedReader(reader);

        start = System.currentTimeMillis();
        for(int i = 0; i < 1000; i++) {
            line = bufferedReader.readLine();
            avlTree.delete(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("AVL Tree deletion of 1 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        for(int i = 1000; i < 10000; i++) {
            line = bufferedReader.readLine();
            avlTree.delete(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("AVL Tree deletion of 10 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        for(int i = 10000; i < 100000; i++) {
            line = bufferedReader.readLine();
            avlTree.delete(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("AVL Tree deletion of 100 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        for(int i = 100000; i < 1000000; i++) {
            line = bufferedReader.readLine();
            avlTree.delete(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("AVL Tree deletion of 1 000 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        reader = new FileReader("test.txt");
        bufferedReader = new BufferedReader(reader);

        start = System.currentTimeMillis();

        for(int i = 0; i < 1000; i++) {
            line = bufferedReader.readLine();
            ttTree.insert(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("2-3 Tree insert of 1 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        for(int i = 1000; i < 10000; i++) {
            line = bufferedReader.readLine();
            ttTree.insert(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("2-3 Tree insert of 10 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        for(int i = 10000; i < 100000; i++) {
            line = bufferedReader.readLine();
            ttTree.insert(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("2-3 Tree insert of 100 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        for(int i = 100000; i < 1000000; i++) {
            line = bufferedReader.readLine();
            ttTree.insert(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("2-3 Tree insert of 1 000 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        reader = new FileReader("test.txt");
        bufferedReader = new BufferedReader(reader);

        start = System.currentTimeMillis();
        for(int i = 0; i < 1000; i++) {
            line = bufferedReader.readLine();
            ttTree.find(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("2-3 Tree search of 1 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        for(int i = 1000; i < 10000; i++) {
            line = bufferedReader.readLine();
            ttTree.find(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("2-3 Tree search of 10 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        for(int i = 10000; i < 100000; i++) {
            line = bufferedReader.readLine();
            ttTree.find(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("2-3 Tree search of 100 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        for(int i = 100000; i < 1000000; i++) {
            line = bufferedReader.readLine();
            ttTree.find(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("2-3 Tree search of 1 000 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        reader = new FileReader("test.txt");
        bufferedReader = new BufferedReader(reader);

        start = System.currentTimeMillis();
        for(int i = 0; i < 1000; i++) {
            line = bufferedReader.readLine();
            ttTree.delete(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("2-3 Tree deletion of 1 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        for(int i = 1000; i < 10000; i++) {
            line = bufferedReader.readLine();
            ttTree.delete(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("2-3 Tree deletion of 10 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        for(int i = 10000; i < 100000; i++) {
            line = bufferedReader.readLine();
            ttTree.delete(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("2-3 Tree deletion of 100 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        for(int i = 100000; i < 1000000; i++) {
            line = bufferedReader.readLine();
            ttTree.delete(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("2-3 Tree deletion of 1 000 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        reader = new FileReader("test.txt");
        bufferedReader = new BufferedReader(reader);

        start = System.currentTimeMillis();
        for(int i = 0; i < 1000; i++) {
            line = bufferedReader.readLine();
            hash1.insert(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("Chaining Hash table insert of 1 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        for(int i = 1000; i < 10000; i++) {
            line = bufferedReader.readLine();
            hash1.insert(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("Chaining Hash table insert of 10 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        for(int i = 10000; i < 100000; i++) {
            line = bufferedReader.readLine();
            hash1.insert(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("Chaining Hash table insert of 100 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        for(int i = 100000; i < 1000000; i++) {
            line = bufferedReader.readLine();
            hash1.insert(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("Chaining Hash table insert of 1 000 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        reader = new FileReader("test.txt");
        bufferedReader = new BufferedReader(reader);

        start = System.currentTimeMillis();
        for(int i = 0; i < 1000; i++) {
            line = bufferedReader.readLine();
            hash1.find(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("Chaining Hash table search of 1 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        for(int i = 1000; i < 10000; i++) {
            line = bufferedReader.readLine();
            hash1.find(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("Chaining Hash table search of 10 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        for(int i = 10000; i < 100000; i++) {
            line = bufferedReader.readLine();
            hash1.find(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("Chaining Hash table search of 100 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        for(int i = 100000; i < 1000000; i++) {
            line = bufferedReader.readLine();
            hash1.find(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("Chaining Hash table search of 1 000 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        reader = new FileReader("test.txt");
        bufferedReader = new BufferedReader(reader);

        start = System.currentTimeMillis();
        for(int i = 0; i < 1000; i++) {
            line = bufferedReader.readLine();
            hash1.delete(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("Chaining Hash table deletion of 1 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        for(int i = 1000; i < 10000; i++) {
            line = bufferedReader.readLine();
            hash1.delete(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("Chaining Hash table deletion of 10 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        for(int i = 10000; i < 100000; i++) {
            line = bufferedReader.readLine();
            hash1.delete(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("Chaining Hash table deletion of 100 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        for(int i = 100000; i < 1000000; i++) {
            line = bufferedReader.readLine();
            hash1.delete(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("Chaining Hash table deletion of 1 000 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        reader = new FileReader("test.txt");
        bufferedReader = new BufferedReader(reader);

        start = System.currentTimeMillis();
        for(int i = 0; i < 1000; i++) {
            line = bufferedReader.readLine();
            hash2.insert(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("Linear probing Hash table insert of 1 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        for(int i = 1000; i < 10000; i++) {
            line = bufferedReader.readLine();
            hash2.insert(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("Linear probing Hash table insert of 10 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        for(int i = 10000; i < 100000; i++) {
            line = bufferedReader.readLine();
            hash2.insert(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("Linear probing Hash table insert of 100 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        for(int i = 100000; i < 1000000; i++) {
            line = bufferedReader.readLine();
            hash2.insert(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("Linear probing Hash table insert of 1 000 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        reader = new FileReader("test.txt");
        bufferedReader = new BufferedReader(reader);

        start = System.currentTimeMillis();
        for(int i = 0; i < 1000; i++) {
            line = bufferedReader.readLine();
            hash2.find(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("Linear probing Hash table search of 1 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        for(int i = 1000; i < 10000; i++) {
            line = bufferedReader.readLine();
            hash2.find(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("Linear probing Hash table search of 10 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        for(int i = 10000; i < 100000; i++) {
            line = bufferedReader.readLine();
            hash2.find(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("Linear probing Hash table search of 100 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        for(int i = 100000; i < 1000000; i++) {
            line = bufferedReader.readLine();
            hash2.find(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("Linear probing Hash table search of 1 000 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        reader = new FileReader("test.txt");
        bufferedReader = new BufferedReader(reader);

        start = System.currentTimeMillis();
        for(int i = 0; i < 1000; i++) {
            line = bufferedReader.readLine();
            hash2.delete(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("Linear probing Hash table deletion of 1 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        for(int i = 1000; i < 10000; i++) {
            line = bufferedReader.readLine();
            hash2.delete(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("Linear probing Hash table deletion of 10 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        for(int i = 10000; i < 100000; i++) {
            line = bufferedReader.readLine();
            hash2.delete(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("Linear probing Hash table deletion of 100 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");

        for(int i = 100000; i < 1000000; i++) {
            line = bufferedReader.readLine();
            hash2.delete(line);
        }
        elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("Linear probing Hash table deletion of 1 000 000 keys: " + Float.toString(elapsedTimeMillis/1000F) + "s.");
    }
}
