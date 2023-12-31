package com.example.shivaknt.models;

import java.util.List;

public class ChatGptResponseModel {
    private String id;
    private String object;
    private int created;
    private String model;
    private List<Choices> choices;
    private Usage usage;
    public void setId(String id) {
         this.id = id;
     }
     public String getId() {
         return id;
     }

    public void setObject(String object) {
         this.object = object;
     }
     public String getObject() {
         return object;
     }

    public void setCreated(int created) {
         this.created = created;
     }
     public int getCreated() {
         return created;
     }

    public void setModel(String model) {
         this.model = model;
     }
     public String getModel() {
         return model;
     }

    public void setChoices(List<Choices> choices) {
         this.choices = choices;
     }
     public List<Choices> getChoices() {
         return choices;
     }

    public void setUsage(Usage usage) {
         this.usage = usage;
     }
     public Usage getUsage() {
         return usage;
     }
}