package example.api.user

import example.api.user.model.UserRequest
import example.api.user.model.UserResponse
import example.core.utils.Log
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import javax.inject.Inject

@Controller(value = "/api")
class FooBar @Inject constructor(private val controller: UserController) {

    /**
     * Add a new user to the database.
     *
     * @param userRequest the user to be added
     */
    @ApiResponse(
        responseCode = "200",
        description = "Add a new user to the database.",
        content = [Content(schema = Schema(implementation = UserResponse::class))]
    )
    @Produces(MediaType.APPLICATION_JSON)
    @Post(value = "/user/add")
    fun onPostUser(@Body("data") userRequest: UserRequest): HttpResponse<Any> {
        Log.debug(TAG, "onPostUser")
        controller.addUser(userRequest)
        return HttpResponse.status<Any>(HttpStatus.OK)
    }

    companion object {
        @JvmField
        val TAG: String = FooBar::class.java.simpleName
    }
}
