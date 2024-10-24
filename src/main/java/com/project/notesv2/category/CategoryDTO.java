package com.project.notesv2.category;


import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryDTO {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
