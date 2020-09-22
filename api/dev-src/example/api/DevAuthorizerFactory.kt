package example.api

import example.api.security.BearerAuthorizer
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Primary
import io.micronaut.http.HttpResponse
import io.micronaut.security.authentication.Authentication
import javax.inject.Singleton

@Factory
class DevAuthorizerFactory {

    @Bean
    @Primary
    @Singleton
    fun devAuthorizer(): BearerAuthorizer = DevBearerAuthorizer()

    class DevBearerAuthorizer : BearerAuthorizer {
        override fun <T> ifAuthorizedForClaim(
            claim: String,
            authentication: Authentication,
            f: () -> HttpResponse<T>
        ): HttpResponse<T> = f()
    }
}
