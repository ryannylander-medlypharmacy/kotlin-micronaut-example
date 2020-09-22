package example.api.security

import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.security.authentication.Authentication
import javax.inject.Singleton

@Singleton
class DefaultBearerAuthorizer : BearerAuthorizer {
    override fun <T> ifAuthorizedForClaim(
        claim: String,
        authentication: Authentication,
        f: () -> HttpResponse<T>
    ): HttpResponse<T> =
        if (authentication.hasClaim(claim)) f()
        else HttpResponse.status(HttpStatus.FORBIDDEN)
}

interface BearerAuthorizer {
    fun <T> ifAuthorizedForClaim(
        claim: String,
        authentication: Authentication,
        f: () -> HttpResponse<T>
    ): HttpResponse<T>
}

fun Authentication.hasClaim(claim: String): Boolean = (this.attributes["deliveryClaims"] as List<*>).contains(claim)
fun Authentication.userName(): String = this.attributes["sub"] as String
fun Authentication.getAllClaims(): List<String> = this.attributes["deliveryClaims"] as List<String>
