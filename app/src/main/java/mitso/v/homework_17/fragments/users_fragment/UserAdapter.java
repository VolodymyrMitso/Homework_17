package mitso.v.homework_17.fragments.users_fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mitso.v.homework_17.R;
import mitso.v.homework_17.api.models.User;
import mitso.v.homework_17.fragments.interfaces.IUserHandler;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private ArrayList<User> mUserList;
    private IUserHandler mUserHandler;

    public UserAdapter(ArrayList<User> mUserList) {
        this.mUserList = mUserList;
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, final int position) {
        final User user = mUserList.get(position);
        holder.mTextView_UserName.setText(user.getName());
        holder.mTextView_UserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserHandler.userOnClick(position);
            }
        });
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_card, parent, false);
        return new UserViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    public void setIUserHandler(IUserHandler mIUserHandler) {
        this.mUserHandler = mIUserHandler;
    }

    public void releaseIUserHAndler() {
        this.mUserHandler = null;
    }
}
