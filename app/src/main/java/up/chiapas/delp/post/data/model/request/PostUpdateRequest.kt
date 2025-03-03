package up.chiapas.delp.post.data.model.request

data class PostFoundResponse(
    val message: String,
    val post: PostDetail
)
data class PostDetail(
    val id: Int,
    val title:String,
    val content: String,
    val is_mine:Boolean,
    val image: Image?,
    val author: AuthorDetail,
    val created_at: String
)

data class AuthorDetail(
    val id: Int,
    val username: String,
    val avatar: String,
)

data class Image(
    val path: String,
    val original_name:String
)