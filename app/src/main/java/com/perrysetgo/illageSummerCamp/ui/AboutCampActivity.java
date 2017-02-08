package com.perrysetgo.illageSummerCamp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.perrysetgo.illageSummerCamp.BaseActivity;
import com.perrysetgo.illageSummerCamp.R;

public class AboutCampActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_camp);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id)
        {
            case R.id.action_about: return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
