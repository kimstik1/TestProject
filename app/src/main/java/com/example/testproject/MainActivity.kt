package com.example.testproject

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ExpandableListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.testproject.network.Model
import com.example.testproject.network.UserPosts
import com.example.testproject.network.api.Repos
import com.example.testproject.recycler.ExpandableAdapter
import com.example.testproject.recycler.data.Data
import com.example.testproject.recycler.data.Posts
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var adapter: ExpandableAdapter? = null
    private var expandableListView: ExpandableListView? = null

    private var postsList: ArrayList<ArrayList<Posts>>? = null
    private var dataList: ArrayList<Data>? = null

    private var tvHello: TextView? = null
    private var btnHello: Button? = null

    private var activity: Activity = this@MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvHello = findViewById<View>(R.id.tvStart) as TextView
        val btn = findViewById<View>(R.id.btnStart) as Button
        val expandableListView = findViewById<View>(R.id.expandableListView) as ExpandableListView

        expandableListView.visibility = View.INVISIBLE

        btn.setOnClickListener {
            postsList = ArrayList<ArrayList<Posts>>()
            dataList = ArrayList<Data>()

            adapter = ExpandableAdapter(postsList!!, dataList!!, this, activity)
            expandableListView.setAdapter(adapter)

            userQuery()
        }
    }

    private fun userQuery() {
        Repos.instance.getUsers().enqueue(object : Callback<List<Model>> {
            override fun onResponse(call: Call<List<Model>>, response: Response<List<Model>>) {
                val models = response.body()

                for (m in models!!) {
                    dataList!!.add(Data(m))
                }
                adapter!!.notifyDataSetChanged()
                postsQuery()
            }

            override fun onFailure(call: Call<List<Model>>, t: Throwable) {
                Log.wtf("onFailure/Users", t)

                tvHello!!.setText(R.string.btnHelloIfFail)
                btnHello!!.setText(R.string.btnHelloIfFail)
            }
        })
    }

    private fun postsQuery() {
        Repos.instance.getPosts().enqueue(object : Callback<List<UserPosts>> {
            override fun onResponse(
                call: Call<List<UserPosts>>,
                response: Response<List<UserPosts>>
            ) {
                val posts: List<UserPosts>? = response.body()

                for (j in dataList!!.indices) {
                    val postsArrayList = java.util.ArrayList<Posts>()
                    for (p in posts!!) {
                        if (j == p.getUserId()!! - 1) {
                            postsArrayList.add(Posts(p))
                        }
                    }
                    postsList!!.add(j, postsArrayList)
                }

                tvHello!!.visibility = View.INVISIBLE
                btnHello!!.visibility = View.INVISIBLE
                expandableListView!!.visibility = View.VISIBLE
            }

            override fun onFailure(call: Call<List<UserPosts>>, t: Throwable) {
                Log.wtf("onFailure/Post", t)

                tvHello!!.setText(R.string.btnHelloIfFail)
                btnHello!!.setText(R.string.btnHelloIfFail)
            }

        })
    }

}


