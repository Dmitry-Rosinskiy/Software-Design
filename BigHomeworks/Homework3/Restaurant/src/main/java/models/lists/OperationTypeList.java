package models.lists;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.OperationType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OperationTypeList {
    private static OperationTypeList instance = null;

    public final List<OperationType> operation_types = new ArrayList<>();

    private OperationTypeList() {}

    public static OperationTypeList getInstance() {
        if (instance == null) {
            instance = new OperationTypeList();
        }
        return instance;
    }

    public static void read(String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            instance = mapper.readValue(new File(path), OperationTypeList.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
