/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kodein.demo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.kodein.shared.UserEntity

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MyScreenContent()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    DemoAppTheme {
        Surface {
            content()
        }
    }
}

@Composable
@ExperimentalCoroutinesApi
fun MyScreenContent() {
    val app = LocalContext.current.applicationContext as DemoApplication
    val userListState = app.userRepository.userListState().collectAsState()

    Column(modifier = Modifier.fillMaxHeight()) {
        Column(modifier = Modifier.weight(1f)) {

            Text(text = "User List (${userListState.value.count()})", modifier = Modifier.padding(6.dp))

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
fun UserRow(user: UserEntity) {
    Text(text = "${user.id} : ${user.name.first}, ${user.name.last}", modifier = Modifier.padding(12.dp))
}

@ExperimentalCoroutinesApi
@Preview("MyScreen preview")
@Composable
fun DefaultPreview() {
    MyApp {
        MyScreenContent()
    }
}
