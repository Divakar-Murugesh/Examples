package com.example.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.R;
import com.example.models.NotificationModel;

import java.util.List;

public class NotificationListAdapter extends BaseAdapter {

    private Context mContext;
    private List<NotificationModel> notificationModels;

    public NotificationListAdapter(Context mContext, List<NotificationModel> notificationModels) {
        this.mContext = mContext;
        this.notificationModels = notificationModels;
    }

    @Override
    public int getCount() {
        return notificationModels.size();
    }

    @Override
    public NotificationModel getItem(int i) {
        return notificationModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        NotificationViewHolder notificationViewHolder;

        if (view == null) {

            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.item_notification, null);

            notificationViewHolder = new NotificationViewHolder();

            notificationViewHolder.item_view = (LinearLayout) view.findViewById(R.id.notification_item);
            notificationViewHolder.textView_notification = (TextView) view.findViewById(R.id.tv_notification);
            notificationViewHolder.textView_time = (TextView) view.findViewById(R.id.tv_notification_time);

            view.setTag(notificationViewHolder);

        } else {
            notificationViewHolder = (NotificationViewHolder) view.getTag();
        }

        NotificationModel notificationModel = getItem(position);

        notificationViewHolder.textView_notification.setText(notificationModel.getNotificationText());
        notificationViewHolder.textView_time.setText(notificationModel.getTimeStamp());

        return view;
    }

    private class NotificationViewHolder {
        LinearLayout item_view;
        TextView textView_notification, textView_time;
    }

}
