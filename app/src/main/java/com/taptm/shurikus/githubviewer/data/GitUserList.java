package com.taptm.shurikus.githubviewer.data;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GitUserList {

    private int totalCount;
    private boolean incompleteResults;
    private List<User> items = new ArrayList<User>();

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public List<User> getItems() {
        return items;
    }

    public void setItems(List<User> items) {
        this.items = items;
    }
}
