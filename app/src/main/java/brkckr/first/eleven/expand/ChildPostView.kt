package brkckr.first.eleven.expand


import android.content.Context;
import android.graphics.drawable.Drawable
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import brkckr.first.eleven.R
import brkckr.first.eleven.thread.PostResponse

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

@Layout(R.layout.child_layout)
class ChildPostView(mContext: Context, movie: PostResponse) {

    @View(R.id.child_name)
    var textViewChild: TextView? = null

    @View(R.id.child_Des)
    var child_Des: TextView? = null

    @View(R.id.child_image)
    var childImage: ImageView? = null

    private val mContext: Context
    private val movie: PostResponse
    @Resolve
    private fun onResolve() {
        textViewChild!!.text = movie.name
        child_Des!!.text = movie.postDetails
        //Glide.with(mContext).load(movie.postImage).into(childImage!!)
        textViewChild!!.setOnClickListener { Toast.makeText(mContext, movie.name, Toast.LENGTH_SHORT).show() }
        childImage!!.setImageResource(movie.postImage)
    }

    companion object {
        private const val TAG = "ChildView"
    }

    init {
        this.mContext = mContext
        this.movie = movie
    }
}