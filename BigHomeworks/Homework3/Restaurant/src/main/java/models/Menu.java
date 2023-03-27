package models;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class Menu {
    private static Menu instance = null;

    public final List<Dish> menu_dishes = new ArrayList<>();

    private Menu() {}

    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    public static void read(String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            instance = mapper.readValue(new File(path), Menu.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
