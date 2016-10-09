package koster.tychokoster_pset5;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

// Adapter for a to do list
public class ToDoAdapter extends BaseAdapter {
    private ToDoList list;
    private Context c;

    public ToDoAdapter(Context context, ToDoList list){
        this.c = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.getSize();
    }

    @Override
    public ToDoItem getItem(int position) {
        return list.getToDoItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item, null);
        }
        TextView text = (TextView) view.findViewById(R.id.listItem);
        text.setText(list.getToDoItem(position).getTitle());
        ImageView check = (ImageView) view.findViewById(R.id.check);
        final MainActivity main = (MainActivity) c;
        // Sets a strikethrough text if the to do item is checked completed
        if(list.getToDoItem(position).getCompleted()){
            text.setPaintFlags(text.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else {
            text.setPaintFlags(text.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
        }
        // On click for the check image to change the state of completed
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.setChecked(position);
            }
        });
        return view;
    }

}
