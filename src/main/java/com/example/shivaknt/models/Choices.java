package com.example.shivaknt.models;

public class Choices {

    private int index;
    private Message message;
    private String finishReason;
    public void setIndex(int index) {
         this.index = index;
     }
     public int getIndex() {
         return index;
     }

    public void setMessage(Message message) {
         this.message = message;
     }
     public Message getMessage() {
         return message;
     }

    public void setFinishReason(String finishReason) {
         this.finishReason = finishReason;
     }
     public String getFinishReason() {
         return finishReason;
     }

}