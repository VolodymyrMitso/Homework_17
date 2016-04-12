package mitso.v.homework_17;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import mitso.v.homework_17.fragments.users_fragment.UsersFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_FragmentContainer_AM, new UsersFragment())
                .commitAllowingStateLoss();
    }
}