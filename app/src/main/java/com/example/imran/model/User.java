package com.example.imran.model;

public class User {
    String id,email,fullname,dob,address,password,SNI;

    public User(String id, String email, String fullname, String dob, String address, String password, String SNI) {
        this.id = id;
        this.email = email;
        this.fullname = fullname;
        this.dob = dob;
        this.address = address;
        this.password = password;
        this.SNI = SNI;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSNI() {
        return SNI;
    }

    public void setSNI(String SNI) {
        this.SNI = SNI;
    }
}
