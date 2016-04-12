package mitso.v.homework_17.fragments.comment_fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mitso.v.homework_17.R;
import mitso.v.homework_17.api.models.Comment;

public class CommentAdapter extends RecyclerView.Adapter<CommentViewHolder> {

    private ArrayList<Comment>  mCommentList;
    private ICommentHandler     mCommentHandler;

    public CommentAdapter(ArrayList<Comment> albumList) {
        this.mCommentList = albumList;
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, final int position) {
        final Comment comment = mCommentList.get(position);
        holder.getTextView_CommentEmail().setText(comment.getEmail());
        holder.getTextView_CommentEmail().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCommentHandler.commentOnClick(comment);
            }
        });
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_card, parent, false);
        return new CommentViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return mCommentList.size();
    }

    public void setCommentHandler(ICommentHandler commentHandler) {
        this.mCommentHandler = commentHandler;
    }

    public void releaseCommentHandler() {
        this.mCommentHandler = null;
    }

    public boolean checkCommentHandler() {
        return mCommentHandler != null;
    }
}
