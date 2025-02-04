package up.chiapas.delp.login.domain

import up.chiapas.delp.core.network.TokenManager
import up.chiapas.delp.login.data.model.UserLoginRequest
import up.chiapas.delp.login.data.model.UserLoginResponse
import up.chiapas.delp.login.data.repository.LoginRepository

class LoginUseCase {
    private val repository = LoginRepository()

    suspend operator fun invoke(credentials: UserLoginRequest): Result<UserLoginResponse>{
        val result = repository.login(credentials)
        if (result.isSuccess){
            TokenManager.saveToken(result.getOrThrow().token)
        }
        return result
    }
}