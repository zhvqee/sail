package org.example.stocks;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class StockWriteTxtMain {
    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter("/Users/user/Dowloads/workspace/sail/flink/src/main/resources/stock.txt");
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            fileWriter.write("stock" + random.nextInt(13));
            fileWriter.write(" ");
            fileWriter.write((System.currentTimeMillis() - random.nextInt(200)) + "");
            fileWriter.write(" ");
            fileWriter.write(random.nextInt(1000)+"");
            fileWriter.write(" ");
            fileWriter.write(random.nextLong() + "");
            fileWriter.write("\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }
}
