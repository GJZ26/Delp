package up.chiapas.delp.post.data.model.request

import okhttp3.MultipartBody

data class EditPostRequest(
    val title: String,
    val body: String,
    val image: MultipartBody.Part?
)