import org.w3c.xhr.XMLHttpRequest

object Api {
    private val GET = "GET"

    fun init(url: String): XMLHttpRequest {
        val request = XMLHttpRequest()
        request.open(GET, url, true)
        return request
    }

    fun call(url: String) {
        val request = init(url)
        request.send()
    }
}

class Overloader {
    private val BLOCKING_API = "http://localhost:8080/api/blocking"
    private val ASYNC_API = "http://localhost:8080/api/async"
    val ASYNC_DATA = "$ASYNC_API/data"
    val ASYNC_DATA2 = "$ASYNC_API/data2"
    val ASYNC_RESET = "$ASYNC_API/reset"
    val BLOCKING_DATA = "$BLOCKING_API/data"
    val BLOCKING_RESET = "$BLOCKING_API/reset"

    fun resetAndCall(url: String, nbRequests: Int) {
        Api.call(BLOCKING_RESET)
        Api.call(ASYNC_RESET)
        (1..nbRequests).forEach { Api.call(url) }
    }

    @JsName("getDataBlocking")
    fun getDataBlocking(nbRequests: Int = 1) = resetAndCall(BLOCKING_DATA, nbRequests)

    @JsName("getDataAsync")
    fun getDataAsync(nbRequests: Int = 1) = resetAndCall(ASYNC_DATA, nbRequests)

    @JsName("getDataAsync2")
    fun getDataAsync2(nbRequests: Int = 1) = resetAndCall(ASYNC_DATA2, nbRequests)
}

fun main(args: Array<String>) {
    console.log("Overloader initialized")
}