package up.chiapas.delp.post.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import up.chiapas.delp.core.shared.data.model.ActionResponse
import up.chiapas.delp.post.data.model.response.Post
import up.chiapas.delp.post.domain.ListPostUseCase
import up.chiapas.delp.post.domain.RemovePostUseCase

class HomeViewModel : ViewModel() {
    private val listPostUseCase = ListPostUseCase()
    private val removePostUseCase = RemovePostUseCase()

    private val _posts = MutableLiveData<List<Post>>(emptyList())
    val posts: LiveData<List<Post>> = _posts

    private var isFetched = false

    suspend fun getList(): ActionResponse {
        if (!isFetched) {
            val result = listPostUseCase.invoke()
            if (result.isSuccess) {
                val tempResult = result.getOrNull()
                if (tempResult != null) {
                    _posts.value = tempResult.posts
                }
            }
            isFetched = true
            return ActionResponse(
                result.isSuccess,
                result.exceptionOrNull()?.message ?: "Ha ocurrido un error, inténtelo más tarde"
            )
        }
        return ActionResponse(true, "Lista ya obtenida")
    }

    suspend fun removePost(id: Int): ActionResponse {
        val result = removePostUseCase.invoke(id)
        if (result.isSuccess) {
            _posts.value = _posts.value?.filterNot { post -> post.id == id } ?: emptyList()
        }

        return ActionResponse(
            result.isSuccess,
            result.exceptionOrNull()?.message ?: "Ha ocurrido un error, inténtelo más tarde"
        )
    }

    fun resetFetched() {
        isFetched = false
    }

}
