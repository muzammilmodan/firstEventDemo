package brkckr.first.eleven

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import brkckr.first.eleven.expand.ApiClient.instance
import brkckr.first.eleven.expand.ApiInterface
import brkckr.first.eleven.expand.ChildView
import brkckr.first.eleven.expand.HeaderView
import brkckr.first.eleven.expand.Movie
import com.mindorks.placeholderview.ExpandablePlaceHolderView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

internal class TestClass : AppCompatActivity() {
    private var categoryMap: MutableMap<String?, MutableList<Movie>>? = null
    private var movieList: List<Movie>? = null
    private var expandablePlaceHolderView: ExpandablePlaceHolderView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        movieList = ArrayList()
        categoryMap = HashMap()
        expandablePlaceHolderView = findViewById<View>(R.id.expandablePlaceHolder) as ExpandablePlaceHolderView
        loadData()
        expandablePlaceHolderView!!.setOnClickListener { view -> Toast.makeText(applicationContext, "Clixcked", view.id).show() }
    }

    private fun loadData() {
        /*val apiInterface = instance!!.create(ApiInterface::class.java)
        apiInterface.allMovies!!.enqueue(object : Callback<List<Movie>?> {
            override fun onResponse(call: Call<List<Movie>?>, response: Response<List<Movie>?>) {
                movieList = response.body()
                getHeaderAndChild(movieList)
            }

            override fun onFailure(call: Call<List<Movie>?>, t: Throwable) {}
        })*/
    }

    private fun getHeaderAndChild(movieList: List<Movie>?) {
        for (movie in movieList!!) {
            var movieList1 = categoryMap!![movie.getCategoty()]
            if (movieList1 == null) {
                movieList1 = ArrayList()
            }
            movieList1.add(movie)
            categoryMap!![movie.getCategoty()] = movieList1
        }
        Log.d("Map", categoryMap.toString())
        val it: MutableIterator<*> = categoryMap!!.entries.iterator()
        while (it.hasNext()) {
            val pair = it.next() as Map.Entry<*, *>
            Log.d("Key", pair.key.toString())
            expandablePlaceHolderView!!.addView(HeaderView(this, pair.key.toString()))
            val movieList1 = pair.value as List<Movie>
            for (movie in movieList1) {
                expandablePlaceHolderView!!.addView(ChildView(this, movie))
            }
            it.remove()
        }
    }
}