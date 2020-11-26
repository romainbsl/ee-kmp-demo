package org.kodein.shared

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking

actual fun runSuspendBlocking(block: suspend CoroutineScope.() -> Unit) = runBlocking { block() }
