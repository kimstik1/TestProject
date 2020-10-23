package com.example.testproject.recycler

import android.annotation.SuppressLint
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
    postsList: ArrayList<ArrayList<Posts>>, //list с постами
    dataList: ArrayList<Data>,              //list с user'ами
    activity: Activity
) : BaseExpandableListAdapter() {

    private val postsList: ArrayList<ArrayList<Posts>> //list с постами
    private val dataList: ArrayList<Data>              //list с user'ами
    private val activity: Activity

    init {
        this.postsList = postsList
        this.dataList = dataList
        this.activity = activity
    }

    override fun getGroupCount(): Int { // узнаем размер list'a с user'ами
        return dataList.size
    }

    override fun getChildrenCount(groupPosition: Int): Int { // узнаем размер list'a с постами user'a
        return postsList[groupPosition].size
    }

    override fun getGroup(groupPosition: Int): Any? { // получаем user'а
        return dataList[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any? { // получаем пост user'a
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

    @SuppressLint("InflateParams")
    override fun getGroupView( // наполняем родительские карточки
        groupPosition: Int, isExpanded: Boolean, convertView: View?,
        parent: ViewGroup
    ): View? {

        var itemView = convertView
        if (itemView == null) {
            val inflater =
                activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            itemView = inflater.inflate(R.layout.parent_list, null)
        }

        val tvName = itemView!!.findViewById<View>(R.id.tvName) as TextView
        val tvEmail = itemView.findViewById<View>(R.id.tvEmail) as TextView
        val tvWeb = itemView.findViewById<View>(R.id.tvWebSite) as TextView
        val tvPhone = itemView.findViewById<View>(R.id.tvPhone) as TextView

        tvName.text = dataList[groupPosition].name
        tvEmail.text = dataList[groupPosition].eMail
        tvWeb.text = dataList[groupPosition].website
        tvPhone.text = dataList[groupPosition].phone

        tvWeb.setOnClickListener { //обработчик нажатия для перехода по ссылку в браузер
            val uri =
                Uri.parse("http://www." + dataList[groupPosition].website)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            activity.startActivity(intent)
        }

        return itemView
    }

    @SuppressLint("InflateParams")
    override fun getChildView( // наполняем дочерние карточки
        groupPosition: Int, childPosition: Int, isLastChild: Boolean,
        convertView: View?, parent: ViewGroup
    ): View {

        var itemView = convertView
        if (itemView == null) {
            val inflater =
                activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            itemView = inflater.inflate(R.layout.child_list, null)
        }

        val tvTitle = itemView!!.findViewById<TextView>(R.id.tvTitle)
        val tvBody = itemView.findViewById<TextView>(R.id.tvBody)

        tvTitle.text = postsList[groupPosition][childPosition].title
        tvBody.text = postsList[groupPosition][childPosition].body
        return itemView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}