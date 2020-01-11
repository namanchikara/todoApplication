package me.naman.todoapp.todoapp;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class todoListSchema {
    private final long id;
    private String name;
    private ArrayList<todoSchema> todoList;
    private AtomicLong taskCount;
    private boolean isDeleted;

    public todoListSchema(long id, String name) {
        this.id = id;
        this.setName(name);
        this.todoList = new ArrayList<todoSchema>();
        this.taskCount = new AtomicLong();
        this.setDeleted(false);
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        if(isDeleted){
            this.todoList.clear();
        }
        this.isDeleted = isDeleted;
    }

    public todoSchema findTodoById(int fid) throws todoException{
        for(int i = 0; i < this.todoList.size(); i++){
            if (todoList.get(i).getId() == fid){
                return todoList.get(i);
            }
        }

        throw new todoException("ToDo doesn't exist");
    }

    public void removeTodoById(int fid) throws todoException{
        for(int i = 0; i < this.todoList.size(); i++){
            if (todoList.get(i).getId() == fid){
                todoList.remove(i);
                return;
            }
        }

        throw new todoException("ToDo doesn't exist");    
    }

    public boolean isValidTodoId(int fid){
        for(int i = 0; i < this.todoList.size(); i++){
            if (todoList.get(i).getId() == fid){
                return true;
            }
        }
        return false;
    }

}