package org.kodein.shared

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import org.kodein.db.DB
import org.kodein.db.on

@OptIn(ExperimentalCoroutinesApi::class)
class UserRepository(private val appDB: DB) {

    private val _userList = MutableStateFlow(emptyList<UserEntity>())
    fun userListState(): StateFlow<List<UserEntity>> = _userList

    init {
        appDB.on<UserEntity>().register {
            didPut { _userList.value += it }
            didDeleteIt { _userList.value -= it }
        }
    }

    fun saveUser(user: UserEntity) {
        appDB.put(user)
    }

    suspend fun getAllUsers(): List<UserEntity> {
        return userListState().first { it.size >= 100 }
    }
}