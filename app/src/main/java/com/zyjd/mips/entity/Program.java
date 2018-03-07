package com.zyjd.mips.entity;

import java.io.Serializable;
import java.util.List;


public class Program implements Serializable {
//    public String name;
    public List<Scene> scenes;
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public List<Scene> getScenes() {
        return scenes;
    }

    public void setScenes(List<Scene> scenes) {
        this.scenes = scenes;
    }
}
