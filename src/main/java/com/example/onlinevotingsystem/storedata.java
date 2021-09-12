package com.example.onlinevotingsystem;

import android.media.Image;
import android.net.Uri;

public class storedata {

    String id,name,course,email,mob,password;
    public storedata() {
    }
    public storedata(String id, String name, String course, String email, String mob, String password) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.email = email;
        this.mob = mob;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
