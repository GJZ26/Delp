package up.chiapas.delp.register.domain

import android.util.Log
import up.chiapas.delp.core.network.TokenManager
import up.chiapas.delp.register.data.model.request.CreateUserRequest
import up.chiapas.delp.register.data.model.response.CreateUserResponse
import up.chiapas.delp.register.data.repository.RegisterRepository

class CreateUserUseCase {
    private val repository = RegisterRepository()

    suspend operator fun invoke(user: CreateUserRequest): Result<CreateUserResponse> {
        val result = repository.createUser(user)
        if (result.isSuccess) {
            TokenManager.saveToken(result.getOrThrow().token)
            Log.i("Delp", result.getOrThrow().token)
        }
        return result
    }
}