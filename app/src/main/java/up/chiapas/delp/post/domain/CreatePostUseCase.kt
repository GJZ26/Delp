package up.chiapas.delp.post.domain

import okhttp3.MultipartBody
import okhttp3.RequestBody
import up.chiapas.delp.post.data.model.response.Post
import up.chiapas.delp.post.data.model.request.PostCreateRequest
import up.chiapas.delp.post.data.repository.PostRespository

class CreatePostUseCase {
    private val repository = PostRespository()

    suspend operator fun invoke(post: PostCreateRequest): Result<Post> {
        val titleRequestBody = RequestBody.create(MultipartBody.FORM, post.title)
        val bodyRequestBody = RequestBody.create(MultipartBody.FORM, post.body)

        return repository.createPost(titleRequestBody, bodyRequestBody, post.image)
    }

}