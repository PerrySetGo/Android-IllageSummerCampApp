//package com.example.guest.illageSummerCamp.adapters;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//
//import com.example.guest.illageSummerCamp.R;
//import com.example.guest.illageSummerCamp.models.Event;
//
//public class EventAdapter extends BaseAdapter{
//    private Context mContext;
//    private ArrayList <Event> mEvents;
//
//    public EventAdapter(Context context, ArrayList<Event> events) {
//        mContext = context;
//        mEvents = events;
//    }
//
////    @Override
////    public int getCount() {
////        return mEvents.size();
////    }
//
//    @Override
//    public Object getItem(int position) {
//        return mEvents.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder holder;
//        if (convertView == null)
//        {
//            convertView = LayoutInflater.from(mContext).inflate(R.layout.event_list_item,null);
//            holder = new ViewHolder();
//            holder.titleLabel = (TextView) convertView.findViewById(R.id.eventTitleLabel);
//            holder.dateLabel = (TextView) convertView.findViewById(R.id.eventDateLabel);
//            holder.locationLabel = (TextView) convertView.findViewById(R.id.eventLocationLabel);
//            holder.descriptionLabel = (TextView) convertView.findViewById(R.id.eventDescriptionLabel);
//
//            convertView.setTag(holder);
//        } else{
//            holder =(ViewHolder) convertView.getTag();
//        }
//        Event event = mEvents.get(position);
//        holder.titleLabel.setText(event.getEventTitle());
//
//        SimpleDateFormat startTimeFormat = new SimpleDateFormat("hh:mm");
//        String startTime = startTimeFormat.format(event.getDateTime());
//
//        SimpleDateFormat eventDateFormat = new SimpleDateFormat("MM/dd");
//        String eventDateString = eventDateFormat.format(event.getDateTime());
//
//        long endTimeInMillis = event.getEventEndTime();
//        Date endTimeDate = new Date (endTimeInMillis);
//
//        SimpleDateFormat endTimeFormat = new SimpleDateFormat("hh:mm");
//        String endTimeString = endTimeFormat.format(endTimeDate);
//
//        holder.dateLabel.setText(startTime + " to " + endTimeString + " on: " + eventDateString);
//        holder.locationLabel.setText(event.getEventLocation());
//        holder.descriptionLabel.setText(event.getEventDescription());
//
//        return convertView;
//
//    }
//
//    private static class ViewHolder{
//        TextView titleLabel;
//        TextView dateLabel;
//        TextView descriptionLabel;
//        TextView locationLabel;
//    }
//
//
//
//
//}
