package models.lists;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.DishCard;
import models.Equipment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DishCardList {
    private static DishCardList instance = null;

    public final List<DishCard> dish_cards = new ArrayList<>();

    private DishCardList() {}

    public static DishCardList getInstance() {
        if (instance == null) {
            instance = new DishCardList();
        }
        return instance;
    }

    public static void read(String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            instance = mapper.readValue(new File(path), DishCardList.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
