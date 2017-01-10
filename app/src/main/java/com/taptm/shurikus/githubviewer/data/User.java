package com.taptm.shurikus.githubviewer.data;

public class User {

    private int id;

    private String login;

    private String avatar_url;

    private String url;

    private String html_url;

    private String repos_url;


    public User(int id, String login, String avatar_url, String url, String html_url, String repos_url) {
        this.id = id;
        this.login = login;
        this.avatar_url = avatar_url;
        this.url = url;
        this.html_url = html_url;
        this.repos_url = repos_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getRepos_url() {
        return repos_url;
    }

    public void setRepos_url(String repos_url) {
        this.repos_url = repos_url;
    }
}
