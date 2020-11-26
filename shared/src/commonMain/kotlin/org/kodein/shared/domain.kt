package org.kodein.shared

import kotlinx.serialization.Serializable
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.kodein.db.model.orm.Metadata
import kotlin.math.absoluteValue
import kotlin.random.Random
import kotlin.random.nextUInt

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

fun User.asEntity() = UserEntity(
    name = name,
    login = login,
    email = email,
    phone = phone
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

@Serializable
data class UserEntity(
    override val id: Int = Random.nextInt(),
    val name: Name,
    val login: Login,
    val email: String,
    val phone: String,
) : Metadata
