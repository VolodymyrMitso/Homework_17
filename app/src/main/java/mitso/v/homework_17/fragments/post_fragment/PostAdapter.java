package mitso.v.homework_17.fragments.post_fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mitso.v.homework_17.R;
import mitso.v.homework_17.api.models.Post;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {

    private ArrayList<Post>     mPostList;
    private IPostHandler        mPostHandler;

    public PostAdapter(ArrayList<Post> postList) {
        this.mPostList = postList;
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, final int position) {
        final Post post = mPostList.get(position);
        holder.getTextView_PostTitle().setText(post.getTitle());
        holder.getTextView_PostTitle().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPostHandler.postOnClick(post);
            }
        });
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_card, parent, false);
        return new PostViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }

    public void setPostHandler(IPostHandler postHandler) {
        this.mPostHandler = postHandler;
    }

    public void releasePostHandler() {
        this.mPostHandler = null;
    }
}