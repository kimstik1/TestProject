package com.example.testproject

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.testproject.network.Model
import com.example.testproject.network.UserPosts
import com.example.testproject.network.api.Repos
import com.example.testproject.recycler.ExpandableAdapter
import com.example.testproject.recycler.data.Data
import com.example.testproject.recycler.data.Posts
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var adapter: ExpandableAdapter? = null

    private var postsList: ArrayList<ArrayList<Posts>>? = null
    private var dataList: ArrayList<Data>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        expandableListView.visibility = View.INVISIBLE

        btnStart.setOnClickListener {
            postsList = ArrayList<ArrayList<Posts>>()
            dataList = ArrayList<Data>()

            adapter = ExpandableAdapter(postsList!!, dataList!!, this)
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
                tvStart.text[R.string.tvHelloIfFail]
                btnStart.text[R.string.btnHelloIfFail]
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

                for (j in dataList.size()) {
                    val postsArrayList = ArrayList<Posts>()
                    for (p in posts!!) {
                        if (j == p.userId - 1) {
                            postsArrayList.add(Posts(p))
                        }
                    }
                    postsList!!.add(j, postsArrayList)
                }

                tvStart.visibility = View.INVISIBLE
                btnStart.visibility = View.INVISIBLE
                expandableListView.visibility = View.VISIBLE
            }

            override fun onFailure(call: Call<List<UserPosts>>, t: Throwable) {
                tvStart.text[R.string.tvHelloIfFail]
                btnStart.text[R.string.btnHelloIfFail]
            }
        })
    }
}