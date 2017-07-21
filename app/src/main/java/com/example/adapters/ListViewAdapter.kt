package com.example.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.example.R
import com.example.models.NotificationModel


class ListViewAdapter(private val mContext: Context, private val notificationModels: List<NotificationModel>) : BaseAdapter() {

    override fun getCount(): Int {
        return notificationModels.size
    }

    override fun getItem(i: Int): NotificationModel {
        return notificationModels[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup): View {
        var view = view

        val notificationViewHolder: NotificationViewHolder

        if (view == null) {

            val mInflater = mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = mInflater.inflate(R.layout.item_notification, null)

            notificationViewHolder = NotificationViewHolder()

            notificationViewHolder.item_view = view!!.findViewById<View>(R.id.notification_item) as LinearLayout
            notificationViewHolder.textView_notification = view.findViewById<View>(R.id.tv_notification) as TextView
            notificationViewHolder.textView_time = view.findViewById<View>(R.id.tv_notification_time) as TextView

            view.tag = notificationViewHolder

        } else {
            notificationViewHolder = view.tag as NotificationViewHolder
        }

        val (_, notificationText, timeStamp) = getItem(position)

        notificationViewHolder.textView_notification!!.text = notificationText
        notificationViewHolder.textView_time!!.text = timeStamp

        return view
    }

    private inner class NotificationViewHolder {
        internal var item_view: LinearLayout? = null
        internal var textView_notification: TextView? = null
        internal var textView_time: TextView? = null
    }

}