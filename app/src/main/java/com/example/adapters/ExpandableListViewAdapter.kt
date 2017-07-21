package com.example.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.R


class ExpandableListViewAdapter(private val context: Context, private val listGroup: List<String>) : BaseExpandableListAdapter() {

    override fun getGroupCount(): Int {
        return listGroup.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return 1
    }

    override fun getGroup(groupPosition: Int): Any {
        return listGroup[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any? {
        return null
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            val infalInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = infalInflater.inflate(R.layout.group_layout, null)
        }

        (convertView as TextView).text = getGroup(groupPosition) as String

        return convertView
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            val infalInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            val itemType = getChildType(groupPosition, childPosition)

            when (itemType) {
                0 -> convertView = infalInflater.inflate(R.layout.item_widgets, null)
                1 -> convertView = infalInflater.inflate(R.layout.item_text, null)
                2 -> convertView = infalInflater.inflate(R.layout.item_layouts, null)
                3 -> convertView = infalInflater.inflate(R.layout.item_containers, null)
                4 -> convertView = infalInflater.inflate(R.layout.item_images, null)
                5 -> convertView = infalInflater.inflate(R.layout.item_date, null)
                6 -> convertView = infalInflater.inflate(R.layout.item_transitions, null)
                7 -> convertView = infalInflater.inflate(R.layout.item_advanced, null)
                8 -> convertView = infalInflater.inflate(R.layout.item_google, null)
                9 -> convertView = infalInflater.inflate(R.layout.item_design, null)
                10 -> convertView = infalInflater.inflate(R.layout.item_appcompat, null)
            }
        }
        return convertView as View
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return false
    }

    override fun getChildTypeCount(): Int {
        return 11
    }

    override fun getChildType(groupPosition: Int, childPosition: Int): Int {
        return groupPosition
    }

}