package com.codezilla.osaid.palstreet;

/**
 * Created by osaid on 12/24/2017.
 */

public class News {
    private int id;
    private String title;
    private String description;
    private double xpoint;
    private double ypoint;
    private String date;
    private int type;

    public News(int id, String title, String description, double xpoint, double ypoint, String date, int type) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.xpoint = xpoint;
        this.ypoint = ypoint;
        this.date = date;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getXpoint() {
        return xpoint;
    }

    public void setXpoint(double xpoint) {
        this.xpoint = xpoint;
    }

    public double getYpoint() {
        return ypoint;
    }

    public void setYpoint(double ypoint) {
        this.ypoint = ypoint;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
