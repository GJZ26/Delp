package up.chiapas.delp.core.shared

data class ActionResponse(
    val success: Boolean,
    val message: String
)

data class ActionResponseAs<T>(
    val success: Boolean,
    val message: String,
    val content: T?
)