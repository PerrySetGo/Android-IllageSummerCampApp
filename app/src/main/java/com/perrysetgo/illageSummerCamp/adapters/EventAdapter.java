package com.perrysetgo.illageSummerCamp.adapters;

import android.app.FragmentManager;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.perrysetgo.illageSummerCamp.Constants;
import com.perrysetgo.illageSummerCamp.R;
import com.perrysetgo.illageSummerCamp.fragments.SignUpFragment;
import com.perrysetgo.illageSummerCamp.models.Event;

public class EventAdapter extends BaseAdapter {
    public static final String TAG = EventAdapter.class.getSimpleName();
    private Context context;
    private ArrayList <Event> mEvents;
    private FragmentManager fm;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private Event event;

    public EventAdapter(Context context, ArrayList<Event> events, FragmentManager fm) {
        this.context = context;
        this.fm = fm;
        mEvents = events;
    }

    @Override
    public int getCount() {
        return mEvents.size();
    }

    @Override
    public Object getItem(int position) {
        return mEvents.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.event_list_item,parent,false);
            holder = new ViewHolder();

            holder.editButton = (ImageButton) convertView.findViewById(R.id.editButton);
            holder.deleteButton = (ImageButton) convertView.findViewById(R.id.deleteButton);
            holder.saveButton = (ImageButton) convertView.findViewById(R.id.saveButton);
            holder.titleLabel = (TextView) convertView.findViewById(R.id.eventTitleLabel);
            holder.dateLabel = (TextView) convertView.findViewById(R.id.eventDateLabel);
            holder.locationLabel = (TextView) convertView.findViewById(R.id.eventLocationLabel);
            holder.descriptionLabel = (TextView) convertView.findViewById(R.id.eventDescriptionLabel);

            convertView.setTag(holder);
        } else{
            holder = (ViewHolder) convertView.getTag(); //what?
        }
        
        event = mEvents.get(position);
        holder.titleLabel.setText(event.getEventTitle());

        SimpleDateFormat startTimeFormat = new SimpleDateFormat("EEE, MM/dd hh:mm a", Locale.US);
        String startDateTime = startTimeFormat.format(event.getEventStartDateTime());
        //END TIME
        SimpleDateFormat endTimeFormat = new SimpleDateFormat("EEE, MM/dd hh:mm a", Locale.US);
        String endDateTime = endTimeFormat.format(event.getEventEndDateTime());

        holder.dateLabel.setText(startDateTime + " to " + endDateTime);
        holder.locationLabel.setText(event.getEventLocation());
        holder.descriptionLabel.setText(event.getEventDescription());


        holder.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null ) {
                    Log.i(TAG, "u ok");
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //get the currently logged in user.
                    String uid = user.getUid(); //check warning

//                    DatabaseReference faveEventRef = FirebaseDatabase
//                            .getInstance()
//                            .getReference(Constants.FIREBASE_CHILD_EVENTS)
//                            .child(event.getPushId());
//
//                    faveEventRef.child("attendees").setValue(uid);


                    Toast.makeText(context, "Event was saved", Toast.LENGTH_LONG).show();
                }
                else {
                    SignUpFragment signupFragment = new SignUpFragment();
                    signupFragment.show(fm, "Sample Fragment");
                }

            }
        });
        return convertView;
    }

    private static class ViewHolder{
        TextView titleLabel;
        TextView dateLabel;
        TextView descriptionLabel;
        TextView locationLabel;
        ImageButton editButton;
        ImageButton deleteButton;
        ImageButton saveButton;
    }


//    private boolean isAuthUser(){
//        authListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                final FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    isAuthed = true;
//                    Log.i(TAG,"user logged in" );
//                }
//            }
//        };
//        return isAuthed;
//    }
}
