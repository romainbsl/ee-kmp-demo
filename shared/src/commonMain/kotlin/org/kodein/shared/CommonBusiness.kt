package org.kodein.shared

import io.ktor.client.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import kotlinx.coroutines.MainScope
import kotlinx.serialization.json.Json
import org.kodein.db.DB
import org.kodein.db.impl.default
import org.kodein.db.inDir
import org.kodein.db.inmemory.inMemory
import org.kodein.db.orm.kotlinx.KotlinxSerializer

class CommonBusiness(private val ctx: PlatformContext) {
    private val httpClient by lazy {
        HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    private val dbFactory by lazy { DB.default.inDir(getApplicationFilesDirectoryPath(ctx)) }
    private val appDB by lazy { dbFactory.open("user", KotlinxSerializer()) }
//    private val appDB = DB.inMemory.open("user", KotlinxSerializer())

    val userRepository by lazy { UserRepository(appDB) }
    val userApi by lazy { UserApi(httpClient, userRepository, MainScope()) }
}
