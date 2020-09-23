package example.api.user

import example.api.user.model.UserRequest

interface UserController {

    fun addUser(user: UserRequest)
}
