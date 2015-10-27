package com.example.guest.illageSummerCamp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.guest.illageSummerCamp.R;
import com.example.guest.illageSummerCamp.models.Event;

public class EventAdapter extends BaseAdapter{
    private Context mContext;
    private ArrayList <Event> mEvents;

    public EventAdapter(Context context, ArrayList<Event> events) {
        mContext = context;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.event_list_item,null);
            holder = new ViewHolder();
            holder.titleLabel = (TextView) convertView.findViewById(R.id.eventTitleLabel);
            holder.timeLabel = (TextView) convertView.findViewById(R.id.eventTimeLabel);
            holder.dateLabel = (TextView) convertView.findViewById(R.id.eventDateLabel);
            holder.descriptionLabel = (TextView) convertView.findViewById(R.id.eventDescriptionLabel);

            convertView.setTag(holder);
        } else{
            holder =(ViewHolder) convertView.getTag();
        }
        Event event = mEvents.get(position);
        holder.titleLabel.setText(event.getEventTitle());
        holder.timeLabel.setText("From: " + event.getEventStartTime() + " To: " + event.getEventEndTime());
        holder.dateLabel.setText(event.getEventDate());
        holder.descriptionLabel.setText(event.getEventDescription());

        return convertView;

    }

    private static class ViewHolder{
        TextView titleLabel;
        TextView timeLabel;
        TextView dateLabel;
        TextView descriptionLabel;
    }
}
