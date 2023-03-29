import BST.*;
import HashTable.*;

import java.io.*;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {

        Tester test = new Tester();

        test.firstScenario();
        test.refresh();
        test.secondScenario();

        /*
        BufferedWriter writer = new BufferedWriter(new FileWriter("test2.txt"));

        for(int i = 0; i < 75000; i++){
            String str = generateString();
            writer.write(str + '\n');
        }
        writer.close();
         */
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
