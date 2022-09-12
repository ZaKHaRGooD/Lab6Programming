package com.lab6.client.io;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.lab6.client.controllers.CollectionManager;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Json файл читалка и записывалка
 * <p>
 * Чтение с файла и запись в файл
 */
public class JsonCollectionFileOperator implements CollectionFileReader<CollectionManager>, CollectionFileWriter<CollectionManager> {
    @Override
    public CollectionManager read(File file) {
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules().registerModule(new JavaTimeModule());
        CollectionManager collectionManager = null;
        try {
            collectionManager = mapper.readValue(file, CollectionManager.class);
        } catch (IOException e) {
             e.printStackTrace();
        }
        return collectionManager;
    }

    @Override
    public void write(File file, CollectionManager products) throws IOException {
        String objectMapper = new ObjectMapper().findAndRegisterModules().registerModule(new JavaTimeModule()).writeValueAsString(products);
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.print(objectMapper);
        printWriter.close();
        System.out.println("Коллекция успешно сохранена в файл");
    }
}
