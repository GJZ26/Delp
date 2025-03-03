package up.chiapas.delp.post.data.model.response

data class PostListResponse (
    val posts: List<Post>
)

data class Post(
    val id:Int,
    val title: String,
    val content: String,
    val is_mine: Boolean,
    val author: Author,
    val created_at: String
)

data class Author(
    val id: Int,
    val username:String,
    val avatar: String
)