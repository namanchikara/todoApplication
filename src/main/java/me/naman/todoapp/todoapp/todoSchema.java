package me.naman.todoapp.todoapp;

public class todoSchema {

    private final long id;
    private String task;
    private boolean isCompleted;

    public todoSchema(long id, String task) {
        this.id = id;
        this.setTask(task);
        this.setCompleted(false);
    }

    public long getId() {
        return id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
  
  }