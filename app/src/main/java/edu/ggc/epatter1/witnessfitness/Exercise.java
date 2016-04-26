package edu.ggc.epatter1.witnessfitness;

import android.graphics.Bitmap;

/**
 * Created by epatter1 on 4/23/2016.
 */
public class Exercise {


    String name;
    String description;
    int picture;

    public Exercise (String name, String description, int picture) {
        setDescription(description);
        setName(name);
        setPicture(picture);
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

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }
}
