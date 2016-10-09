package koster.tychokoster_pset5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Yvonn on 5-10-2016.
 */

public class ToDoListAdapter extends BaseAdapter {
    private ToDoManager TDManager;
    private Context c;

    // Adapter for the to do lists
    public ToDoListAdapter(Context context, ToDoManager TDManager){
        this.c = context;
        this.TDManager = TDManager;
    }

    @Override
    public int getCount() {
        return TDManager.getSize();
    }

    @Override
    public ToDoList getItem(int position) {
        return TDManager.getToDoList(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lists_item, parent, false);
        }
        TextView text = (TextView) view.findViewById(R.id.listsitem);
        // set text to to the title of the to do list
        text.setText(TDManager.getToDoList(position).getTitle());
        return view;
    }
}
