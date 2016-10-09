package koster.tychokoster_pset5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ToDoManager TDManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TDManager = ToDoManager.getInstance();
        getListView();
    }

    // Writes the todos at that time to the storage file, when stopped.
    @Override
    protected void onStop() {
        TDManager.writeToDos(this);
        super.onStop();
    }

    // Writes the todos at the time of destroy to the data file.
    protected void onDestroy() {
        TDManager.writeToDos(this);
        super.onRestart();
    }

    // Reads the lists and shows them for the user to interact with.
    private void getListView() {
        TDManager.readToDos(this);
        final ListView todolist = (ListView) findViewById(R.id.itemLists);
        final ToDoListAdapter listadapter = new ToDoListAdapter(this, TDManager);
        ToDoList list = TDManager.getToDoList(TDManager.getCurrentTab());
        ToDoAdapter todoadapter = new ToDoAdapter(this, list);
        ListView todo = (ListView) findViewById(R.id.itemList);
        todo.setAdapter(todoadapter);
        todolist.setAdapter(listadapter);
        // On click for the items in the listview
        todolist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                todolist.setSelector(android.R.color.darker_gray);
                TDManager.setCurrenttab(position);
                getToDoList(position);
            }
        });
        // Long click listener to remove the items in the listview
        todolist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                todolist.setSelector(android.R.color.white);
                TDManager.deleteToDoList(position);
                listadapter.notifyDataSetChanged();
                TDManager.writeToDos(getApplicationContext());
                Toast.makeText(getApplicationContext(), "Item removed", Toast.LENGTH_SHORT ).show();
                getToDoList(position);
                return true;
            }
        });
    }

    // Retrieves the to do list of the selected to do list and shows it for the user to interact with
    private void getToDoList(int position) {
        final ListView todo = (ListView) findViewById(R.id.itemList);
        Button btn = (Button) findViewById(R.id.btnNewItem);
        final EditText txt = (EditText) findViewById(R.id.textNewItem);
        // shows the add button and edittext when clicked on a list
        btn.setVisibility(View.VISIBLE);
        txt.setVisibility(View.VISIBLE);
        TDManager.readToDos(this);
        final ToDoList list = TDManager.getToDoList(position);
        // if there is no list the button and edittext go away again
        if(list == null){
            todo.setAdapter(null);
            btn.setVisibility(View.GONE);
            txt.setVisibility(View.GONE);
        }
        else {
            final ToDoAdapter todoadapter = new ToDoAdapter(this, list);
            // On click for the button to add in the to do list
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String item_title = txt.getText().toString();
                    ToDoItem item = new ToDoItem(item_title, false);
                    list.addToDoItem(item);
                    txt.setText("");
                    TDManager.writeToDos(getApplicationContext());
                    todoadapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), "Item added", Toast.LENGTH_SHORT).show();
                }
            });
            // Long click to remove the items from the to do list
            todo.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    list.deleteToDoItem(position);
                    TDManager.updateTodoList(position, list);
                    TDManager.writeToDos(getApplicationContext());
                    todoadapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), "Item Removed", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
            todo.setAdapter(todoadapter);
        }
    }

    // Adds new to do list with new item already in it
    public void addList(View v) {
        EditText newlist = (EditText) findViewById(R.id.textNewList);
        String list_title = newlist.getText().toString();
        if(list_title.equals("")){
            Toast.makeText(this, "Empty list", Toast.LENGTH_SHORT).show();
        }
        else {
            ArrayList<ToDoItem> items = new ArrayList<>();
            items.add(new ToDoItem("Item", false));
            ToDoList list = new ToDoList(items, list_title);
            TDManager.addToDoList(list);
            TDManager.writeToDos(this);
            newlist.setText("");
            Toast.makeText(this, "Item added", Toast.LENGTH_SHORT).show();
            getListView();
        }
    }

    // Sets the boolean of completed of the item in the list to false or true depending on the current state of completed
    public void setChecked(int position){
        ToDoList list = TDManager.getToDoList(TDManager.getCurrentTab());
        ListView todo = (ListView) findViewById(R.id.itemList);
        ToDoAdapter todoadapter = new ToDoAdapter(this, list);
        if(list.getToDoItem(position).getCompleted()){
            list.getToDoItem(position).setCompletedFalse();
        }
        else {
            list.getToDoItem(position).setCompletedTrue();
        }
        TDManager.updateTodoList(TDManager.getCurrentTab(), list);
        TDManager.writeToDos(this);
        todoadapter.notifyDataSetChanged();
        todo.setAdapter(todoadapter);
    }
}
