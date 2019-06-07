package com.llgzone.market.model;

public class User {
    private String uid;

    private String name;

    private Byte sex;

    private String phone;

    private String password;

    private String description;

    private Byte type;

    public User(String uid, String name, Byte sex, String phone, String password, String description, Byte type) {
        this.uid = uid;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.password = password;
        this.description = description;
        this.type = type;
    }

    public User() {
        super();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}