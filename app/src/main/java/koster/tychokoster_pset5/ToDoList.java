package koster.tychokoster_pset5;

import java.io.Serializable;
import java.util.ArrayList;

public class ToDoList implements Serializable {
    private ArrayList<ToDoItem> ToDoList = new ArrayList<>();
    private String title;

    // To Do List defined
    public ToDoList(ArrayList<ToDoItem> ToDoList, String title){
        this.ToDoList = ToDoList;
        this.title = title;
    }

    // Adds to do item to list
    public void addToDoItem(ToDoItem item) {
        ToDoList.add(item);
    }

    // Deletes to do item from list
    public void deleteToDoItem(int position) {
        ToDoList.remove(position);
    }

    // Gets the to do item on a certain position
    public ToDoItem getToDoItem(int position) {
        return ToDoList.get(position);
    }

    // Sets title of a to do list
    public void setTitle(String newtitle) {
        title = newtitle;
    }

    // Retrieves size of the To Do list
    public int getSize() {return ToDoList.size();}

    // Retrieves the title of the to do list
    public String getTitle() {
        return title;
    }
}
