package util

import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TestServerRule: TestRule {
    @PublishedApi internal val mockWebServer = MockWebServer()

    inline fun <reified T> instantiateClient(): T = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(T::class.java)

    override fun apply(base: Statement, description: Description?) = object: Statement() {
        @Throws(Throwable::class)
        override fun evaluate() {
            mockWebServer.start()

            base.evaluate()

            mockWebServer.shutdown()
        }
    }

    fun runServerBlocking(block: suspend (mockWebServer: MockWebServer) -> Unit) = runBlocking { block(mockWebServer) }
}