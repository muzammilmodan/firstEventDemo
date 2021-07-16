package brkckr.first.eleven.thread

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.SparseBooleanArray
import android.view.View
import android.widget.EditText
import android.widget.MultiAutoCompleteTextView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import brkckr.first.eleven.R
import brkckr.first.eleven.expand.ExpandActivity
import brkckr.first.eleven.expand.ExpandPostActivity
import java.util.HashMap


class ThreadTimeLineActivity : AppCompatActivity() {

    lateinit var rcVwThreadATTL: RecyclerView
    lateinit var mContext: Context

    lateinit var edtDetails: EditText
    lateinit var progressBr: ProgressBar
    lateinit var tv_counter: TextView

    var progressCount = 0
    var totalMainCount = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_time_line)
        mContext = ThreadTimeLineActivity@ this

        rcVwThreadATTL = findViewById<RecyclerView>(R.id.rcVwThreadATTL) as RecyclerView
        edtDetails = findViewById<MultiAutoCompleteTextView>(R.id.edtDetails) as MultiAutoCompleteTextView
        progressBr = findViewById<ProgressBar>(R.id.progressBr) as ProgressBar
        tv_counter = findViewById<TextView>(R.id.tv_counter) as TextView

        val res: Resources = resources
        val drawable: Drawable = res.getDrawable(R.drawable.circular_progress_bar)
        progressBr.progress = progressCount // Main Progress
        progressBr.secondaryProgress = 100 // Secondary Progress
        progressBr.max = 100 // Maximum Progress
        progressBr.progressDrawable = drawable

        setPostList()

        changedListnerData()
    }

    private fun changedListnerData() {
        edtDetails.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                var totalCount = totalMainCount - s.length

                tv_counter.text = "$totalCount/$totalMainCount"

                val len: Int = edtDetails.text.toString().length
                if (len > 0) {
                    isTextComplete.put(edtDetails.id, true)
                } else {
                    isTextComplete.put(edtDetails.id, false)
                }
                checkProgress(s.length)
            }
        })
    }

    private val isTextComplete = SparseBooleanArray()

    private fun checkProgress(length: Int) {
        for (i in 0 until isTextComplete.size()) {
            if (isTextComplete.valueAt(i)) {
                progressCount = length
            }
        }
        progressBr.progress = progressCount / 2
    }

    lateinit var alPostList: ArrayList<PostResponse>


    private var categoryMap: MutableMap<String?, MutableList<PostResponse>>? = null
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
                R.drawable.profile_dmy, "Lyon have won.",
                R.drawable.profile_dmy, 3, "", 5, 4,
                2, false, false, false, "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
        alPostList.add(addFav)

        addFav = PostResponse(3, 45, "elon musk", "@casper123", "2 day ago",
                R.drawable.profile_dmy, "Lyon have won.sfsdfLyon have won.sfsdfLyon have won.sfsdfLyon have won.sfsdfLyon have won.sfsdfLyon have won.sfsdfLyon have won.sfsdf",
                R.drawable.profile_dmy, 3, "", 5, 4,
                2, false, false, false, "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
        alPostList.add(addFav)
        addFav = PostResponse(3, 45, "elon musk", "@casper123", "2 day ago",
                R.drawable.profile_dmy, "Lyon have won.",
                R.drawable.profile_dmy, 3, "", 5, 4,
                2, false, false, false, "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
        alPostList.add(addFav)

        addFav = PostResponse(3, 45, "elon musk", "@casper123", "2 day ago",
                R.drawable.profile_dmy, "Lyon have won.sfsdf",
                R.drawable.profile_dmy, 3, "", 5, 4,
                2, false, false, false, "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
        alPostList.add(addFav)
        addFav = PostResponse(3, 45, "elon musk", "@casper123", "2 day ago",
                R.drawable.profile_dmy, "Lyon have won.",
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

        addFav = PostResponse(12, 10, "BigB", "@casper123", "2 day ago",
                R.drawable.profile_dmy, "This is third, Lyon have won a record-extending seventh Women's Champions Lyon have won a record-extending seventh Women's Champions Lyon have won a record-extending seventh Women's Champions",
                R.drawable.profile_dmy, 3, "", 5, 4,
                2, false, false, false, "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
        alPostList.add(addFav)

        addFav = PostResponse(12, 10, "BigB", "@casper123", "2 day ago",
                R.drawable.profile_dmy, "This is third, Lyon have won a record-extending seventh Women's Champions Lyon have won a record-extending seventh Women's Champions Lyon have won a record-extending seventh Women's Champions",
                R.drawable.profile_dmy, 3, "", 5, 4,
                2, false, false, false, "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
        alPostList.add(addFav)

        for (movie in alPostList) {
            var movieList1 = categoryMap!![movie.postUserId]
            if (movieList1 == null) {
                movieList1 = ArrayList()
            }
            movieList1.add(movie)
            categoryMap!![movie.postUserId.toString()] = movieList1
        }

        Log.d("Map Data ======-----> ", categoryMap.toString())
        val it: MutableIterator<*> = categoryMap!!.entries.iterator()
        while (it.hasNext()) {
            val pair = it.next() as Map.Entry<*, *>

            Log.d("Key ======-----> ", pair.key.toString())

           // expandablePlaceHolderView!!.addView(HeaderView(this, pair.key.toString()))

            val movieList1 = pair.value as List<PostResponse>
            for (movie in movieList1) {
                //expandablePlaceHolderView!!.addView(ChildView(this, movie))
                Log.d("Key ======-----> ", movie.toString())
            }
            it.remove()
        }
        showPostData()
    }

    private fun showPostData() {
        try {
            var postAdapter = PostListAdapter(mContext, alPostList)

            val linearLayoutManager = LinearLayoutManager(mContext)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            rcVwThreadATTL.layoutManager = linearLayoutManager
            rcVwThreadATTL.adapter = postAdapter
            postAdapter.notifyDataSetChanged()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun OPenExpanView(view: View){
        var intent= Intent(mContext, ExpandPostActivity::class.java)
        startActivity(intent)
    }

}