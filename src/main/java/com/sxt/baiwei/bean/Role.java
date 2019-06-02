package com.sxt.baiwei.bean;

import java.io.Serializable;

public class Role implements Serializable {
    private Integer id;

    private String name;

    private String namezh;

    private Integer[] checkedMenu;

    public Integer[] getCheckedMenu() {
        return checkedMenu;
    }

    public void setCheckedMenu(Integer[] checkedMenu) {
        this.checkedMenu = checkedMenu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNamezh() {
        return namezh;
    }

    public void setNamezh(String namezh) {
        this.namezh = namezh == null ? null : namezh.trim();
    }
}