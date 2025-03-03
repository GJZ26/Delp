package up.chiapas.delp.register.data.model.response

data class CreateUserResponse (
    val message:String,
    val token: String,
    val user: User
)

data class User(
    val id: Int,
    val username: String,
    val avatar: String
)