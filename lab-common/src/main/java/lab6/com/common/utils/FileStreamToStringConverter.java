package com.lab6.client.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Класс для записи входного потока в переменную line.
 */
public final class FileStreamToStringConverter {
    private FileStreamToStringConverter() {

    }

    public static String fileStreamToString(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        return bufferedReader.readLine();
//        String line = bufferedReader.readLine();
//        return line;
    }
}
