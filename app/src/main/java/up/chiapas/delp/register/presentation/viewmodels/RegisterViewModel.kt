package up.chiapas.delp.register.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import up.chiapas.delp.core.shared.data.model.ActionResponse
import up.chiapas.delp.register.data.model.request.CreateUserRequest
import up.chiapas.delp.register.domain.CreateUserUseCase

class RegisterViewModel() : ViewModel() {
    private val createUserUseCase = CreateUserUseCase()

    private var _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private var _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private var _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private var _rePassword = MutableLiveData<String>()
    val re_password: LiveData<String> = _rePassword

    fun onChangeUsername(username: String) {
        _username.value = username
    }

    fun onChangeEmail(email: String) {
        _email.value = email
    }

    fun onChangePassword(password: String) {
        _password.value = password
    }

    fun onChangeRePassword(rePassword: String) {
        _rePassword.value = rePassword
    }

    suspend fun submit(): ActionResponse {
        val usernameValue = username.value?.trim()
        val emailValue = email.value?.trim()
        val passwordValue = password.value?.trim()
        val rePasswordValue = re_password.value?.trim()

        if (usernameValue.isNullOrEmpty() || emailValue.isNullOrEmpty() || passwordValue.isNullOrEmpty()) {
            return ActionResponse(false, "Todos los campos son obligatorios")
        }

        if (passwordValue != rePasswordValue) {
            return ActionResponse(false, "Las contraseñas no coinciden")
        }

        val result = createUserUseCase.invoke(CreateUserRequest(usernameValue, emailValue, passwordValue))

        return ActionResponse(result.isSuccess, result.exceptionOrNull()?.message ?: "Ha ocurrido un error, inténtelo mas tarde")
    }
}