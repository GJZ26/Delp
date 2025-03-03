package up.chiapas.delp.post.presentation.viewmodels

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import up.chiapas.delp.core.shared.data.model.ActionResponse
import up.chiapas.delp.post.data.model.request.EditPostRequest
import up.chiapas.delp.post.data.model.request.PostCreateRequest
import up.chiapas.delp.post.domain.CreatePostUseCase
import up.chiapas.delp.post.domain.EditPostUseCase
import up.chiapas.delp.post.domain.FindPostUseCase
import java.io.File

class PostViewModel() : ViewModel() {
    private val createPostUseCase = CreatePostUseCase()
    private val findPostUseCase = FindPostUseCase()
    private val editPostUseCase = EditPostUseCase()

    private var _title = MutableLiveData<String>("")
    val title: LiveData<String> = _title

    private var _body = MutableLiveData<String>("")
    val body: LiveData<String> = _body

    private var _imageFile = MutableLiveData<File?>(null)
    val imageFile: LiveData<File?> = _imageFile

    private var _image = MutableLiveData<String>("")
    val image: LiveData<String> = _image

    private var _authorAvatar = MutableLiveData<String>("")
    val authorAvatar: LiveData<String> = _authorAvatar

    private var _author = MutableLiveData<String>("")
    val author: LiveData<String> = _author

    private var _createdAt = MutableLiveData<String>("")
    val createdAt: LiveData<String> = _createdAt

    private var _imageUri  = MutableLiveData<Uri>(null)
    val imageUri: LiveData<Uri> = _imageUri

    fun onChangeTitle(title: String) {
        _title.value = title
    }

    fun onChangeBody(body: String) {
        _body.value = body
    }

    private fun setTitle(title: String) {
        _title.value = title
    }

    private fun setBody(body: String) {
        _body.value = body
    }

    private fun setImage(image: String?) {
        _image.value = image ?: ""
    }

    private fun setAuthor(author: String) {
        _author.value = author
    }

    private fun setCreatedAt(createdAt: String) {
        _createdAt.value = createdAt
    }

    private fun setAuthorAvatar(authorAvatar: String) {
        _authorAvatar.value = authorAvatar
    }

    suspend fun save(): ActionResponse {
        val titleValue = title.value?.trim()
        val bodyValue = body.value?.trim()
        val imageFileValue = imageFile.value

        if (titleValue.isNullOrEmpty() || bodyValue.isNullOrEmpty()) {
            return ActionResponse(false, "Please fill all the fields before publishing a post")
        }

        val imagePart: MultipartBody.Part? = imageFileValue?.let { file ->
            val requestBody = RequestBody.create("image/*".toMediaTypeOrNull(), file)
            MultipartBody.Part.createFormData("image", file.name, requestBody)
        }

        val result = createPostUseCase.invoke(PostCreateRequest(titleValue, bodyValue, imagePart))
        return ActionResponse(
            result.isSuccess,
            result.exceptionOrNull()?.message ?: "Ha ocurrido un error, inténtelo más tarde"
        )
    }

    suspend fun editPost(id: String): ActionResponse {
        val titleValue = title.value?.trim()
        val bodyValue = body.value?.trim()
        val imageFileValue = imageFile.value

        if (titleValue.isNullOrEmpty() || bodyValue.isNullOrEmpty()) {
            return ActionResponse(false, "Please fill all the fields before publish a post")
        }

        val imagePart: MultipartBody.Part? = imageFileValue?.let { file ->
            val requestBody = RequestBody.create("image/*".toMediaTypeOrNull(), file)
            MultipartBody.Part.createFormData("image", file.name, requestBody)
        }

        val result = editPostUseCase.invoke(id, EditPostRequest(titleValue, bodyValue, imagePart))

        return ActionResponse(
            result.isSuccess,
            result.exceptionOrNull()?.message ?: "Ha ocurrido un error, inténtelo mas tarde"
        )
    }


    suspend fun findPost(id: String): ActionResponse {
        val result = findPostUseCase.invoke(id)
        val post = result.getOrNull()
        if (post != null) {
            setTitle(post.post.title)
            setBody(post.post.content)
            setAuthor(post.post.author.username)
            setImage(post.post.image?.path)
            setCreatedAt(post.post.created_at)
            setAuthorAvatar(post.post.author.avatar)
        }
        return ActionResponse(
            result.isSuccess,
            result.exceptionOrNull()?.message ?: "Ha ocurrido un error, inténtelo mas tarde"
        )
    }

    fun onChangeImageUri(uri: Uri, file: File? = null) {
        _imageUri.value = uri
        _imageFile.value = file

        Log.d("PostViewModel", "Image URI updated: $uri")
    }


}