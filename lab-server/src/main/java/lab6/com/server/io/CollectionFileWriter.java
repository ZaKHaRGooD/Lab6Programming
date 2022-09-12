package com.lab6.client.io;

import java.io.File;
import java.io.IOException;

/**
 * Интерфейс для записи в файл
 * @param <T> параметризованный метод
 */
public interface CollectionFileWriter<T> {
    void write(File file, T object) throws IOException;
}
