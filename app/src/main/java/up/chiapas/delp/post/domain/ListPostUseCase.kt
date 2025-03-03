package up.chiapas.delp.post.domain

import up.chiapas.delp.post.data.model.response.PostListResponse
import up.chiapas.delp.post.data.repository.PostRespository

class ListPostUseCase
{
    private val repository = PostRespository()

    suspend operator fun invoke(): Result<PostListResponse>{
        val result = repository.listPost()
        return result
    }
}