package mitso.v.homework_17.fragments.todo_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import mitso.v.homework_17.R;
import mitso.v.homework_17.api.Api;
import mitso.v.homework_17.api.interfaces.ConnectCallback;
import mitso.v.homework_17.api.models.Todo;
import mitso.v.homework_17.api.response.TodoListResponse;
import mitso.v.homework_17.fragments.BaseFragment;
import mitso.v.homework_17.fragments.utils.CheckConnection;
import mitso.v.homework_17.fragments.utils.Constants;
import mitso.v.homework_17.fragments.utils.SpacingDecoration;

public class TodoFragment extends BaseFragment {

    private final String        LOG_TAG = Constants.TODO_FRAGMENT_TAG;

    private RecyclerView        mRecyclerView_Todo;
    private TodoAdapter         mTodoAdapter;
    private ArrayList<Todo>     mTodooList;

    private Integer userId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.todo_fragment, container, false);

        try {
            userId = getArguments().getInt(Constants.USER_ID_BUNDLE_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(mMainActivity, mMainActivity.getResources().getString(R.string.s_error), Toast.LENGTH_SHORT).show();
        }

        if (userId != null && userId != 0) {

            if (CheckConnection.checkConnection(mMainActivity)) {

                Toast.makeText(mMainActivity, mMainActivity.getResources().getString(R.string.s_connecting), Toast.LENGTH_SHORT).show();

                Api.getTodosByUser(userId, new ConnectCallback() {
                    @Override
                    public void onSuccess(Object object) {

                        TodoListResponse todoListResponse = (TodoListResponse) object;
                        ArrayList<Todo> todoArrayList = todoListResponse.getTodos();
                        mTodooList = todoArrayList;

                        Log.e(LOG_TAG, String.valueOf(todoArrayList.size()));
                        Log.e(LOG_TAG, todoArrayList.get(0).toString());
                        Log.e(LOG_TAG, todoArrayList.get(todoArrayList.size() - 1).toString());

                        mRecyclerView_Todo = (RecyclerView) rootView.findViewById(R.id.rv_Todos_TF);
                        mTodoAdapter = new TodoAdapter(mTodooList);
                        mRecyclerView_Todo.setAdapter(mTodoAdapter);
                        mRecyclerView_Todo.setLayoutManager(new GridLayoutManager(mMainActivity, 1));
                        int spacingInPixels = mMainActivity.getResources().getDimensionPixelSize(R.dimen.d_size_10dp);
                        mRecyclerView_Todo.addItemDecoration(new SpacingDecoration(spacingInPixels));

                        Log.d(LOG_TAG, "onSuccess");
                        Toast.makeText(mMainActivity, mMainActivity.getResources().getString(R.string.s_success), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.d(LOG_TAG, "onFailure");
                        Toast.makeText(mMainActivity, mMainActivity.getResources().getString(R.string.s_failure), Toast.LENGTH_SHORT).show();
                        Toast.makeText(mMainActivity, mMainActivity.getResources().getString(R.string.s_error), Toast.LENGTH_SHORT).show();
                    }
                });

            } else
                Toast.makeText(mMainActivity, mMainActivity.getResources().getString(R.string.s_no_connection), Toast.LENGTH_SHORT).show();

        } else
            Toast.makeText(mMainActivity, mMainActivity.getResources().getString(R.string.s_error), Toast.LENGTH_SHORT).show();

        return rootView;
    }
}