package example.core.user

import example.core.entity.User
import javax.inject.Inject
import javax.inject.Singleton
import example.core.utils.Log

@Singleton
class UserService(@Inject private val userRepository: UserRepository) {

    fun addUser(user: User) {
        Log.debug(TAG, "addUser")
        try {
            userRepository.addUser(user)
        } catch (e: Exception) {
            Log.error(TAG, e.message)
        }
    }

    companion object {
        @JvmField
        val TAG: String = UserService::class.java.simpleName
    }
}
