package brkckr.first.eleven.expand


import android.content.Context;
import android.graphics.drawable.Drawable
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import brkckr.first.eleven.R

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

@Layout(R.layout.child_layout)
class ChildView(mContext: Context, movie: Movie) {
    @View(R.id.child_name)
    var textViewChild: TextView? = null

    @View(R.id.child_image)
    var childImage: ImageView? = null
    private val mContext: Context
    private val movie: Movie
    @Resolve
    private fun onResolve() {
        textViewChild!!.text = movie.getName()
        Glide.with(mContext).load(movie.getImageUrl()).into(childImage!!)
        textViewChild!!.setOnClickListener { Toast.makeText(mContext, movie.getName(), Toast.LENGTH_SHORT).show() }
    }

    companion object {
        private const val TAG = "ChildView"
    }

    init {
        this.mContext = mContext
        this.movie = movie
    }
}