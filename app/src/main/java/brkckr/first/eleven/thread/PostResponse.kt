package brkckr.first.eleven.thread

data class PostResponse(
        var id: Int,
        var postUserId: Int,
        var name: String,
        var socialId: String,
        var postTime: String,
        var profilePic: Int,
        var postDetails: String,
        var postImage: Int,
        var postType: Int,//0 = bigimage, 1=poll, 2 = match des
        var pollList: String,
        var likeCount:Int,
        var commentCount:Int,
        var retweetCount:Int,
        var isLikePost:Boolean,
        var isCommentPost:Boolean,
        var isRetweetPost:Boolean,
        var postVideoUrl: String?=null,
        var lastViewVisible:Boolean=false,
        var isClicked:Boolean=false
)