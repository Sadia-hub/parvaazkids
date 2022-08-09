package com.example.parvaaz_kids;

public class Drives {
    private String id;
    private String title;
    private String description;
    private String contact;
    private String address;

    private int target;
    private int collectedAmount=0;

    public Drives(){

    }

    public Drives(String title, String description, String contact, String address, int target){
        this.title = title;
        this.description = description;
        this.contact = contact;
        this.address = address;
        this.target = target;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getCollectedAmount() {
        return collectedAmount;
    }

    public void setCollectedAmount(int collectedAmount) {
        this.collectedAmount += collectedAmount;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
