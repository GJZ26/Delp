package up.chiapas.delp.login.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import up.chiapas.delp.core.shared.data.model.ActionResponse
import up.chiapas.delp.login.data.model.request.UserLoginRequest
import up.chiapas.delp.login.domain.LoginUseCase

class LoginViewModel() : ViewModel() {
    private val loginUseCase = LoginUseCase()

    private var _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private var _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    fun onChangeEmail(email: String) {
        _email.value = email
    }

    fun onChangePassword(password: String) {
        _password.value = password
    }

    suspend fun submit(): ActionResponse {
        val emailValue = email.value?.trim()
        val passwordValue = password.value?.trim()

        if(emailValue.isNullOrEmpty() || passwordValue.isNullOrEmpty()){
            return ActionResponse(false, "Todos los campos son obligatorios")
        }
        val result = loginUseCase.invoke(UserLoginRequest(emailValue,passwordValue))
        return ActionResponse(result.isSuccess, result.exceptionOrNull()?.message ?: "Ha ocurrido un error, inténtelo mas tarde")
    }
}