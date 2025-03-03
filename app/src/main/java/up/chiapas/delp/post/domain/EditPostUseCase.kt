package up.chiapas.delp.post.domain

import okhttp3.MultipartBody
import okhttp3.RequestBody
import up.chiapas.delp.post.data.model.request.EditPostRequest
import up.chiapas.delp.post.data.model.response.EditPostResponse
import up.chiapas.delp.post.data.repository.PostRespository

class EditPostUseCase {
    private val repository = PostRespository();

    suspend operator fun invoke(id: String, post: EditPostRequest): Result<EditPostResponse> {
        val titleRequestBody = RequestBody.create(MultipartBody.FORM, post.title)
        val bodyRequestBody = RequestBody.create(MultipartBody.FORM, post.body)
        return repository.editPost(id, titleRequestBody,bodyRequestBody, post.image)
    }
}