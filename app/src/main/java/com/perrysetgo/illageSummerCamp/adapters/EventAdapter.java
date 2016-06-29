package com.perrysetgo.illageSummerCamp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

import com.perrysetgo.illageSummerCamp.R;
import com.perrysetgo.illageSummerCamp.models.Event;

public class EventAdapter extends BaseAdapter {
    public static final String TAG = EventAdapter.class.getSimpleName();
    private Context mContext;
    private ArrayList <Event> mEvents;

    public EventAdapter(Context context, ArrayList<Event> events) {
        mContext = context;
        mEvents = events; //// TODO: 6/29/16 events should be sorted before displaying.probably easiest to just sort the arraylist.
    }

//    Collections.sort(mEvents, new );

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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.event_list_item,parent,false);
            holder = new ViewHolder();

            holder.editButton = (ImageButton) convertView.findViewById(R.id.editButton);
            holder.deleteButton = (ImageButton) convertView.findViewById(R.id.deleteButton);
//            if (isRegistered()){
//                holder.editButton.setVisibility(View.VISIBLE);
//                holder.deleteButton.setVisibility(View.VISIBLE);
//            }

            holder.titleLabel = (TextView) convertView.findViewById(R.id.eventTitleLabel);
            holder.dateLabel = (TextView) convertView.findViewById(R.id.eventDateLabel);
            holder.locationLabel = (TextView) convertView.findViewById(R.id.eventLocationLabel);
            holder.descriptionLabel = (TextView) convertView.findViewById(R.id.eventDescriptionLabel);

            convertView.setTag(holder);
        } else{
            holder = (ViewHolder) convertView.getTag();
        }
        
        Event event = mEvents.get(position);
        holder.titleLabel.setText(event.getEventTitle());


        //// TODO: 6/29/16 fix AM or PM display. Currently shows all times as AM.Will need rework of timer with flag a :C
        SimpleDateFormat startTimeFormat = new SimpleDateFormat("EEE, MM/dd hh:mm", Locale.US);
        String startDateTime = startTimeFormat.format(event.getEventStartDateTime());
        Log.i(TAG, "startTime/date" + startDateTime);
        //END TIME
        SimpleDateFormat endTimeFormat = new SimpleDateFormat("EEE, MM/dd hh:mm", Locale.US);
        String endDateTime = endTimeFormat.format(event.getEventEndDateTime());

        holder.dateLabel.setText(startDateTime + " to " + endDateTime);
        holder.locationLabel.setText(event.getEventLocation());
        holder.descriptionLabel.setText(event.getEventDescription());

        return convertView;

    }

    private static class ViewHolder{
        TextView titleLabel;
        TextView dateLabel;
        TextView descriptionLabel;
        TextView locationLabel;
        ImageButton editButton;
        ImageButton deleteButton;
    }
}
