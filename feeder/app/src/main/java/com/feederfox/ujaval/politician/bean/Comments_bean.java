package com.feederfox.ujaval.politician.bean;

public class Comments_bean {

     int Comments_id;
     int FK_Comments_PID;
     String Comments_image;
     String Comments_name;
     String Comments_user;
     String Comments_uploaded_on;
     String Comments_comment;
     String Comments_ratings;

    public int getComments_id() {
        return Comments_id;
    }

    public void setComments_id(int comments_id) {
        Comments_id = comments_id;
    }

    public int getFK_Comments_PID() {
        return FK_Comments_PID;
    }

    public void setFK_Comments_PID(int FK_Comments_PID) {
        this.FK_Comments_PID = FK_Comments_PID;
    }

    public String getComments_image() {
        return Comments_image;
    }

    public String getComments_name() {
        return Comments_name;
    }

    public void setComments_name(String comments_name) {
        Comments_name = comments_name;
    }

    public void setComments_image(String comments_image) {
        Comments_image = comments_image;
    }

    public String getComments_user() {
        return Comments_user;
    }

    public void setComments_user(String comments_user) {
        Comments_user = comments_user;
    }

    public String getComments_uploaded_on() {
        return Comments_uploaded_on;
    }

    public void setComments_uploaded_on(String comments_uploaded_on) {
        Comments_uploaded_on = comments_uploaded_on;
    }

    public String getComments_comment() {
        return Comments_comment;
    }

    public void setComments_comment(String comments_comment) {
        Comments_comment = comments_comment;
    }

    public String getComments_ratings() {
        return Comments_ratings;
    }

    public void setComments_ratings(String comments_ratings) {
        Comments_ratings = comments_ratings;
    }
}
