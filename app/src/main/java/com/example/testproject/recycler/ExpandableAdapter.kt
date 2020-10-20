package com.example.testproject.recycler

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.testproject.R
import com.example.testproject.recycler.data.Data
import com.example.testproject.recycler.data.Posts

class ExpandableAdapter(
    postsList1: ArrayList<ArrayList<Posts>>,
    dataList1: ArrayList<Data>,
    activity1: Activity
) : BaseExpandableListAdapter() {

    private val postsList: ArrayList<ArrayList<Posts>>
    private val dataList: ArrayList<Data>
    private val activity: Activity

    init {
        postsList = postsList1
        dataList = dataList1
        activity = activity1
    }

    override fun getGroupCount(): Int {
        return dataList.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return postsList[groupPosition].size
    }

    override fun getGroup(groupPosition: Int): Any? {
        return dataList[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any? {
        return postsList[groupPosition][childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(
        groupPosition: Int, isExpanded: Boolean, convertView: View,
        parent: ViewGroup
    ): View? {

        var itemView = convertView
        if (itemView == null) {
            val inflater =
                activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            itemView = inflater.inflate(R.layout.parent_list, null)
        }

        val tvName = convertView.findViewById<View>(R.id.tvName) as TextView
        val tvEmail = convertView.findViewById<View>(R.id.tvEmail) as TextView
        val tvWeb = convertView.findViewById<View>(R.id.tvWebSite) as TextView
        val tvPhone = convertView.findViewById<View>(R.id.tvPhone) as TextView

        tvName.text = dataList[groupPosition].name
        tvEmail.text = dataList[groupPosition].eMail
        tvWeb.text = dataList[groupPosition].website
        tvPhone.text = dataList[groupPosition].phone

        tvWeb.setOnClickListener {
            val uri =
                Uri.parse("http://www." + dataList[groupPosition].website)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            activity.startActivity(intent)
        }

        return itemView
    }

    override fun getChildView(
        groupPosition: Int, childPosition: Int, isLastChild: Boolean,
        convertView: View, parent: ViewGroup
    ): View {

        var itemView = convertView
        if (itemView == null) {
            val inflater =
                activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            itemView = inflater.inflate(R.layout.child_list, null)
        }

        val tvTitle = convertView.findViewById<TextView>(R.id.tvTitle)
        val tvBody = convertView.findViewById<TextView>(R.id.tvBody)

        tvTitle.text = postsList[groupPosition][childPosition].title
        tvBody.text = postsList[groupPosition][childPosition].body
        return itemView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}