package example.api

import io.micronaut.runtime.Micronaut
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info

@OpenAPIDefinition(
    info = Info(
        title = "Example Micronaut API",
        version = "0.1",
        description = "Kotlin Micronaut Example with Postgres"
    )
)
object ApiApplication {
    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build(*args).start()
    }
}
