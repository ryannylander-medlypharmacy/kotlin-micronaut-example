package example.api.security

import io.micronaut.http.HttpResponse
import java.util.*
import javax.inject.Singleton

interface BasicAuthorizer {
    fun <T> authorize(authHeader: Optional<String>, f: () -> HttpResponse<T>): HttpResponse<T>
}

@Singleton
class DefaultBasicAuthorizer : BasicAuthorizer {
    override fun <T> authorize(authHeader: Optional<String>, f: () -> HttpResponse<T>): HttpResponse<T> = f()
}
