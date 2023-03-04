import BST.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Main {

    public static void main(String[] args){
        AvlTree avlTree = new AvlTree();
        TwoThreeTree ttTree = new TwoThreeTree();

        try {
            FileReader reader = new FileReader("test.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                avlTree.insert(line);
                ttTree.insert(line);

            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        avlTree.delete("adxxjusxpm");
        avlTree.delete("swqcqjceir");
        avlTree.delete("igunqxcmyp");
        avlTree.delete("jgdhglsyqt");

        avlTree.print();

        if(avlTree.find("axrokashom") != null)
            System.out.println("found\n");
        else
            System.out.println("did not find\n");

        if(avlTree.find("igunqxcmyp") != null)
            System.out.println("found\n");
        else
            System.out.println("did not find\n");

        if(avlTree.find("loremipsum") != null)
            System.out.println("found\n");
        else
            System.out.println("did not find\n");

        if(avlTree.find("yukkmklgoy") != null)
            System.out.println("found\n");
        else
            System.out.println("did not find\n");

        System.out.println("-------------------");

        ttTree.print();
    }

    static String generateString(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
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
