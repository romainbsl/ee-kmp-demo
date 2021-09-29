package org.kodein.shared

import kotlinx.serialization.Serializable
import org.kodein.db.model.orm.Metadata
import kotlin.random.Random

@Serializable
data class UserApiResult(
    val results: List<User>
)

@Serializable
data class User(
    val name: Name,
    val login: Login,
    val email: String,
    val phone: String,
)

@Serializable
data class Name(
    val last: String,
    val first: String
)

@Serializable
data class Login(
    val uuid: String,
    val username: String,
    val password: String
)

fun User.asDomain() = UserDomain(
    name = name,
    login = login,
    email = email,
    phone = phone
)

@Serializable
data class UserDomain(
    override val id: Int = Random.nextInt(),
    val name: Name,
    val login: Login,
    val email: String,
    val phone: String,
) : Metadata
