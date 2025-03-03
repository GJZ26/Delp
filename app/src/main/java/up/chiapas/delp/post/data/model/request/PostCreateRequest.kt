package up.chiapas.delp.post.data.model.request

import okhttp3.MultipartBody

data class PostCreateRequest(
    val title: String,
    val body: String,
    val image: MultipartBody.Part?
)
