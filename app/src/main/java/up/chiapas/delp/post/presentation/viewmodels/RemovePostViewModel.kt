package up.chiapas.delp.post.presentation.viewmodels

import androidx.lifecycle.ViewModel
import up.chiapas.delp.core.shared.data.model.ActionResponse
import up.chiapas.delp.post.domain.RemovePostUseCase

class RemovePostViewModel() : ViewModel() {
    private val removePostUseCase = RemovePostUseCase()

    suspend fun removePost(id: Int): ActionResponse {
        val result = removePostUseCase.invoke(id)
        return ActionResponse(
            result.isSuccess,
            result.exceptionOrNull()?.message ?: "Ha ocurrido un error, inténtelo mas tarde"
        )
    }
}