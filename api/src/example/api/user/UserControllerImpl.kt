package example.api.user

import example.api.user.model.UserRequest
import example.api.user.model.toUserEntity
import example.core.user.UserService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserControllerImpl (
    @Inject private val userService: UserService
) : UserController {

    override fun addUser(user: UserRequest) {
        userService.addUser( user.toUserEntity() )
    }
}
