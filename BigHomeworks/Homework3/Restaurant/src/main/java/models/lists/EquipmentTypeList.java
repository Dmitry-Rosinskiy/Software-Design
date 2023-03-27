package models.lists;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.EquipmentType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentTypeList {
    private static EquipmentTypeList instance = null;

    public final List<EquipmentType> equipment_type = new ArrayList<>();

    private EquipmentTypeList() {}

    public static EquipmentTypeList getInstance() {
        if (instance == null) {
            instance = new EquipmentTypeList();
        }
        return instance;
    }

    public static void read(String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            instance = mapper.readValue(new File(path), EquipmentTypeList.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
