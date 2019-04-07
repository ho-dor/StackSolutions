package com.example.stacksolutions;

public class Questions{

    private String owner;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;
    private String link;
    private String image;
    private String view_count;
    private String answer_count;
    private String last_activity;
    private String creation;
    private String score;

    public Questions(String owner, String title, String link, String image, String view_count, String answer_count, String last_activity, String creation, String score) {
        this.owner = owner;
        this.title = title;
        this.link = link;
        this.image = image;
        this.view_count = view_count;
        this.answer_count = answer_count;
        this.last_activity = last_activity;
        this.creation = creation;
        this.score = score;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getView_count() {
        return view_count;
    }

    public void setView_count(String view_count) {
        this.view_count = view_count;
    }

    public String getAnswer_count() {
        return answer_count;
    }

    public void setAnswer_count(String answer_count) {
        this.answer_count = answer_count;
    }

    public String getLast_activity() {
        return last_activity;
    }

    public void setLast_activity(String last_activity) {
        this.last_activity = last_activity;
    }

    public String getCreation() {
        return creation;
    }

    public void setCreation(String creation) {
        this.creation = creation;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
