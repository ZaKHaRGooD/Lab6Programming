package com.lab6.client.io;

import java.io.File;
import java.io.IOException;

/**
 * Интерфейс для чтения из файла
 * @param <T> параметризованный метод
 */
public interface CollectionFileReader<T> {
    T read(File file) throws IOException;
}
