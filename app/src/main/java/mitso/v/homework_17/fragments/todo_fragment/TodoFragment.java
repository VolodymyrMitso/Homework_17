package mitso.v.homework_17.fragments.todo_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mitso.v.homework_17.R;
import mitso.v.homework_17.api.Api;
import mitso.v.homework_17.api.interfaces.ConnectCallback;
import mitso.v.homework_17.api.models.Todo;
import mitso.v.homework_17.api.response.TodoListResponse;
import mitso.v.homework_17.fragments.BaseFragment;

public class TodoFragment extends BaseFragment {

    private static final String LOG_TAG = "TODO FRAGMENT";

    private RecyclerView        mRecyclerView_Todo;
    private TodoAdapter         mTodoAdapter;
    private ArrayList<Todo>     mTodooList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.todo_fragment, container, false);

        int id =  getArguments().getInt("id");

        Api.getTodosByUser(id, new ConnectCallback() {
            @Override
            public void onSuccess(Object object) {
                TodoListResponse todoListResponse = (TodoListResponse) object;
                ArrayList<Todo> todoArrayList = todoListResponse.getTodos();
                mTodooList = todoArrayList;

                Log.e(LOG_TAG, "todoArrayList.size:" + todoArrayList.size());

                Log.e(LOG_TAG, todoArrayList.get(0).toString());
                Log.e(LOG_TAG, todoArrayList.get(19).toString());

                mRecyclerView_Todo = (RecyclerView) rootView.findViewById(R.id.rv_Todos_TF);
                mTodoAdapter = new TodoAdapter(mTodooList);
                mRecyclerView_Todo.setAdapter(mTodoAdapter);
                mRecyclerView_Todo.setLayoutManager(new GridLayoutManager(mMainActivity, 1));
                int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.d_size_10dp);
                mRecyclerView_Todo.addItemDecoration(new SpacingDecoration(spacingInPixels));
            }

            @Override
            public void onFailure(Throwable throwable, String errorMessage) {

            }
        });

        return rootView;
    }
}