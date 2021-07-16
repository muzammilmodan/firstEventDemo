package brkckr.first.eleven.thread

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import brkckr.first.eleven.R
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily

class PostListAdapter(
        var context: Context,
        var alFollowList: ArrayList<PostResponse>
) : RecyclerView.Adapter<PostListAdapter.ViewHolder>() {

    /* companion object {
         var mClickListener: BtnClickListener? = null
     }*/

    fun setData(alFollowList: ArrayList<PostResponse>) {
        this.alFollowList.clear()
        this.alFollowList.addAll(alFollowList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_post_list_data, parent, false)
        return ViewHolder(v)
    }

    var lastSelectedPosition: Int = -1
    var lastUserID: Int? = -1
    var lastUserName: String? = ""

    // var thumb: Bitmap? = null
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //  mClickListener = btnlistener
        var itemList = alFollowList[position]

        holder.tvUserNameRPLD.text = itemList.name
        holder.tvPostDesRPLD.text = itemList.postDetails

        holder.ivProfilePicRPLD.setImageResource(itemList.profilePic)
        holder.ivProfilePicRPLD.shapeAppearanceModel =
                holder.ivProfilePicRPLD.shapeAppearanceModel
                        .toBuilder()
                        .setTopRightCorner(CornerFamily.ROUNDED, 0f)
                        .setTopLeftCorner(CornerFamily.ROUNDED, 35f)
                        .setBottomLeftCorner(CornerFamily.ROUNDED, 35f)
                        .setBottomRightCorner(CornerFamily.ROUNDED, 35f)
                        .build()

        if (itemList.postType == 0) {
            holder.ivPostMainPicRPLD.visibility = View.VISIBLE
        }

        if (!itemList.isClicked) {
            if (alFollowList.size > (position + 1)) {

                lastSelectedPosition = position

                if (alFollowList[position].postUserId == alFollowList[position + 1].postUserId) {
                    lastUserName = alFollowList[position].name
                    lastUserID = alFollowList[position].postUserId
                    holder.viewLine.visibility = View.VISIBLE
                    holder.viewLineBottom.visibility = View.GONE
                    alFollowList[position].lastViewVisible = true

                    var checkPosition = position - 1
                    if (alFollowList[checkPosition].lastViewVisible) {
                        if (alFollowList[position].postUserId == alFollowList[position - 1].postUserId) {

                            holder.tvShowReplies.visibility = View.VISIBLE
                            holder.tvPostDesRPLD.visibility = View.GONE
                            holder.ivPostMainPicRPLD.visibility = View.GONE

                            if (alFollowList.size > (position + 2)) {
                                if (alFollowList[position].lastViewVisible
                                        && alFollowList[position + 1].postUserId == alFollowList[position + 2].postUserId) {
                                    holder.cnstMain.visibility = View.GONE
                                } else {
                                    holder.viewLine.visibility = View.GONE
                                    holder.viewLineBottom.visibility = View.VISIBLE
                                    holder.cnstMain.visibility = View.GONE
                                }
                            } else if (alFollowList.size > (position + 1)) {
                                holder.viewLine.visibility = View.GONE
                                holder.viewLineBottom.visibility = View.VISIBLE
                            }

                        } else {
                            holder.tvShowReplies.visibility = View.GONE
                            holder.tvPostDesRPLD.visibility = View.VISIBLE
                        }
                    } else {
                        holder.tvShowReplies.visibility = View.GONE
                        holder.tvPostDesRPLD.visibility = View.VISIBLE
                    }

                    alFollowList[position + 1].lastViewVisible = true

                } else {
                    lastUserID = alFollowList[position].postUserId
                    holder.viewLine.visibility = View.GONE
                    holder.viewLineBottom.visibility = View.VISIBLE

                    if (alFollowList[position].lastViewVisible
                            || alFollowList[position].postUserId == alFollowList[position + 1].postUserId) {

                        holder.tvShowReplies.visibility = View.VISIBLE
                        holder.tvPostDesRPLD.visibility = View.GONE
                        holder.ivPostMainPicRPLD.visibility = View.GONE

                        if (alFollowList.size > (position + 2)) {
                            if (alFollowList[position].lastViewVisible
                                    && alFollowList[position + 1].postUserId == alFollowList[position + 2].postUserId) {
                                holder.cnstMain.visibility = View.GONE
                            } else {
                                holder.viewLine.visibility = View.GONE
                                holder.viewLineBottom.visibility = View.VISIBLE
                            }
                        }
                    } else {
                        holder.tvShowReplies.visibility = View.GONE
                        holder.tvPostDesRPLD.visibility = View.VISIBLE
                    }
                }
            } else {
                holder.viewLine.visibility = View.GONE
                holder.viewLineBottom.visibility = View.VISIBLE

                if (alFollowList[position].lastViewVisible) {

                    holder.tvShowReplies.visibility = View.VISIBLE
                    holder.tvPostDesRPLD.visibility = View.GONE
                    holder.ivPostMainPicRPLD.visibility = View.GONE

                    Log.e("Show Replies Visible 3", "-----===> ")
                    holder.cnstMain.visibility = View.GONE

                    /*  if (alFollowList.size > (position + 2)) {
                      if (alFollowList[position].lastViewVisible
                              && alFollowList[position + 1].postUserId == alFollowList[position + 2].postUserId) {
                          holder.cnstMain.visibility = View.GONE
                      } else {
                          holder.viewLine.visibility = View.GONE
                          holder.viewLineBottom.visibility = View.VISIBLE
                          holder.cnstMain.visibility = View.GONE
                      }
                  }
  */
                } else {
                    holder.tvShowReplies.visibility = View.GONE
                    holder.tvPostDesRPLD.visibility = View.VISIBLE
                }
            }
        } else {
            holder.tvShowReplies.visibility = View.GONE
            holder.tvPostDesRPLD.visibility = View.VISIBLE
            holder.ivPostMainPicRPLD.visibility = View.GONE
            holder.viewLine.visibility = View.VISIBLE
            holder.cnstMain.visibility = View.VISIBLE
            holder.viewLineBottom.visibility = View.GONE

            if (alFollowList.size > (position + 1)) {
                if (alFollowList[position].postUserId != alFollowList[position + 1].postUserId) {
                    holder.viewLine.visibility = View.GONE
                }
            } else if (alFollowList.size == (position + 1)) {
                holder.viewLine.visibility = View.GONE
            } else {
            }
        }

        if (itemList.postDetails.length < 200) {
            var textData = itemList.postDetails
            holder.tvPostDesRPLD.text = itemList.postDetails

            if (itemList.postType != 0)
                holder.rlPostDetailsVwRPLD.layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 200)
        } else {
            //holder.viewLine.layoutParams = ViewGroup.LayoutParams(1, 20)
        }

        holder.tvShowReplies.setOnClickListener {
            Toast.makeText(context, "Clicked Show Replies...$position", Toast.LENGTH_SHORT).show()
            if (alFollowList[position].postUserId == alFollowList[position - 1].postUserId) {
                alFollowList[position].lastViewVisible = false
                alFollowList[position - 1].lastViewVisible = false
            } else if (alFollowList[position].postUserId == alFollowList[position + 1].postUserId) {
                alFollowList[position].lastViewVisible = false
                alFollowList[position - 1].lastViewVisible = false
            }
            alFollowList[position].isClicked = true


            for (i in alFollowList.indices) {
                if (alFollowList[i].postUserId == alFollowList[position].postUserId) {
                    alFollowList[i].isClicked = true
                    holder.tvShowReplies.visibility = View.GONE
                    holder.tvPostDesRPLD.visibility = View.VISIBLE
                    holder.viewLine.visibility = View.GONE
                    holder.viewLineBottom.visibility = View.GONE
                    holder.ivPostMainPicRPLD.visibility = View.GONE

                    Log.e("Show Replies Visible 3", "-----===> ")
                    holder.cnstMain.visibility = View.VISIBLE
                    notifyItemChanged(i)
                }
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return alFollowList.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var tvUserNameRPLD = view.findViewById<TextView>(R.id.tvUserNameRPLD) as TextView
        var tvPostDesRPLD = view.findViewById<TextView>(R.id.tvPostDesRPLD) as TextView

        var ivProfilePicRPLD = view.findViewById<ShapeableImageView>(R.id.ivProfilePicRPLD) as ShapeableImageView
        var ivPostMainPicRPLD = view.findViewById<ShapeableImageView>(R.id.ivPostMainPicRPLD) as ShapeableImageView

        var viewLine = view.findViewById<View>(R.id.viewLine) as View
        var cnstLytProfileRPLD = view.findViewById<ConstraintLayout>(R.id.cnstLytProfileRPLD) as ConstraintLayout
        var rlPostDetailsVwRPLD = view.findViewById<RelativeLayout>(R.id.rlPostDetailsVwRPLD) as RelativeLayout
        var viewLineBottom = view.findViewById<View>(R.id.viewLineBottom) as View

        var tvShowReplies = view.findViewById<TextView>(R.id.tvShowReplies) as TextView

        var cnstMain = view.findViewById<ConstraintLayout>(R.id.cnstMain) as ConstraintLayout
    }
}
