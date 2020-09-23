package user

import example.core.entity.User
import example.core.user.UserRepository
import norm.query
import javax.inject.Inject
import javax.inject.Singleton
import javax.sql.DataSource

@Singleton
class UserRepositoryImpl (
    @Inject private val dataSource: DataSource
) : UserRepository {
    override fun addUser(user: User) {
        dataSource.connection.use {
            connection -> CreateUserQuery().query(
                connection,
                with(user) {
                    CreateUserParams(
                        firstName = firstName,
                        lastName = lastName,
                        phone = phone,
                        email = email
                    )
                }
            )
        }
    }
}
