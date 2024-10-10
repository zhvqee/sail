package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BaseTest {

    public static String readText(String fileName) throws IOException {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        InputStream resourceAsStream = systemClassLoader.getResourceAsStream(fileName);
        InputStreamReader reader = new InputStreamReader(resourceAsStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String readLine = null;
        String ret = "";
        while ((readLine = bufferedReader.readLine()) != null) {
            ret += readLine;
        }
        bufferedReader.close();
        return ret;
    }
}
