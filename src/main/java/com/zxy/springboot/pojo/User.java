package com.zxy.springboot.pojo;

/**
 * @Description:
 * @Author: zhangxy
 * @Date: Created in 2019/12/17
 */
public class User {
    private Integer id;
    private String name;
    private String adderess;

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
        this.name = name;
    }

    public String getAdderess() {
        return adderess;
    }

    public void setAdderess(String adderess) {
        this.adderess = adderess;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", adderess='" + adderess + '\'' +
                '}';
    }
}
