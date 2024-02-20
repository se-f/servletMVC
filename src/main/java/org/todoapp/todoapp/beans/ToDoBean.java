package org.todoapp.todoapp.beans;

public class ToDoBean {
    private String content;
    private String user;
    private String isComplete;

    public ToDoBean(String content, String user, String isComplete) {
        this.content = content;
        this.user = user;
        this.isComplete = isComplete;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(String isComplete) {
        this.isComplete = isComplete;
    }
}