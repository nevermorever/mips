package com.zyjd.mips.entity;

import java.io.Serializable;
import java.util.List;

public class Scene implements Serializable {
    public String name;
    public List<Element> elements;

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
