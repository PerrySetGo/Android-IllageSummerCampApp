package com.perrysetgo.illageSummerCamp.ui;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.perrysetgo.illageSummerCamp.BaseActivity;
import com.perrysetgo.illageSummerCamp.R;

public class ContactActivity extends BaseActivity {
    public static final String TAG = ContactActivity.class.getSimpleName();

    @Bind(R.id.viewWebsiteButton) Button mViewWebSiteButton;
    @Bind(R.id.sendNoteForm) Button mSendNoteForm;
    @Bind(R.id.emailContact) EditText mEmailContact;
    @Bind(R.id.nameContact) EditText mNameContact;
    @Bind(R.id.messageContact) EditText mMessageContact;
    @Bind(R.id.contactFormIntro) TextView mContactFormIntro;
    @Bind(R.id.formSubmitButton) Button mFormSubmitButton;

    String recipientEmail = "lectique@gmail.com";
    String messageSubject = "Illage Summer Camp 2016";


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

        mSendNoteForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewWebSiteButton.setVisibility(View.INVISIBLE);
                mSendNoteForm.setVisibility(View.INVISIBLE);

                mContactFormIntro.setVisibility(View.VISIBLE);
                mEmailContact.setVisibility(View.VISIBLE);
                mNameContact.setVisibility(View.VISIBLE);
                mMessageContact.setVisibility(View.VISIBLE);
                mFormSubmitButton.setVisibility(View.VISIBLE);
            }
        });

        mFormSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mNameContact.getText().toString();
                String email = mEmailContact.getText().toString();
                String message = mMessageContact.getText().toString();
                String formattedMessage = buildMessage(name, email, message);
                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                emailIntent.setType("message/rfc822");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipientEmail});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, messageSubject);
                emailIntent.putExtra(Intent.EXTRA_TEXT, formattedMessage);

                PackageManager packageManager = getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(emailIntent, 0);
                boolean isIntentSafe = activities.size() > 0;

                if (isIntentSafe) {
                    startActivity(emailIntent);
                } else {
                    Toast.makeText(getApplicationContext(), "Your email is not configured. Please configure it first, then try again.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_contact:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //static
    private String buildMessage(String name, String email, String message) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm", Locale.US);
        Calendar cal = Calendar.getInstance();
        String currentDateTime = dateFormat.format(cal.getTime());
        return "Message from: " + name + "\n\n"
                + "At: " + currentDateTime + "\n\n"
                + "From: " + email + "\n\n"
                + message;
    }


}