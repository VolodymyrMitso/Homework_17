package mitso.v.homework_17.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;

import mitso.v.homework_17.MainActivity;

public class BaseFragment extends Fragment {

    protected MainActivity mMainActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mMainActivity = (MainActivity) context;
    }
}