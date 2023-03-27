package models.lists;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.ProductType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductTypeList {
    private static ProductTypeList instance = null;

    public final List<ProductType> product_types = new ArrayList<>();

    private ProductTypeList() {}

    public static ProductTypeList getInstance() {
        if (instance == null) {
            instance = new ProductTypeList();
        }
        return instance;
    }

    public static void read(String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            instance = mapper.readValue(new File(path), ProductTypeList.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
