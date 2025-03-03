package up.chiapas.delp.post.domain

import up.chiapas.delp.post.data.model.response.PostRemoveResponse
import up.chiapas.delp.post.data.repository.PostRespository

class RemovePostUseCase {
    private val repository = PostRespository()

    suspend operator fun invoke(id: Int): Result<PostRemoveResponse> {
        val response = repository.removePost(id)
        return response
    }
}