package topinambur.async

import kotlinx.coroutines.*
import topinambur.AuthorizationStrategy
import topinambur.Http
import topinambur.None
import topinambur.ServerResponse
import java.io.PrintStream

class HttpAsync(private val scope: CoroutineScope, url: String = "", log: PrintStream? = null) {
    private val client = Http(url, log)

    suspend fun get(url: String = "", params: Map<String, String> = emptyMap(), headers: Map<String, String> = emptyMap(), auth: AuthorizationStrategy = None(), followRedirects: Boolean = true, timeoutMillis: Int = 30000)
            = async { client.get(url, params, headers, auth, followRedirects, timeoutMillis) }

    fun get(url: String = "", params: Map<String, String> = emptyMap(), headers: Map<String, String> = emptyMap(), auth: AuthorizationStrategy = None(), followRedirects: Boolean = true, timeoutMillis: Int = 30000, callback: (ServerResponse) -> Unit)
            = launch { callback(client.get(url, params, headers, auth, followRedirects, timeoutMillis)) }

    suspend fun head(url: String = "", params: Map<String, String> = emptyMap(), headers: Map<String, String> = emptyMap(), auth: AuthorizationStrategy = None(), followRedirects: Boolean = true, timeoutMillis: Int = 30000)
            = async { client.head(url, params, headers, auth, followRedirects, timeoutMillis) }

    fun head(url: String = "", params: Map<String, String> = emptyMap(), headers: Map<String, String> = emptyMap(), auth: AuthorizationStrategy = None(), followRedirects: Boolean = true, timeoutMillis: Int = 30000, callback: (ServerResponse) -> Unit)
            = launch { callback(client.head(url, params, headers, auth, followRedirects, timeoutMillis)) }

    suspend fun options(url: String = "", params: Map<String, String> = emptyMap(), headers: Map<String, String> = emptyMap(), auth: AuthorizationStrategy = None(), followRedirects: Boolean = true, timeoutMillis: Int = 30000)
            = async { client.options(url, params, headers, auth, followRedirects, timeoutMillis) }

    fun options(url: String = "", params: Map<String, String> = emptyMap(), headers: Map<String, String> = emptyMap(), auth: AuthorizationStrategy = None(), followRedirects: Boolean = true, timeoutMillis: Int = 30000, callback: (ServerResponse) -> Unit)
            = launch { callback(client.options(url, params, headers, auth, followRedirects, timeoutMillis)) }

    suspend fun post(url: String = "", body: String = "", headers: Map<String, String> = emptyMap(), auth: AuthorizationStrategy = None(), followRedirects: Boolean = true, timeoutMillis: Int = 30000)
            = async { client.post(url, body, headers, auth, followRedirects, timeoutMillis) }

    fun post(url: String = "", body: String = "", headers: Map<String, String> = emptyMap(), auth: AuthorizationStrategy = None(), followRedirects: Boolean = true, timeoutMillis: Int = 30000, callback: (ServerResponse) -> Unit)
            = launch { callback(client.post(url, body, headers, auth, followRedirects, timeoutMillis)) }

    suspend fun put(url: String = "", body: String = "", data: Map<String, String> = emptyMap(), headers: Map<String, String> = emptyMap(), auth: AuthorizationStrategy = None(), followRedirects: Boolean = true, timeoutMillis: Int = 30000)
            = async { client.put(url, body, headers, auth, followRedirects, timeoutMillis) }

    fun put(url: String = "", body: String = "", headers: Map<String, String> = emptyMap(), auth: AuthorizationStrategy = None(), followRedirects: Boolean = true, timeoutMillis: Int = 30000, callback: (ServerResponse) -> Unit)
            = launch { callback(client.put(url, body, headers, auth, followRedirects, timeoutMillis)) }

    suspend fun delete(url: String = "", body: String = "", headers: Map<String, String> = emptyMap(), auth: AuthorizationStrategy = None(), followRedirects: Boolean = true, timeoutMillis: Int = 30000)
            = async { client.delete(url, body, headers, auth, followRedirects, timeoutMillis) }

    fun delete(url: String = "", body: String = "", headers: Map<String, String> = emptyMap(), auth: AuthorizationStrategy = None(), followRedirects: Boolean = true, timeoutMillis: Int = 30000, callback: (ServerResponse) -> Unit)
            = launch { callback(client.delete(url, body, headers, auth, followRedirects, timeoutMillis)) }

    suspend fun call(url: String = "", method: String = "GET", params: Map<String, String> = emptyMap(), data: String = "", headers: Map<String, String> = emptyMap(), auth: AuthorizationStrategy = None(), followRedirects: Boolean = true, timeoutMillis: Int = 30000)
            = async { client.call(url, method, params, data.toByteArray(), headers, auth, followRedirects, timeoutMillis) }

    fun call(url: String = "", method: String = "GET", params: Map<String, String> = emptyMap(), data: String = "", headers: Map<String, String> = emptyMap(), auth: AuthorizationStrategy = None(), followRedirects: Boolean = true, timeoutMillis: Int = 30000, callback: (ServerResponse) -> Unit)
            = launch { callback(client.call(url, method, params, data.toByteArray(), headers, auth, followRedirects, timeoutMillis)) }

    private suspend fun async(callback: () -> ServerResponse): Deferred<ServerResponse> {
        return scope.async {
            withContext(Dispatchers.IO) {
                callback()
            }
        }
    }

    private fun launch(callback: () -> Unit) {
        scope.launch {
            withContext(Dispatchers.IO) {
                callback()
            }
        }
    }
}