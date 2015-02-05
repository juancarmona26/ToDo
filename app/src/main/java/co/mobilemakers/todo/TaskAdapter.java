package co.mobilemakers.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Juan on 04/02/2015.
 */
public class TaskAdapter extends ArrayAdapter<Task> {
    Context mContext;
    List<Task> mTasks;

    public TaskAdapter (Context context, List<Task> tasks) {
        super(context, R.layout.list_item_entry, tasks);
        mContext = context;
        mTasks = tasks;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = displayView(position, convertView, parent);
        return rowView;
    }

    private View displayView(int position, View convertView, ViewGroup parent) {
        View rowView;
        if(convertView != null) {
            rowView = convertView;
        } else {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.list_item_entry, parent, false);
        }
        if(rowView != null) {
            TextView textViewTaskTitle = (TextView) rowView.findViewById(R.id.text_view_task_title);
            textViewTaskTitle.setText(mTasks.get(position).getTittle());
        }
        return rowView;
    }
}
