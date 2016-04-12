package mitso.v.homework_17.fragments.todo_fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import mitso.v.homework_17.R;

public class TodoViewHolder extends RecyclerView.ViewHolder {

    private TextView mTextView_TodoInfo;

    public TodoViewHolder(View itemView) {
        super(itemView);

        mTextView_TodoInfo = (TextView) itemView.findViewById(R.id.tv_TodoInfo_TC);
    }

    public TextView getTextView_TodoInfo() {
        return mTextView_TodoInfo;
    }
}