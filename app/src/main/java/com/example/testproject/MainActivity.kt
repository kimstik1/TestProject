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

    private var adapter: ExpandableAdapter? = null // expandable адаптер

    private var postsList: ArrayList<ArrayList<Posts>>? = null  // посты user'ов
    private var dataList: ArrayList<Data>? = null               // данные user'ов

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        expandableListView.visibility = View.INVISIBLE // скрываем expandableListView

        btnStart.setOnClickListener {       // обработчик нажатия btn запускающий получение данных
            userQuery()
        }
    }

    //Получаем список user'ов
    private fun userQuery() {
        Repos.instance.getUsers().enqueue(object : Callback<List<Model>> {
            override fun onResponse(call: Call<List<Model>>, response: Response<List<Model>>) {
                val models = response.body()

                // листы с данными
                postsList = ArrayList<ArrayList<Posts>>()   // посты user'ов
                dataList = ArrayList<Data>()                // данные user'ов

                postsList?.clear()
                dataList?.clear()

                for (m in models!!) {// парсим каждый полученный элемент
                    dataList!!.add(Data(m)) // сохраняем данные в dataList, который потом передадим в адаптер
                }
                postsQuery() // запрашиваем посты user'ов
            }

            override fun onFailure(call: Call<List<Model>>, t: Throwable) {
                failure()
            }
        })
    }

    // Получаем Posts наших user'ов
    private fun postsQuery() {
        Repos.instance.getPosts().enqueue(object : Callback<List<UserPosts>> {
            override fun onResponse(
                call: Call<List<UserPosts>>,
                response: Response<List<UserPosts>>
            ) {
                val posts: List<UserPosts>? = response.body()

                // парсим данные
                for (j in 0 until (dataList!!.size)) {       // получаем количество пользователей
                    val postsArrayList =
                        ArrayList<Posts>()     // создаем лист в который кладем title и body

                    for (p in posts!!) {            // парсим каждый элемент полученного списка
                        if (j == p.userId - 1) {             // если userId совпадает с номером пользователя из списка dataList сохраняем их элемент в list
                            postsArrayList.add(Posts(p))    // добавляем в list
                        }
                    }
                    postsList!!.add(
                        j,                // позиция на которую сохраняется list
                        postsArrayList)  // сохраняем list в postList, который передадим в адаптер
                }
                // в случае успеха 2 запросов выводим list на экран
                showList()
            }

            override fun onFailure(call: Call<List<UserPosts>>, t: Throwable) {
                failure()
            }
        })
    }

    fun showList() {
        adapter = ExpandableAdapter(postsList!!, dataList!!, this) // передаем данные в адаптер
        expandableListView.setAdapter(adapter)                            // сохраняем для нашего expandable адаптер

        // Убираем видимость btn и tv, выводим список
        tvStart.visibility = View.INVISIBLE
        btnStart.visibility = View.INVISIBLE
        expandableListView.visibility = View.VISIBLE
    }

    fun failure() {
        // Изменяем текст btn и tv если не удалось получить данные
        tvStart.setText(R.string.tvHelloIfFail)
        btnStart.setText(R.string.btnHelloIfFail)
    }
}