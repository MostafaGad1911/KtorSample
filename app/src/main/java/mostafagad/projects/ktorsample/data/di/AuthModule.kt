package mostafagad.projects.ktorsample.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import mostafagad.projects.ktorsample.data.repositiories.ReposServiceImpl
import mostafagad.projects.ktorsample.domain.repositiories.ReposServiceRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AuthModule {

    @Singleton
    @Provides
    fun provideClient(): HttpClient {
        return HttpClient(Android) {
            install(Logging){
                level = LogLevel.ALL
            }
            install(JsonFeature){
                // https://stackoverflow.com/a/67083244/7698605
                serializer = KotlinxSerializer(json = kotlinx.serialization.json.Json {
                    isLenient = false
                    ignoreUnknownKeys = true
                    coerceInputValues = true
                })
            }
        }
    }

    @Singleton
    @Provides
    fun getReposServiceImpl(httpClient: HttpClient): ReposServiceRepository{
        return ReposServiceImpl(httpClient)
    }


}