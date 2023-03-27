package models.lists;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Equipment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentList {
    private static EquipmentList instance = null;

    public final List<Equipment> equipment = new ArrayList<>();

    private EquipmentList() {}

    public static EquipmentList getInstance() {
        if (instance == null) {
            instance = new EquipmentList();
        }
        return instance;
    }

    public static void read(String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            instance = mapper.readValue(new File(path), EquipmentList.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
