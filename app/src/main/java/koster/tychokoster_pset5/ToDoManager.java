package koster.tychokoster_pset5;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yvonn on 5-10-2016.
 */

public class ToDoManager {
    private static ToDoManager instance = null;
    private ArrayList<ToDoList> todolists = new ArrayList<ToDoList>();
    private FileInputStream input;
    private FileOutputStream output;
    private String file = "todos.data";
    private ObjectInputStream object_in;
    private ObjectOutputStream object_out;
    private int currenttab;

    // Creates To Do manager
    protected ToDoManager() {}

    // Gets the instance of the To Do manager
    public static ToDoManager getInstance() {
        if(instance == null) {
            instance = new ToDoManager();
        }
        return instance;
    }

    // Adds To Do list to manager
    public boolean addToDoList(ToDoList list) {
        todolists.add(list);
        return true;
    }

    // Deletes a To Do list at a certain position
    public void deleteToDoList(int position) {
        todolists.remove(position);
    }

    // Updates the To Do list if a change is made
    public boolean updateTodoList(int position, ToDoList todolist)
    {
        // Checks if the todolist is not empty and if the position is in the todolist
        if(position < todolists.size() && todolists.size() > 0)
        {
            todolists.set(position, todolist);
            return true;
        }
        return false;
    }

    // Retrieves the To Do list at a certain position in the manager
    public ToDoList getToDoList(int position) {
        // Checks if the todolist is not empty and if the position is in the todolist
        if(todolists.size() > 0 && position < todolists.size())
        {
            return todolists.get(position);
        }
        return null;
    }

    public int getSize() {
        return todolists.size();
    }

    // Reads all the current To Do list in the manager from the data file
    public void readToDos(Context context) {
        try {
            input = context.openFileInput(file);
            object_in = new ObjectInputStream(input);
            todolists = (ArrayList<ToDoList>) object_in.readObject();
            input.close();
            object_in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Writes all the current To Do lists to the data file
    public void writeToDos(Context context) {
        try {
            output = context.openFileOutput(file, Context.MODE_PRIVATE);
            object_out = new ObjectOutputStream(output);
            object_out.writeObject(todolists);
            output.close();
            object_out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Retrieves the current selected list
    public int getCurrentTab() {
        return currenttab;
    }

    // Sets the current selected list
    public void setCurrenttab(int pos) {
        currenttab = pos;
    }
}
