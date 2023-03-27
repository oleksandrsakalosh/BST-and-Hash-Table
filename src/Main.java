import BST.*;

import java.io.*;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {

        AvlTree avlTree = new AvlTree();
        TwoThreeTree ttTree = new TwoThreeTree();

        FileReader reader = new FileReader("test.txt");
        BufferedReader bufferedReader = new BufferedReader(reader);

        String line;

        long start = System.currentTimeMillis();
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

        reader.close();

        /*
        BufferedWriter writer = new BufferedWriter(new FileWriter("test.txt"));

        for(int i = 0; i < 1000000; i++){
            String str = generateString();
            writer.write(str + '\n');
        }
        writer.close();
         */

    }

    static String generateString(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 30;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        return generatedString;
    }

}
