package up.chiapas.delp.post.domain

import up.chiapas.delp.post.data.model.request.PostFoundResponse
import up.chiapas.delp.post.data.repository.PostRespository

class FindPostUseCase {
    private val repository = PostRespository()

    suspend operator fun invoke(id: String): Result<PostFoundResponse>{
        val result = repository.find(id)
        return result
    }
}