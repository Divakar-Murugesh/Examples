package com.example.myapplication;

public class NotificationModel {

    private Integer id;
    private String notificationText;
    private String timeStamp;

    public NotificationModel(Integer id, String notificationText, String timeStamp) {
        this.id = id;
        this.notificationText = notificationText;
        this.timeStamp = timeStamp;
    }

    public Integer getId() {
        return id;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
}