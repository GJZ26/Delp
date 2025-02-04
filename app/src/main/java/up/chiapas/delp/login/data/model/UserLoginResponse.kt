package up.chiapas.delp.login.data.model

data class UserLoginResponse(
    val message: String,
    val token: String,
    val user: User
)

data class User(
    val id: Int,
    val username: String,
    val avatar: String
)
