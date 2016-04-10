package mitso.v.homework_17.fragments.comment_fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import mitso.v.homework_17.R;

public class CommentViewHolder extends RecyclerView.ViewHolder {

    public TextView mTextView_CommentEmail;

    public CommentViewHolder(View itemView) {
        super(itemView);

        mTextView_CommentEmail = (TextView) itemView.findViewById(R.id.tv_CommentEmail_CC);
    }
}