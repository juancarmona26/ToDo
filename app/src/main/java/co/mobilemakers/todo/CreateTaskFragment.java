package co.mobilemakers.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateTaskFragment extends Fragment {

    private Button mButtonDone;
    private EditText mEditTextTaskTitle;
    public EventListener mEvenListener;
    public interface EventListener {
        public void onDoneButtonClicked();
    }
    public CreateTaskFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_task, container, false);
        mButtonDone = (Button) rootView.findViewById(R.id.button_done);
        mEditTextTaskTitle = (EditText) rootView.findViewById(R.id.edit_text_task_title);
        mButtonDone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Activity activity = getActivity();
                if(!TextUtils.isEmpty(mEditTextTaskTitle.getText())) {
                    Intent intent = new Intent();
                    intent.putExtra("taskTitle", mEditTextTaskTitle.getText().toString());
                    activity.setResult(Activity.RESULT_OK, intent);
                    activity.finish();
                } else {
                    Toast.makeText(activity, "Please Enter a title of the task", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mEvenListener = (EventListener) activity;
        } catch (Exception e) {

        }
    }
}
