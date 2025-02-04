package up.chiapas.delp.register.data.model

data class CreateUserRequest(
    val username: String,
    val email: String,
    val password: String
)