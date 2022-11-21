package com.example.mentalhealth.positivenotes;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class SS {
    public String name2;
    public String email2;
    public String story;

    public SS() {
    }

    public SS(String name2, String email2, String story) {
        this.name2 = name2;
        this.email2 = email2;
        this.story = story;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }
}
