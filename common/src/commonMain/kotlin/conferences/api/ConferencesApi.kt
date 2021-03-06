package conferences.api

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import conferences.model.Conference

class ConferencesApi {

    private val baseUrl = "http://35.205.117.180"

    private val client by lazy {
        HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer(Json(JsonConfiguration()))
            }
        }
    }

    suspend fun fetchConferences() = client.get<List<Conference>>("$baseUrl/conferences")
}