package edmt.dev.kotlineatitv2client.Callback

import edmt.dev.kotlineatitv2client.Model.CommentModel

interface ICommentCallBack {
    fun onCommentLoadSuccess(commentList:List<CommentModel>)
    fun onCommentLoadFailed(message:String)
}