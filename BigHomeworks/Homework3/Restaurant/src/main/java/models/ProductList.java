package models;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductList {
    private static ProductList instance = null;

    public final List<Product> products = new ArrayList<>();

    private ProductList() {}

    public static ProductList getInstance() {
        if (instance == null) {
            instance = new ProductList();
        }
        return instance;
    }

    public static void read(String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            instance = mapper.readValue(new File(path), ProductList.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
