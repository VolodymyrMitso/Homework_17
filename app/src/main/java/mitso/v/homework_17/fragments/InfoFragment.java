package mitso.v.homework_17.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import mitso.v.homework_17.R;

public class InfoFragment extends BaseFragment implements View.OnClickListener {

    private TextView mTextView_UserInfo;
    private Button mButton_ToDo;

    private int id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.info_fragment, container, false);

        String string =  getArguments().getString("info");
        int id =  getArguments().getInt("id");
        this.id = id;
        mTextView_UserInfo = (TextView) rootView.findViewById(R.id.tv_UserInfo_IF);
        mTextView_UserInfo.setText(string);

        Toast.makeText(mMainActivity, String.valueOf(id), Toast.LENGTH_SHORT).show();

        mButton_ToDo = (Button) rootView.findViewById(R.id.btn_Todo_IF);
        mButton_ToDo.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Todo_IF:

                TodoFragment todoFragment = new TodoFragment();
                Bundle bundle = new Bundle();

                bundle.putInt("to id", id);
                todoFragment.setArguments(bundle);

                mMainActivity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fl_FragmentContainer_AM, todoFragment)
                        .addToBackStack(null)
                        .commitAllowingStateLoss();

                break;
        }
    }
}
