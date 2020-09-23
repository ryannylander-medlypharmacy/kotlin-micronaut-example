package example.core.user

import example.core.entity.User
import javax.inject.Singleton

@Singleton
interface UserRepository {
    fun addUser(user: User)
}
