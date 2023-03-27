package models.lists;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Visitor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VisitorList {
    private static VisitorList instance = null;

    public final List<Visitor> visitors_orders = new ArrayList<>();

    private VisitorList() {}

    public static VisitorList getInstance() {
        if (instance == null) {
            instance = new VisitorList();
        }
        return instance;
    }

    public static void read(String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            instance = mapper.readValue(new File(path), VisitorList.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
