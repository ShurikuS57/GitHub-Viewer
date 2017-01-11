package com.taptm.shurikus.githubviewer.data;


public class Repo {

    private int id;

    private String name;

    private String html_url;

    private String description;

    private int stargazers_count;

    public Repo(int id, String name, String html_url, String description, int stargazers_count) {
        this.id = id;
        this.name = name;
        this.html_url = html_url;
        this.description = description;
        this.stargazers_count = stargazers_count;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(int stargazers_count) {
        this.stargazers_count = stargazers_count;
    }
}
