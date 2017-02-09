package com.perrysetgo.illageSummerCamp.ui;


import android.os.Bundle;
import android.view.MenuItem;
import com.perrysetgo.illageSummerCamp.BaseActivity;
import com.perrysetgo.illageSummerCamp.R;

public class MainActivity extends BaseActivity {

//    String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.action_main: return true;
        }

        return super.onOptionsItemSelected(item);
    }
}