package com.helpiez.app.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.helpiez.app.R;
import com.helpiez.app.model.NGOEvent;
import com.helpiez.app.ui.activity.EventDetailActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by admin on 25/01/16.
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    final private static int EVENT_STATUS_OVER = 0;
    final private static int EVENT_STATUS_UPCOMING = 1;
    final private static int EVENT_STATUS_RUNNING = 2;

    Context mContext;
    private ArrayList<NGOEvent> mEvents;

    public EventAdapter(Context context) {
        mContext = context;
        mEvents = new ArrayList<>();
    }
    public void set(ArrayList<NGOEvent> events, int eventStatus) {
        for(NGOEvent event : events)
        {
//            String deadline = event.getEventDeadline();
//            Log.e("Rahul", deadline);
//            if(getEventStatus(event) == eventStatus)
//            {
//                mEvents.add(event);
//            }
        }
        mEvents.addAll(events);

        this.notifyDataSetChanged();
        events.clear();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView card = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_event, parent, false);
        ViewHolder vh = new ViewHolder(card);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final CardView card = holder.mCard;
        final NGOEvent event = mEvents.get(position);
        Log.e("Rahul", event.getEventName());
        TextView txtContent = (TextView)card.findViewById(R.id.name);
        TextView txtDesc = (TextView)card.findViewById(R.id.desc);
        txtContent.setText(event.getEventName());
        txtDesc.setText(event.getEventCause());
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EventDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("EVENT_DETAIL", event);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CardView mCard;
        public ViewHolder(View itemView) {
            super(itemView);
            mCard = (CardView)itemView;
        }

        public ViewHolder(CardView view) {
            super(view);
            mCard = view;
        }

    }

    private Boolean isOver(String deadline) {
        Date date;
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        try
        {
            date = format.parse(deadline);
            Date current = new Date();
            if(date.after(current))
                return true;
        }
        catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    private Boolean isUpComing(String startDate) {
        Date date;
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        try
        {
            date = format.parse(startDate);
            Date current = new Date();
            if(date.after(current))
                return true;
        }
        catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public int getEventStatus(NGOEvent event){
        if(isOver(event.getEventDeadline()))
            return EVENT_STATUS_OVER;
        if(isUpComing(event.getEventTime()))
            return EVENT_STATUS_UPCOMING;
        return EVENT_STATUS_RUNNING;
    }

}
