package me.naman.todoapp.todoapp;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class todoListSchema {
    private final long id;
    private String name;
    private ArrayList< todoSchema > todoList;
    private AtomicLong taskCount;

    public todoListSchema(long id, String name) {
        this.id = id;
        this.setName(name);
        this.todoList = new ArrayList < todoSchema >  ();
        this.taskCount = new AtomicLong();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addTask(String task){
        this.todoList.add(new todoSchema(this.taskCount.incrementAndGet(), task));
    }

    public ArrayList<todoSchema> getTodoList(){
        return this.todoList;
    }

}