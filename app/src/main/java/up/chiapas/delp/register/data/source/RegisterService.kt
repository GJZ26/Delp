package up.chiapas.delp.register.data.source

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import up.chiapas.delp.register.data.model.request.CreateUserRequest
import up.chiapas.delp.register.data.model.response.CreateUserResponse

interface RegisterService {
    @POST("/api/user")
    suspend fun createUser(@Body request: CreateUserRequest): Response<CreateUserResponse>
}