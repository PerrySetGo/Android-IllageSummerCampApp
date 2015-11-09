package com.example.guest.illageSummerCamp.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.guest.illageSummerCamp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ContactActivity extends AppCompatActivity {
    @Bind(R.id.viewWebsiteButton) Button mViewWebSiteButton;
    @Bind(R.id.sendNoteForm) Button mSendNoteForm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ButterKnife.bind(this);

        mViewWebSiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse("http://www.portlandputsout.com");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(webIntent);
            }
        });

    }
}
