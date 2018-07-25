package com.seeds.seeds_birthdayreminder.Entity;

import android.graphics.Bitmap;

import java.util.Calendar;

public class BirthdayEvent {
    private String fullName;
    private Bitmap picture;
    private String relationToThePublisher;
    private String phoneNumber;
    private String emailAddress;
    private String letter;
    private int age;
    private Calendar birthDate;

    public BirthdayEvent(String fullName, int age, String relationToThePublisher, String letter, Calendar birthDate) {
        this.fullName = fullName;
        this.age = age;
        this.relationToThePublisher = relationToThePublisher;
        this.letter = letter;
        this.birthDate = birthDate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRelationToThePublisher() {
        return relationToThePublisher;
    }

    public void setRelationToThePublisher(String relationToThePublisher) {
        this.relationToThePublisher = relationToThePublisher;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }
}
