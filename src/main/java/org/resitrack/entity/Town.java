package org.resitrack.entity;

import java.util.List;

public class Town {
    private String id;
    private int zipCode;
    private String name;
    private float totalArea;
    private List<House> houses;

    public Town(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
