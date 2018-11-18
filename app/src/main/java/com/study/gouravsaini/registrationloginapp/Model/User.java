package com.study.gouravsaini.registrationloginapp.Model;

public class User {

    private String admissionno;
    private String password;

    public User(String admissionno, String password) {
        this.admissionno = admissionno;
        this.password = password;
    }

    public User() {
    }

    public String getAdmissionno() {
        return admissionno;
    }

    public void setAdmissionno(String admissionno) {
        this.admissionno = admissionno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
