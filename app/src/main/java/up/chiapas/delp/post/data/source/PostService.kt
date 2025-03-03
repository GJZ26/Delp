package up.chiapas.delp.post.data.source

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import up.chiapas.delp.post.data.model.response.EditPostResponse
import up.chiapas.delp.post.data.model.response.Post
import up.chiapas.delp.post.data.model.request.PostFoundResponse
import up.chiapas.delp.post.data.model.response.PostListResponse
import up.chiapas.delp.post.data.model.response.PostRemoveResponse

interface PostService {
    @GET("/api/post")
    suspend fun listPosts(): Response<PostListResponse>

    @Multipart
    @POST("/api/post")
    suspend fun createPost(
        @Part("title") title: RequestBody,
        @Part("body") body: RequestBody,
        @Part image: MultipartBody.Part?
    ): Response<Post>


    @DELETE("/api/post/{id}")
    suspend fun removePost(@Path("id") id: Int): Response<PostRemoveResponse>

    @GET("/api/post/{id}")
    suspend fun find(@Path("id") id: String): Response<PostFoundResponse>

    @Multipart
    @POST("/api/post/{id}")
    suspend fun edit(@Path("id") id: String,   @Part("title") title: RequestBody,
                     @Part("body") body: RequestBody,
                     @Part image: MultipartBody.Part?
    ): Response<EditPostResponse>
}