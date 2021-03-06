package com.epicodus.adoptdontshop.models;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Friend {
    String name;
    String animal;
    String zip;
    String size;
    String sex;
    String age;
    String email;
    List<String> imageURL = new ArrayList<>();
    private String pushId;
    String index;

    public Friend() {}

    public Friend(String name, String animal, String size,
                  String sex, String age, String zip, String email, ArrayList<String> imageURL)
    {

        this.name = name;
        this.animal = animal;
        this.zip = zip;
        this.size = size;
        this.sex = sex;
        this.age = age;
        this.email = email;
        this.imageURL = imageURL;
        this.index = "not_specified";
    }

    public String getName() {
        return name;
    }

    public String getAnimal() { return animal; }

    public String getZip() {
        return zip;
    }

    public String getSize() {
        return size;
    }

    public String getSex(){
        return sex;
    }

    public String getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getImageURL() {
        return imageURL;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }


}