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
    postsList: ArrayList<ArrayList<Posts>>,
    dataList: ArrayList<Data>,
    context: Context,
    activity1: Activity
) : BaseExpandableListAdapter() {

    var postsList: ArrayList<ArrayList<Posts>>? = null
    var dataList: ArrayList<Data>? = null
    var mContext: Context? = null
    var activity: Activity? = null

    fun ExpandableAdapter(
        postsList: ArrayList<ArrayList<Posts>>,
        dataList: ArrayList<Data>,
        mContext: Context,
        activity: Activity
    ) {

        this.postsList = postsList
        this.dataList = dataList
        this.mContext = mContext
        this.activity = activity
    }

    override fun getGroupCount(): Int {
        return dataList!!.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return postsList!![groupPosition].size
    }

    override fun getGroup(groupPosition: Int): Any? {
        return dataList!![groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any? {
        return postsList!![groupPosition][childPosition]
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
        groupPosition: Int, isExpanded: Boolean, convertView: View?,
        parent: ViewGroup?
    ): View? {
        var convertView = convertView
        if (convertView == null) {
            val inflater =
                mContext!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.parent_list, null)
        }
        val tvName = convertView!!.findViewById<View>(R.id.tvName) as TextView
        val tvEmail = convertView.findViewById<View>(R.id.tvEmail) as TextView
        val tvWeb = convertView.findViewById<View>(R.id.tvWebSite) as TextView
        val tvPhone = convertView.findViewById<View>(R.id.tvPhone) as TextView
        tvName.text = dataList!![groupPosition].getName()
        tvEmail.text = dataList!![groupPosition].geteMail()
        tvWeb.text = dataList!![groupPosition].getWebsite()
        tvPhone.text = dataList!![groupPosition].getPhone()

        tvWeb.setOnClickListener { v: View? ->
            val uri =
                Uri.parse("http://www." + dataList!![groupPosition].getWebsite())
            val intent = Intent(Intent.ACTION_VIEW, uri)
            activity!!.startActivity(intent)
        }

        return convertView
    }

    override fun getChildView(
        groupPosition: Int, childPosition: Int, isLastChild: Boolean,
        convertView: View?, parent: ViewGroup?
    ): View? {
        var convertView = convertView
        if (convertView == null) {
            val inflater =
                mContext!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.child_list, null)
        }
        val tvTitle = convertView!!.findViewById<TextView>(R.id.tvTitle)
        val tvBody = convertView.findViewById<TextView>(R.id.tvBody)
        tvTitle.setText(postsList!![groupPosition][childPosition].getTitle())
        tvBody.setText(postsList!![groupPosition][childPosition].getBody())
        return convertView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}