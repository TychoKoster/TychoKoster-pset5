package koster.tychokoster_pset5;

import java.io.Serializable;

public class ToDoItem implements Serializable {
    String title;
    boolean completed;

    // To Do item defined
    public ToDoItem(String title, Boolean completed){
        this.title = title;
        this.completed = completed;
    }

    // Sets item title
    protected void setTitle(String newtitle) {
        title = newtitle;
    }

    // Retrieves title
    protected String getTitle() {
        return title;
    }

    // Sets completed true
    protected void setCompletedTrue() {
        completed = true;
    }

    // Sets completed false
    protected void setCompletedFalse() {
        completed = false;
    }

    // Retrieves completed state
    protected boolean getCompleted() {
        return completed;
    }
}
