package com.lab6.client.configuration;

import com.lab6.client.controllers.CollectionManager;
import com.lab6.client.io.CollectionFileReader;
import com.lab6.client.io.CollectionFileWriter;
import com.lab6.client.io.JsonCollectionFileOperator;

import java.io.File;

/**
 * Configurator, which configures the app
 **/
public final class Configurator {
    public static final CollectionFileReader<CollectionManager> COLLECTION_FILE_READER = new JsonCollectionFileOperator();
    public static final CollectionFileWriter<CollectionManager> COLLECTION_FILE_WRITER = new JsonCollectionFileOperator();
    private static File inputFile;
    private static File outputFile;


    private Configurator() {

    }

    public static File getInputFile() {
        return inputFile;
    }

    public static File getOutputFile() {
        return outputFile;
    }

    public static boolean configure() {
        if (System.getenv("JSON_COLLECTION") == null) {
            System.out.println(System.getenv());
            System.out.println("Установите переменное окружение \"JSON_COLLECTION\"");
            return false;
        } else {
            inputFile = new File(System.getenv("JSON_COLLECTION"));
            outputFile = new File(System.getenv("JSON_COLLECTION"));
        }
        return true;
    }
}
