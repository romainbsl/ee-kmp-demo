package org.kodein.demo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.kodein.shared.UserDomain


@Composable
@ExperimentalCoroutinesApi
fun ContentView() {
    val app = LocalContext.current.applicationContext as DemoApplication
    val userListState = app.userRepository.userListState().collectAsState()

    Column(modifier = Modifier.fillMaxHeight()) {
        Column(modifier = Modifier.weight(1f)) {

            Text(
                text = "User List (${userListState.value.count()})",
                modifier = Modifier.padding(6.dp)
            )

            LazyColumn {
                items(userListState.value) {
                    UserRow(user = it)
                    Divider()
                }
            }
        }
    }
}

@Composable
fun UserRow(user: UserDomain) {
    Text(
        text = "${user.id} : ${user.name.first}, ${user.name.last}",
        modifier = Modifier.padding(12.dp)
    )
}
