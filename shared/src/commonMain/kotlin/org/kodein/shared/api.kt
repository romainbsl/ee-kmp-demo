package org.kodein.shared

import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class UserApi(
    private val httpClient: HttpClient,
    private val userRepository: UserRepository,
    scope: CoroutineScope
) : CoroutineScope by scope {
    fun fetchUsers(size: Int = 100) {
        launch {
            val response = httpClient.get<UserApiResult>("https://randomuser.me/api/?results=$size")
            response.results.forEach {
                userRepository.saveUser(it.asEntity())
            }
        }
    }
}
