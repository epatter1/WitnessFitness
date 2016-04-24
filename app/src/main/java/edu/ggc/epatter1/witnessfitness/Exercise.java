package edu.ggc.epatter1.witnessfitness;

import android.graphics.Bitmap;

/**
 * Created by epatter1 on 4/23/2016.
 */
public class Exercise {


    String name;
    String description;
    Bitmap picture;

    public Exercise (String name, String description) {
        setDescription(description);
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }
}
