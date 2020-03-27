package topinambur.async

import kotlinx.coroutines.*
import topinambur.AuthorizationStrategy
import topinambur.HttpClient
import topinambur.None
import topinambur.ServerResponse
import java.io.PrintStream

class HttpAsyncClient(private val scope: CoroutineScope = GlobalScope, url: String, log: PrintStream? = null) {
    private val client = HttpClient(url, log)

    suspend fun get(params: Map<String, String> = emptyMap(), headers: Map<String, String> = emptyMap(), auth: AuthorizationStrategy = None(), followRedirects: Boolean = true, timeoutMillis: Int = 30000)
            = async { client.get(params, headers, auth, followRedirects, timeoutMillis) }

    suspend fun get(params: Map<String, String> = emptyMap(), headers: Map<String, String> = emptyMap(), auth: AuthorizationStrategy = None(), followRedirects: Boolean = true, timeoutMillis: Int = 30000, callback: (ServerResponse) -> Unit)
            = launch { callback(client.get(params, headers, auth, followRedirects, timeoutMillis)) }

    suspend fun head(params: Map<String, String> = emptyMap(), headers: Map<String, String> = emptyMap(), auth: AuthorizationStrategy = None(), followRedirects: Boolean = true, timeoutMillis: Int = 30000)
            = async { client.head(params, headers, auth, followRedirects, timeoutMillis) }

    suspend fun head(params: Map<String, String> = emptyMap(), headers: Map<String, String> = emptyMap(), auth: AuthorizationStrategy = None(), followRedirects: Boolean = true, timeoutMillis: Int = 30000, callback: (ServerResponse) -> Unit)
            = launch { callback(client.head(params, headers, auth, followRedirects, timeoutMillis)) }

    suspend fun options(params: Map<String, String> = emptyMap(), headers: Map<String, String> = emptyMap(), auth: AuthorizationStrategy = None(), followRedirects: Boolean = true, timeoutMillis: Int = 30000)
            = async { client.options(params, headers, auth, followRedirects, timeoutMillis) }

    suspend fun options(params: Map<String, String> = emptyMap(), headers: Map<String, String> = emptyMap(), auth: AuthorizationStrategy = None(), followRedirects: Boolean = true, timeoutMillis: Int = 30000, callback: (ServerResponse) -> Unit)
            = launch { callback(client.options(params, headers, auth, followRedirects, timeoutMillis)) }

    suspend fun post(body: String = "", data: Map<String, String> = emptyMap(), headers: Map<String, String> = emptyMap(), auth: AuthorizationStrategy = None(), followRedirects: Boolean = true, timeoutMillis: Int = 30000)
            = async { client.post(body, data, headers, auth, followRedirects, timeoutMillis) }

    suspend fun post(body: String = "", data: Map<String, String> = emptyMap(), headers: Map<String, String> = emptyMap(), auth: AuthorizationStrategy = None(), followRedirects: Boolean = true, timeoutMillis: Int = 30000, callback: (ServerResponse) -> Unit)
            = launch { callback(client.post(body, data, headers, auth, followRedirects, timeoutMillis)) }

    suspend fun put(body: String = "", data: Map<String, String> = emptyMap(), headers: Map<String, String> = emptyMap(), auth: AuthorizationStrategy = None(), followRedirects: Boolean = true, timeoutMillis: Int = 30000)
            = async { client.put(body, data, headers, auth, followRedirects, timeoutMillis) }

    suspend fun put(body: String = "", data: Map<String, String> = emptyMap(), headers: Map<String, String> = emptyMap(), auth: AuthorizationStrategy = None(), followRedirects: Boolean = true, timeoutMillis: Int = 30000, callback: (ServerResponse) -> Unit)
            = launch { callback(client.put(body, data, headers, auth, followRedirects, timeoutMillis)) }

    suspend fun delete(body: String = "", data: Map<String, String> = emptyMap(), headers: Map<String, String> = emptyMap(), auth: AuthorizationStrategy = None(), followRedirects: Boolean = true, timeoutMillis: Int = 30000)
            = async { client.delete(body, data, headers, auth, followRedirects, timeoutMillis) }

    suspend fun delete(body: String = "", data: Map<String, String> = emptyMap(), headers: Map<String, String> = emptyMap(), auth: AuthorizationStrategy = None(), followRedirects: Boolean = true, timeoutMillis: Int = 30000, callback: (ServerResponse) -> Unit)
            = launch { callback(client.delete(body, data, headers, auth, followRedirects, timeoutMillis)) }

    suspend fun call(method: String = "GET", params: Map<String, String> = emptyMap(), data: String = "", headers: Map<String, String> = emptyMap(), auth: AuthorizationStrategy = None(), followRedirects: Boolean = true, timeoutMillis: Int = 30000)
            = async { client.call(method, params, data, headers, auth, followRedirects, timeoutMillis) }

    suspend fun call(method: String = "GET", params: Map<String, String> = emptyMap(), data: String = "", headers: Map<String, String> = emptyMap(), auth: AuthorizationStrategy = None(), followRedirects: Boolean = true, timeoutMillis: Int = 30000, callback: (ServerResponse) -> Unit)
            = launch { callback(client.call(method, params, data, headers, auth, followRedirects, timeoutMillis)) }

    private suspend fun async(callback: () -> ServerResponse): Deferred<ServerResponse> {
        return scope.async {
            withContext(Dispatchers.IO) {
                callback()
            }
        }
    }

    private suspend fun launch(callback: () -> Unit) {
        scope.launch {
            withContext(Dispatchers.IO) {
                callback()
            }
        }
    }
}