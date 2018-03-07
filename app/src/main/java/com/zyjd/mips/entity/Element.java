package com.zyjd.mips.entity;

import java.io.Serializable;

// 节目中的最小单元，单个的界面元素
public class Element implements Serializable {
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
