package co.mobilemakers.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
* Created by Juan on 04/02/2015.
*/
public class ToDoFragment extends ListFragment {
    private static final int REQUEST_CODE_CREATE_TASK = 0;
    ListView mListViewTasks;
    ArrayAdapter<Task> mAdapter;
    public ToDoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_todo_list, container, false);


        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        prepareListView();
    }

    private void prepareListView() {
        List<Task> entries = new ArrayList<>();
        mAdapter = new TaskAdapter(getActivity(), entries);
        setListAdapter(mAdapter);
        mListViewTasks = getListView();
        mListViewTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Le clickeaste", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_todo, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        boolean handle = false;

        switch (itemId) {
            case R.id.menu_todo:
                Intent intent = new Intent(getActivity(), CreateTaskActivity.class);
                startActivityForResult(intent,REQUEST_CODE_CREATE_TASK);
                handle = true;
            break;
        }

        if(!handle) {
            handle = super.onOptionsItemSelected(item);
        }

        return handle;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            Toast.makeText(getActivity(), data.getExtras().getString("taskTitle"), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getActivity(), "Nada, Sorry :(", Toast.LENGTH_LONG).show();
        }
    }
}
