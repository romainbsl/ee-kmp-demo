package org.kodein.shared

import kotlin.test.Test

import io.ktor.client.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withTimeout
import kotlinx.serialization.json.Json
import org.kodein.db.DB
import org.kodein.db.inmemory.inMemory
import org.kodein.db.orm.kotlinx.KotlinxSerializer
import kotlin.time.ExperimentalTime
import kotlin.time.seconds

@OptIn(ExperimentalTime::class)
class UserApiTest {
    private val httpClient by lazy {
        HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
    }
    private val db = DB.inMemory.open("user", KotlinxSerializer())
    private val userRepository by lazy { UserRepository(db) }

    @Test
    fun main() = runSuspendBlocking {
        val userApi= UserApi(httpClient, userRepository, this)
        userApi.fetchUsers()
        withTimeout(5.seconds) {
            userRepository.userListState().first { it.size == 100 }
        }
    }

}
