package org.example;

import com.github.javafaker.Faker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class FileCreateMain {
    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(BuildIndexMain.dataFile.getPath() + File.separator + "data.txt");
        Faker faker = new Faker();
        for (int i = 1; i < 1000; i++) {
            fileWriter.append(UUID.randomUUID().toString().replaceAll("-", ""));
            fileWriter.append(" ");
            fileWriter.append(faker.lorem().paragraph().substring(0, 40));
            fileWriter.append("\n");
        }
        fileWriter.flush();
        fileWriter.close();

    }
}