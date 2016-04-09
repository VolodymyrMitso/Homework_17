package mitso.v.homework_17.fragments.users_fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import mitso.v.homework_17.R;

public class UserViewHolder  extends RecyclerView.ViewHolder {

    public TextView mTextView_UserName;

    public UserViewHolder(View itemView) {
        super(itemView);

        mTextView_UserName = (TextView) itemView.findViewById(R.id.tv_UserName_UC);
    }
}