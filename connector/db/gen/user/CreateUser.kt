package user

import java.sql.PreparedStatement
import java.sql.ResultSet
import kotlin.Int
import kotlin.String
import norm.ParamSetter
import norm.Query
import norm.RowMapper

data class CreateUserParams(
  val firstName: String?,
  val lastName: String?,
  val phone: String?,
  val email: String?
)

class CreateUserParamSetter : ParamSetter<CreateUserParams> {
  override fun map(ps: PreparedStatement, params: CreateUserParams) {
    ps.setObject(1, params.firstName)
    ps.setObject(2, params.lastName)
    ps.setObject(3, params.phone)
    ps.setObject(4, params.email)
  }
}

data class CreateUserResult(
  val id: Int,
  val firstName: String?,
  val lastName: String?,
  val phone: String?,
  val email: String?
)

class CreateUserRowMapper : RowMapper<CreateUserResult> {
  override fun map(rs: ResultSet): CreateUserResult = CreateUserResult(
  id = rs.getObject("id") as kotlin.Int,
    firstName = rs.getObject("first_name") as kotlin.String?,
    lastName = rs.getObject("last_name") as kotlin.String?,
    phone = rs.getObject("phone") as kotlin.String?,
    email = rs.getObject("email") as kotlin.String?)
}

class CreateUserQuery : Query<CreateUserParams, CreateUserResult> {
  override val sql: String = """
      |INSERT INTO users(first_name, last_name, phone, email)
      |VALUES (?, ?, ?, ?)
      |returning *;
      |""".trimMargin()

  override val mapper: RowMapper<CreateUserResult> = CreateUserRowMapper()

  override val paramSetter: ParamSetter<CreateUserParams> = CreateUserParamSetter()
}
