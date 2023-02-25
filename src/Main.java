import BST.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Main {

    public static void main(String[] args){
        AvlTree Tree = new AvlTree();

        try {
            FileReader reader = new FileReader("test.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                Tree.insert(line);
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Tree.delete("adxxjusxpm");
        Tree.delete("swqcqjceir");
        Tree.delete("igunqxcmyp");
        Tree.delete("jgdhglsyqt");

        Tree.print();
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
