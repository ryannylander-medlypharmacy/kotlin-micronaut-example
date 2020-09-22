package example.api.utils

import io.micronaut.context.annotation.Replaces
import liquibase.Liquibase
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.FileSystemResourceAccessor
import org.junit.ClassRule
import org.postgresql.ds.PGSimpleDataSource
import org.testcontainers.containers.PostgreSQLContainer
import java.sql.DriverManager
import javax.inject.Singleton
import javax.sql.DataSource

class MyPostgreSQLContainer : PostgreSQLContainer<MyPostgreSQLContainer>()

@Singleton
@Replaces(DataSource::class)
class TestDataSource : PGSimpleDataSource() {

    @ClassRule
    private val sqlContainer = MyPostgreSQLContainer().also { it.start() }

    init {
        val connection = DriverManager.getConnection(
            sqlContainer.jdbcUrl,
            sqlContainer.username,
            sqlContainer.password
        )

        Liquibase(
            "../migration/res/changelog.xml",
            FileSystemResourceAccessor(),
            JdbcConnection(connection)
        ).update("main")

        setURL(sqlContainer.jdbcUrl)
        user = sqlContainer.username
        password = sqlContainer.password
    }
}
