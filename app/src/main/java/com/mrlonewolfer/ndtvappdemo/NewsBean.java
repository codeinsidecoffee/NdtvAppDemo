package com.mrlonewolfer.ndtvappdemo;

import androidx.annotation.NonNull;

public class NewsBean {

    private String title, link, description, StoryImage;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStoryImage() {
        return StoryImage;
    }

    public void setStoryImage(String storyImage) {
        StoryImage = storyImage;
    }

    @Override
    public String toString() {
        return "NewsBean{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", StoryImage='" + StoryImage + '\'' +
                '}';
    }
}
