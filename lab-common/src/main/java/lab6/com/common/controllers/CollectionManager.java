package com.lab6.client.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.lab6.client.entities.Product;
import com.lab6.client.entities.validators.EntityValidator;
import com.lab6.client.io.CollectionFileReader;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


/**
 * класс оболочка для PriorityQueue, чтобы хранить некоторые дополнительную информацию
 *
 * @author Shevchenko Z.
 */
public class CollectionManager {
    private PriorityQueue<Product> products;
//    @JsonDeserialize(using = LocalDateDeserializer.class)
//    @JsonSerialize()
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd ")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    private ZonedDateTime creationDate = ZonedDateTime.now();
    private Map<String, List<Product>> groupsByCoordinates;

    public CollectionManager() {
        products = new PriorityQueue<>();
    }

    /**
     * Collection manager initialization from file
     *
     * @param collectionFileReader
     * @param file
     * @return
     * @throws IOException
     */
    public static CollectionManager readFromFile(CollectionFileReader<CollectionManager> collectionFileReader, File file) throws IOException {
        if (file.length() == 0) {
            return new CollectionManager();
        }
        try {
            CollectionManager collectionManager = collectionFileReader.read(file);
            collectionManager.setCreationDate();
            if (collectionManager.products == null) {
                collectionManager.products = new PriorityQueue<>();
            }
            collectionManager.setup();
            return collectionManager;
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IOException(e.getMessage());
        }
    }

    public PriorityQueue<Product> getProducts() {
        return products;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate() {
        creationDate = ZonedDateTime.now();
    }

    public void add(Product newProduct) {
        products.add(newProduct);
    }

    public void clear() {
        products.clear();
    }

    public void show() {
        if (!products.isEmpty()) {
            for (Product product : products) {
                System.out.println(product);
            }
        } else {
            System.out.println("Коллекция пуста.");
        }
    }

    public Product findById(Long id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        throw new IllegalArgumentException("Нет продукта с таким id");
    }

    public List<String> getInfo() {
        List<String> info = new ArrayList<>();
        info.add(products.getClass().getName());

        info.add(creationDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm")));
        info.add(String.valueOf(products.size()));
        return info;
    }

    public Product removeHead() {
        return products.poll();
    }

    public void makeGroupsByCoordinates() {
        groupsByCoordinates = new HashMap<>();
        for (Product product : products) {
            if (groupsByCoordinates.containsKey(product.getCoordinates().toString())) {
                groupsByCoordinates.get(product.getCoordinates().toString()).add(product);
            } else {
                groupsByCoordinates.put(product.getCoordinates().toString(), new ArrayList<>(Collections.singletonList(product)));
//                groupsByCoordinates.put(product.getCoordinates(), new ArrayList<>(Arrays.asList(product)));
            }
        }
    }

    public void outputGroupsByCoordinates() {
        if (groupsByCoordinates.isEmpty()) {
            System.out.println("Коллекция пуста");
        } else {
            for (Map.Entry<String, List<Product>> group : groupsByCoordinates.entrySet()) {
                System.out.println("Coordinates - " + group.getKey() + ": " + group.getValue().size() + " members.");
            }
        }
    }

    public void printAscending() {
        List<Product> productsAscending = new ArrayList<>(products);
        Collections.sort(productsAscending);
        for (Product product : productsAscending) {
            System.out.println(product);
        }
    }

    public void filterContainsName(String substring) {
        for (Product product : products) {
            int index = product.getName().lastIndexOf(substring);
            if (index == -1) {
                System.out.print("");
            } else {
                System.out.println(product.toString());
            }
        }
    }

    /**
     * сетап объекта класса CollectionManager
     */
    private void setup() {
        for (Product product : products) {
            if (EntityValidator.isEntityValid(product)) {
                product.setId();
            } else {
                throw new IllegalArgumentException("Поля продукта некорректны.");
            }
        }
    }
}
