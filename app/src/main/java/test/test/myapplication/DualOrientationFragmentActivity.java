package test.test.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import test.test.myapplication.fragment.DetailsFragment;
import test.test.myapplication.fragment.FragmentList;


public class DualOrientationFragmentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dual_orientation_fragment);

        int orient = this.getResources().getConfiguration().orientation;
        if (orient ==1){
            final Fragment fragment = new FragmentList();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.frame_one, fragment);
            ft.commitAllowingStateLoss();
        }else{
            final Fragment fragment = new DetailsFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.frame_one, fragment);
            ft.commitAllowingStateLoss();
        }
    }


}
