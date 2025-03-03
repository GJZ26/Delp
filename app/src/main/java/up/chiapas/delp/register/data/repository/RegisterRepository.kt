package up.chiapas.delp.register.data.repository

import org.json.JSONObject
import up.chiapas.delp.core.network.RequestHelper
import up.chiapas.delp.register.data.model.request.CreateUserRequest
import up.chiapas.delp.register.data.model.response.CreateUserResponse

class RegisterRepository {
    private val registerService = RequestHelper.registerService

    suspend fun createUser(user: CreateUserRequest): Result<CreateUserResponse> {
        return try {
            val response = registerService.createUser(request = user)

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