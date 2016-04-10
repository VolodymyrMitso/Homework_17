package mitso.v.homework_17.fragments.post_fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import mitso.v.homework_17.R;

public class PostViewHolder extends RecyclerView.ViewHolder {

    public TextView mTextView_PostTitle;

    public PostViewHolder(View itemView) {
        super(itemView);

        mTextView_PostTitle = (TextView) itemView.findViewById(R.id.tv_PostTitle_PC);
    }
}