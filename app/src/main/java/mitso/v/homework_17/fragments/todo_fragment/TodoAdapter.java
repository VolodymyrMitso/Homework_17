package mitso.v.homework_17.fragments.todo_fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mitso.v.homework_17.R;
import mitso.v.homework_17.api.models.Todo;

public class TodoAdapter extends RecyclerView.Adapter<TodoViewHolder> {

    private ArrayList<Todo> mTodoList;

    public TodoAdapter(ArrayList<Todo> photoList) {
        this.mTodoList = photoList;
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, final int position) {
        final Todo todo = mTodoList.get(position);

        holder.mTextView_TodoInfo.setText(todo.toString());
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_card, parent, false);
        return new TodoViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return mTodoList.size();
    }
}
