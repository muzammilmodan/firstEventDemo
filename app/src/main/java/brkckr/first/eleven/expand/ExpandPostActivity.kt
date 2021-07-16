package brkckr.first.eleven.expand

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import brkckr.first.eleven.R
import brkckr.first.eleven.thread.PostResponse
import com.mindorks.placeholderview.ExpandablePlaceHolderView
import java.util.ArrayList
import java.util.HashMap

class ExpandPostActivity : AppCompatActivity() {

    private var categoryMap: MutableMap<String?, MutableList<PostResponse>>? = null
    lateinit var alPostList: ArrayList<PostResponse>
    private var expandablePlaceHolderView: ExpandablePlaceHolderView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expand_post)
       // movieList = ArrayList()
        categoryMap = HashMap()
        expandablePlaceHolderView = findViewById<View>(R.id.expandablePlaceHolder) as ExpandablePlaceHolderView
        setPostList()
        expandablePlaceHolderView!!.setOnClickListener { view -> Toast.makeText(applicationContext, "Clixcked", view.id).show() }
    }

    private fun setPostList() {
        categoryMap = HashMap()

        // postType values: 0 = bigimage, 1=poll, 2 = match des ,3 video
        alPostList = ArrayList()
        var addFav = PostResponse(0, 15, "Jhon", "@casper123", "2 day ago", R.drawable.profile_dmy,
                "Lyon have won a record-extending seventh Women's Champions\n" +
                        "League title, beating Wolfsburg 3-1", R.drawable.profile_dmy, 0, "", 51, 4, 80, true, false, false)
        alPostList.add(addFav)

        addFav = PostResponse(1, 25, "Batida cart", "@casper123", "2 day ago", R.drawable.profile_dmy, "Who impressed you, ", R.drawable.profile_dmy, 1, "", 5, 4, 8, false, true, false)
        alPostList.add(addFav)

        addFav = PostResponse(2, 25, "Batida cart", "@casper123", "2 day ago", R.drawable.profile_dmy,
                "Real Salt Lake owner Dell Loy Hansen will sell his soccer teams in the wake of reports that " +
                        "he made racist comments.", R.drawable.profile_dmy, 2, "", 5, 4, 8, false, false, true)
        alPostList.add(addFav)

        addFav = PostResponse(2, 125, "Geter men", "@casper123", "2 day ago", R.drawable.profile_dmy,
                "Real Salt Lake owner Dell Loy Hansen will sell his soccer teams in the wake of reports that " +
                        "he made racist comments.", R.drawable.profile_dmy, 2, "", 5, 4, 8, false, false, true)
        alPostList.add(addFav)

        addFav = PostResponse(3, 45, "elon musk", "@casper123", "2 day ago",
                R.drawable.profile_dmy, "Lyon have won.sfsdf",
                R.drawable.profile_dmy, 3, "", 5, 4,
                2, false, false, false, "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
        alPostList.add(addFav)
        addFav = PostResponse(3, 45, "elon musk", "@casper123", "2 day ago",
                R.drawable.profile_dmy, "asdasdsdsads .",
                R.drawable.profile_dmy, 3, "", 5, 4,
                2, false, false, false, "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
        alPostList.add(addFav)

        addFav = PostResponse(3, 45, "elon musk", "@casper123", "2 day ago",
                R.drawable.profile_dmy, "sfsdfLyon have won.sfsdfLyon have won.sfsdfLyon have won.sfsdfLyon have won.sfsdfLyon have won.sfsdfLyon have won.sfsdf",
                R.drawable.profile_dmy, 3, "", 5, 4,
                2, false, false, false, "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
        alPostList.add(addFav)
        addFav = PostResponse(3, 45, "elon musk", "@casper123", "2 day ago",
                R.drawable.profile_dmy, "lleeoeoeoeoee",
                R.drawable.profile_dmy, 3, "", 5, 4,
                2, false, false, false, "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
        alPostList.add(addFav)

        addFav = PostResponse(3, 45, "elon musk", "@casper123", "2 day ago",
                R.drawable.profile_dmy, "poeoeproerpoer ererere",
                R.drawable.profile_dmy, 3, "", 5, 4,
                2, false, false, false, "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
        alPostList.add(addFav)
        addFav = PostResponse(3, 45, "elon musk", "@casper123", "2 day ago",
                R.drawable.profile_dmy, "qwwqqwewewqeweqwewqew",
                R.drawable.profile_dmy, 3, "", 5, 4,
                2, false, false, false, "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
        alPostList.add(addFav)

        addFav = PostResponse(3, 155, "elon best", "@casper123", "2 day ago",
                R.drawable.profile_dmy, "Lyon have won second.",
                R.drawable.profile_dmy, 3, "", 5, 4,
                2, false, false, false, "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
        alPostList.add(addFav)

        addFav = PostResponse(4, 35, "SRK", "@casper123", "2 day ago",
                R.drawable.profile_dmy, "Lyon have won a record-extending seventh Women's Champions Lyon have won a record-extending seventh Women's Champions Lyon have won a record-extending seventh Women's Champions",
                R.drawable.profile_dmy, 3, "", 5, 4,
                2, false, false, false, "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
        alPostList.add(addFav)

        addFav = PostResponse(5, 35, "SRK", "@casper123", "2 day ago",
                R.drawable.profile_dmy, "Lyon have won a record-extending seventh Women's Champions Lyon have won a record-extending seventh Women's Champions Lyon have won a record-extending seventh Women's Champions",
                R.drawable.profile_dmy, 3, "", 5, 4,
                2, false, false, false, "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
        alPostList.add(addFav)


        addFav = PostResponse(6, 35, "SRK", "@casper123", "2 day ago",
                R.drawable.profile_dmy, "Lyon have won a record-extending seventh Women's Champions Lyon have won a record-extending seventh Women's Champions Lyon have won a record-extending seventh Women's Champions",
                R.drawable.profile_dmy, 3, "", 5, 4,
                2, false, false, false, "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
        alPostList.add(addFav)

        addFav = PostResponse(7, 25, "Batida cart", "@casper123", "2 day ago",
                R.drawable.profile_dmy, "Lyon have won a record-extending seventh Women's Champions Lyon have won a record-extending seventh Women's Champions Lyon have won a record-extending seventh Women's Champions",
                R.drawable.profile_dmy, 3, "", 5, 4,
                2, false, false, false, "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
        alPostList.add(addFav)

        addFav = PostResponse(8, 35, "SRK", "@casper123", "2 day ago",
                R.drawable.profile_dmy, "Lyon have won a record-extending seventh Women's Champions Lyon have won a record-extending seventh Women's Champions Lyon have won a record-extending seventh Women's Champions",
                R.drawable.profile_dmy, 3, "", 5, 4,
                2, false, false, false, "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
        alPostList.add(addFav)
        addFav = PostResponse(9, 10, "BigB", "@casper123", "2 day ago",
                R.drawable.profile_dmy, "Lyon have won a record-extending seventh Women's Champions Lyon have won a record-extending seventh Women's Champions Lyon have won a record-extending seventh Women's Champions",
                R.drawable.profile_dmy, 3, "", 5, 4,
                2, false, false, false, "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
        alPostList.add(addFav)

        addFav = PostResponse(10, 10, "BigB", "@casper123", "2 day ago",
                R.drawable.profile_dmy, "Lyon have won a record-extending seventh Women's Champions Lyon have won a record-extending seventh Women's Champions Lyon have won a record-extending seventh Women's Champions",
                R.drawable.profile_dmy, 3, "", 5, 4,
                2, false, false, false, "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
        alPostList.add(addFav)

        addFav = PostResponse(11, 10, "BigB", "@casper123", "2 day ago",
                R.drawable.profile_dmy, "This is second, Lyon have won a record-extending seventh Women's Champions Lyon have won a record-extending seventh Women's Champions Lyon have won a record-extending seventh Women's Champions",
                R.drawable.profile_dmy, 3, "", 5, 4,
                2, false, false, false, "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
        alPostList.add(addFav)


        addFav = PostResponse(12, 10, "BigB", "@casper123", "2 day ago",
                R.drawable.profile_dmy, "This is third, Lyon have won a record-extending seventh Women's Champions Lyon have won a record-extending seventh Women's Champions Lyon have won a record-extending seventh Women's Champions",
                R.drawable.profile_dmy, 3, "", 5, 4,
                2, false, false, false, "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
        alPostList.add(addFav)


        for (movie in alPostList) {
            var movieList1 = categoryMap!![movie.postUserId.toString()+"."+movie.name]
            if (movieList1 == null) {
                movieList1 = ArrayList()
            }
            movieList1.add(movie)
            categoryMap!![movie.postUserId.toString()+"."+movie.name] = movieList1
        }

        Log.d("Map Data ======-----> ", categoryMap.toString())
        val it: MutableIterator<*> = categoryMap!!.entries.iterator()
        while (it.hasNext()) {
            val pair = it.next() as Map.Entry<*, *>

            Log.d("Key ======-----> ", pair.key.toString())

            expandablePlaceHolderView!!.addView(HeaderView(this, pair.key.toString()))

            val movieList1 = pair.value as List<PostResponse>
            for (movie in movieList1) {
                expandablePlaceHolderView!!.addView(ChildPostView(this, movie))
                Log.d("Key ======-----> ", movie.toString())
            }
            it.remove()
        }
      //  getHeaderAndChild(alPostList)
    }

    private fun getHeaderAndChild(movieList: List<PostResponse>) {
        for (movie in movieList) {
            var movieList1 = categoryMap!![movie.postUserId]
            if (movieList1 == null) {
                movieList1 = ArrayList()
            }
            movieList1.add(movie)
            categoryMap!![movie.postUserId.toString()] = movieList1
        }
        Log.d("Map", categoryMap.toString())
        val it: MutableIterator<*> = categoryMap!!.entries.iterator()
        while (it.hasNext()) {
            val pair = it.next() as Map.Entry<*, *>
            Log.d("Key", pair.key.toString())
            expandablePlaceHolderView!!.addView(HeaderView(this, pair.key.toString()))
            val movieList1 = pair.value as List<PostResponse>
            for (movie in movieList1) {
                expandablePlaceHolderView!!.addView(ChildPostView(this, movie))
            }
            it.remove()
        }
    }
}