package up.chiapas.delp.login.data.source

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import up.chiapas.delp.login.data.model.request.UserLoginRequest
import up.chiapas.delp.login.data.model.response.UserLoginResponse

interface LoginService {
    @POST("/api/user/access")
    suspend fun login(@Body credentials: UserLoginRequest): Response<UserLoginResponse>
}