package example.api.user.model

import example.core.entity.User

data class UserRequest (
    val firstName: String,
    val lastName: String,
    val phone: String,
    val email: String
)

fun UserRequest.toUserEntity() = User(
    firstName = firstName,
    lastName = lastName,
    phone = phone,
    email = email
)
