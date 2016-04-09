package mitso.v.homework_17.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mitso.v.homework_17.R;
import mitso.v.homework_17.api.Api;
import mitso.v.homework_17.api.interfaces.ConnectCallback;
import mitso.v.homework_17.api.models.Todo;
import mitso.v.homework_17.api.response.TodoListResponse;

public class TodoFragment extends BaseFragment {

    private static final String LOG_TAG = "TODO FRAGMENT";

    private TextView mTextView_Todo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.todo_fragment, container, false);

        int id =  getArguments().getInt("to id");
        mTextView_Todo = (TextView) rootView.findViewById(R.id.tv_Todo_IF);
        final StringBuilder stringBuilder = new StringBuilder();

        Api.getTodosByUser(id, new ConnectCallback() {
            @Override
            public void onSuccess(Object object) {
                TodoListResponse todoListResponse = (TodoListResponse) object;
                ArrayList<Todo> todoArrayList = todoListResponse.getTodos();

                Log.e(LOG_TAG, "todoArrayList.size:" + todoArrayList.size());

                Log.e(LOG_TAG, "todoArrayList.first:" + todoArrayList.get(0));
                Log.e(LOG_TAG, "todoArrayList.last:" + todoArrayList.get(19));

                for (int i = 0; i < todoArrayList.size(); i++) {
                    stringBuilder.append(todoArrayList.get(i).toString());
                }

                mTextView_Todo.setText(stringBuilder);
            }

            @Override
            public void onFailure(Throwable throwable, String errorMessage) {

            }
        });

        return rootView;
    }
}