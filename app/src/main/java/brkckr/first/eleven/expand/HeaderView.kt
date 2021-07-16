package brkckr.first.eleven.expand

import android.content.Context
import android.widget.TextView
import android.widget.Toast
import brkckr.first.eleven.R
import com.mindorks.placeholderview.annotations.Layout
import com.mindorks.placeholderview.annotations.Resolve
import com.mindorks.placeholderview.annotations.View
import com.mindorks.placeholderview.annotations.expand.Collapse
import com.mindorks.placeholderview.annotations.expand.Expand
import com.mindorks.placeholderview.annotations.expand.Parent
import com.mindorks.placeholderview.annotations.expand.SingleTop


@Parent
@SingleTop
@Layout(R.layout.header_layout)
class HeaderView(var mContext: Context, var mHeaderText: String) {
    private val TAG = "HeaderView"

    @View(R.id.header_text)
    var headerText: TextView? = null

    //private var mContext: Context? = null
   // private var mHeaderText: String? = null

   /* fun HeaderView(context: Context?, headerText: String?) {
        mContext = context
        mHeaderText = headerText
    }*/

    @Resolve
    private fun onResolve() {
        headerText!!.text = mHeaderText
    }

    @Expand
    private fun onExpand() {
        Toast.makeText(mContext, "onExpand $mHeaderText", Toast.LENGTH_SHORT).show()
    }

    @Collapse
    private fun onCollapse() {
        Toast.makeText(mContext, "onCollapse $mHeaderText", Toast.LENGTH_SHORT).show()
    }
}