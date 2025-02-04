package up.chiapas.delp.login.data.repository

import org.json.JSONObject
import up.chiapas.delp.core.network.RequestHelper
import up.chiapas.delp.login.data.model.UserLoginRequest
import up.chiapas.delp.login.data.model.UserLoginResponse

class LoginRepository {
    private val loginService = RequestHelper.loginService

    suspend fun login(credentials: UserLoginRequest): Result<UserLoginResponse>
    {
        return try{
            val response = loginService.login(credentials)

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
        }catch(e: Exception){
            Result.failure(e)
        }
    }
}