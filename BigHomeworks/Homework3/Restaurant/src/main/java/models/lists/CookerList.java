package models.lists;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Cooker;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CookerList {
    private static CookerList instance = null;

    public final List<Cooker> cookers = new ArrayList<>();

    private CookerList() {}

    public static CookerList getInstance() {
        if (instance == null) {
            instance = new CookerList();
        }
        return instance;
    }

    public static void read(String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            instance = mapper.readValue(new File(path), CookerList.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
