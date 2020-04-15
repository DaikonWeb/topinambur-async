package topinambur.async

import daikon.HttpServer
import daikon.core.HttpStatus.OK_200
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class HttpAsyncClientTest {

    @Test
    fun `Deferred request`() {
        HttpServer(8080)
            .any("/") { _, res -> res.status(OK_200) }
            .start().use {
                runBlocking {
                    val http = local("/").httpAsync(this)

                    assertThat(http.get().await().statusCode).isEqualTo(OK_200)
                    assertThat(http.post().await().statusCode).isEqualTo(OK_200)
                    assertThat(http.delete().await().statusCode).isEqualTo(OK_200)
                    assertThat(http.head().await().statusCode).isEqualTo(OK_200)
                    assertThat(http.options().await().statusCode).isEqualTo(OK_200)
                    assertThat(http.put().await().statusCode).isEqualTo(OK_200)
                    assertThat(http.call("GET").await().statusCode).isEqualTo(OK_200)
                }
            }
    }

    @Test
    fun `Launch request`() {
        HttpServer(8080)
            .any("/") { _, res -> res.status(OK_200) }
            .start().use {
                runBlocking {
                    val http = local("/").httpAsync(this)
                    http.get { assertThat(it.statusCode).isEqualTo(OK_200) }
                    http.post { assertThat(it.statusCode).isEqualTo(OK_200) }
                    http.delete { assertThat(it.statusCode).isEqualTo(OK_200) }
                    http.head { assertThat(it.statusCode).isEqualTo(OK_200) }
                    http.options { assertThat(it.statusCode).isEqualTo(OK_200) }
                    http.put { assertThat(it.statusCode).isEqualTo(OK_200) }
                    http.call("GET") { assertThat(it.statusCode).isEqualTo(OK_200) }
                }
            }
    }

    private fun local(path: String) = "http:$path/localhost:8080/"
}
