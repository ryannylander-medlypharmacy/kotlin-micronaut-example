package example.api.fixture

import example.core.entity.User

fun userFixture() = User(
    faker.name().firstName(),
    faker.name().lastName(),
    faker.phoneNumber().phoneNumber(),
    "example@mail.org"
)
