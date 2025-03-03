package up.chiapas.delp.post.data.repository

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import up.chiapas.delp.core.network.RequestHelper
import up.chiapas.delp.post.data.model.response.EditPostResponse
import up.chiapas.delp.post.data.model.response.Post
import up.chiapas.delp.post.data.model.request.PostFoundResponse
import up.chiapas.delp.post.data.model.response.PostListResponse
import up.chiapas.delp.post.data.model.response.PostRemoveResponse

class PostRespository {
    private val service = RequestHelper.postService

    suspend fun listPost(): Result<PostListResponse> {
        return try {
            val response = service.listPosts()
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                val errorMessage = response.errorBody()?.string()
                val error = if (errorMessage.isNullOrEmpty()) {
                    "Ha ocurrido un error inesperado"
                } else {
                    try {
                        val errorJson = JSONObject(errorMessage)
                        val message =
                            errorJson.optString("message", "Ha ocurrido un error inesperado")
                        message
                    } catch (e: Exception) {
                        "Ha ocurrido un error inesperado"
                    }
                }
                Result.failure(Exception(error))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun createPost(
        title: RequestBody,
        body: RequestBody,
        image: MultipartBody.Part?
    ): Result<Post> {
        return try {
            val response = service.createPost(title, body, image)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                val errorMessage = response.errorBody()?.string()
                val error = if (errorMessage.isNullOrEmpty()) {
                    "Ha ocurrido un error inesperado"
                } else {
                    try {
                        val errorJson = JSONObject(errorMessage)
                        val message =
                            errorJson.optString("message", "Ha ocurrido un error inesperado")
                        message
                    } catch (e: Exception) {
                        "Ha ocurrido un error inesperado"
                    }
                }
                Result.failure(Exception(error))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    suspend fun removePost(id: Int): Result<PostRemoveResponse> {
        return try {
            val response = service.removePost(id)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                val errorMessage = response.errorBody()?.string()
                val error = if (errorMessage.isNullOrEmpty()) {
                    "Ha ocurrido un error inesperado"
                } else {
                    try {
                        val errorJson = JSONObject(errorMessage)
                        val message =
                            errorJson.optString("message", "Ha ocurrido un error inesperado")
                        message
                    } catch (e: Exception) {
                        "Ha ocurrido un error inesperado"
                    }
                }
                Result.failure(Exception(error))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun find(id: String): Result<PostFoundResponse> {
        return try {
            val response = service.find(id)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                val errorMessage = response.errorBody()?.string()
                val error = if (errorMessage.isNullOrEmpty()) {
                    "Ha ocurrido un error inesperado"
                } else {
                    try {
                        val errorJson = JSONObject(errorMessage)
                        val message =
                            errorJson.optString("message", "Ha ocurrido un error inesperado")
                        message
                    } catch (e: Exception) {
                        "Ha ocurrido un error inesperado"
                    }
                }
                Result.failure(Exception(error))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun editPost(
        id: String, title: RequestBody,
        body: RequestBody,
        image: MultipartBody.Part?
    ): Result<EditPostResponse> {
        return try {
            val response = service.edit(id, title, body, image)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                val errorMessage = response.errorBody()?.string()
                val error = if (errorMessage.isNullOrEmpty()) {
                    "Ha ocurrido un error inesperado"
                } else {
                    try {
                        val errorJson = JSONObject(errorMessage)
                        val message =
                            errorJson.optString("message", "Ha ocurrido un error inesperado")
                        message
                    } catch (e: Exception) {
                        "Ha ocurrido un error inesperado"
                    }
                }
                Result.failure(Exception(error))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}